package com.prodigy.fondbase.service.logging;

import com.prodigy.fondbase.SecurityUtil;
import com.prodigy.fondbase.dao.logging.LoggingChangesDao;
import com.prodigy.fondbase.dao.logging.LoggingMainDao;
import com.prodigy.fondbase.dao.logging.LoggingTypeDao;
import com.prodigy.fondbase.model.Address;
import com.prodigy.fondbase.model.LivingPlace;
import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.model.SubscriberType;
import com.prodigy.fondbase.model.logging.LoggingChanges;
import com.prodigy.fondbase.model.logging.LoggingMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoggingMainServiceImpl implements LoggingMainService {

    private final int USER_TYPE = 1000;
    private final int SUBSCRIBER_TYPE = 1001;

    @Autowired
    private LoggingMainDao loggingMainDao;

    @Autowired
    private LoggingTypeDao loggingTypeDao;

    @Autowired
    private LoggingChangesDao loggingChangesDao;

    @Override
    public LoggingMain get(int id) {
        return loggingMainDao.get(id);
    }

    @Override
    @Transactional
    public LoggingMain save(LoggingMain main) {
        return loggingMainDao.save(main);
    }

    @Override
    @Transactional
    public boolean compareAndSave(Subscriber previousSubscriber, Subscriber newSubscriber) throws Exception {

        try {

            LoggingMain main = new LoggingMain();
            main.setUser(SecurityUtil.get().getUser());
            main.setSubscriber(newSubscriber);
            main.setType(loggingTypeDao.get(SUBSCRIBER_TYPE));

            List<LoggingChanges> loggingChanges = new ArrayList<>();


            if (previousSubscriber == null) {

                loggingChanges.add(new LoggingChanges("Фамилия", "", newSubscriber.getLastname()));
                loggingChanges.add(new LoggingChanges("Имя", "", newSubscriber.getFirstname()));
                loggingChanges.add(new LoggingChanges("Отчество", "", newSubscriber.getMiddlename()));
                loggingChanges.add(new LoggingChanges("Телефон", "", (newSubscriber.getPhone() == null) ? "" : newSubscriber.getPhone().getCellPhone()));
                loggingChanges.add(new LoggingChanges("Почтовый адрес", "", newSubscriber.getEmail()));
                loggingChanges.add(new LoggingChanges("ИИН", "", newSubscriber.getIin()));
                loggingChanges.add(new LoggingChanges("Паспорт", "", newSubscriber.getPassport()));
                loggingChanges.add(new LoggingChanges("Дата выдачи", "", (newSubscriber.getDateOfIssue() == null) ? "" : newSubscriber.getDateOfIssue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
                loggingChanges.add(new LoggingChanges("День рождения", "", (newSubscriber.getBirthday() == null) ? "" : newSubscriber.getBirthday().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));

                for (SubscriberType newType : newSubscriber.getSubscriberTypes()) {
                    LoggingChanges log = new LoggingChanges("Кем является", "", newType.getName());
                    loggingChanges.add(log);
                }

                Address newAddressRegistration = null;
                Address newAddressResidential = null;
                for (Address address : newSubscriber.getAddresses()) {
                    if (address.getLivingPlace() == LivingPlace.REGISTRATION_AND_RESIDENCE_PLACE) {
                        newAddressRegistration = address;
                    } else if (address.getLivingPlace() == LivingPlace.REGISTRATION_PLACE) {
                        newAddressRegistration = address;
                    } else {
                        newAddressResidential = address;
                    }
                }
                loggingChanges.add(new LoggingChanges("Индекс рег", "", newAddressRegistration.getMailIndex()));
                loggingChanges.add(new LoggingChanges("Регион рег", "", newAddressRegistration.getRegion().getName()));
                loggingChanges.add(new LoggingChanges("Город рег", "", newAddressRegistration.getCity().getName()));
                loggingChanges.add(new LoggingChanges("Район рег", "", newAddressRegistration.getDistrict().getName()));
                loggingChanges.add(new LoggingChanges("Улица рег", "", newAddressRegistration.getHouse().getStreet().getName()));
                loggingChanges.add(new LoggingChanges("Дом рег", "", newAddressRegistration.getHouse().getHouseNumber()));
                loggingChanges.add(new LoggingChanges("Корпус рег", "", newAddressRegistration.getHouse().getCorps()));
                loggingChanges.add(new LoggingChanges("Буква рег", "", newAddressRegistration.getHouse().getLetter()));
                loggingChanges.add(new LoggingChanges("Квартира рег", "", newAddressRegistration.getFlat()));

                if (newAddressResidential != null) {
                    loggingChanges.add(new LoggingChanges("Индекс", "", newAddressResidential.getMailIndex()));
                    loggingChanges.add(new LoggingChanges("Регион", "", newAddressResidential.getRegion().getName()));
                    loggingChanges.add(new LoggingChanges("Город", "", newAddressResidential.getCity().getName()));
                    loggingChanges.add(new LoggingChanges("Район", "", newAddressResidential.getDistrict().getName()));
                    loggingChanges.add(new LoggingChanges("Улица", "", newAddressResidential.getHouse().getStreet().getName()));
                    loggingChanges.add(new LoggingChanges("Дом", "", newAddressResidential.getHouse().getHouseNumber()));
                    loggingChanges.add(new LoggingChanges("Корпус", "", newAddressResidential.getHouse().getCorps()));
                    loggingChanges.add(new LoggingChanges("Буква", "", newAddressResidential.getHouse().getLetter()));
                    loggingChanges.add(new LoggingChanges("Квартира", "", newAddressResidential.getFlat()));
                }

                main = loggingMainDao.save(main);

                for (LoggingChanges log : loggingChanges) {
                    log.setMain(main);
                    loggingChangesDao.save(log);
                }

                return true;
            }


            if (newSubscriber.getLastname() != null && !newSubscriber.getLastname().equals(previousSubscriber.getLastname())) {
                LoggingChanges log = new LoggingChanges("Фамилия", previousSubscriber.getLastname(), newSubscriber.getLastname());
                loggingChanges.add(log);
            }
            if (newSubscriber.getFirstname() != null && !newSubscriber.getFirstname().equals(previousSubscriber.getFirstname())) {
                LoggingChanges log = new LoggingChanges("Имя", previousSubscriber.getFirstname(), newSubscriber.getFirstname());
                loggingChanges.add(log);
            }
            if (newSubscriber.getMiddlename() != null && !newSubscriber.getMiddlename().equals(previousSubscriber.getMiddlename())) {
                LoggingChanges log = new LoggingChanges("Отчество", previousSubscriber.getMiddlename(), newSubscriber.getMiddlename());
                loggingChanges.add(log);
            }
            if (previousSubscriber.getPhone() == null && newSubscriber.getPhone() != null) {
                LoggingChanges log = new LoggingChanges("Телефон", "", newSubscriber.getPhone().getCellPhone());
                loggingChanges.add(log);
            } else if (previousSubscriber.getPhone() != null && newSubscriber.getPhone() == null) {
                if (!"".equals(previousSubscriber.getPhone().getCellPhone())) {
                    LoggingChanges log = new LoggingChanges("Телефон", previousSubscriber.getPhone().getCellPhone(), "");
                    loggingChanges.add(log);
                }
            } else if (previousSubscriber.getPhone() != null && newSubscriber.getPhone() != null) {
                if (!newSubscriber.getPhone().getCellPhone().equals(previousSubscriber.getPhone().getCellPhone())) {
                    LoggingChanges log = new LoggingChanges("Телефон", previousSubscriber.getPhone().getCellPhone(), newSubscriber.getPhone().getCellPhone());
                    loggingChanges.add(log);
                }
            }
            if (newSubscriber.getIin() != null && !newSubscriber.getEmail().equals(previousSubscriber.getEmail())) {
                LoggingChanges log = new LoggingChanges("Почтовый адрес", previousSubscriber.getEmail(), newSubscriber.getEmail());
                loggingChanges.add(log);
            }

            if (newSubscriber.getIin() != null && !newSubscriber.getIin().equals(previousSubscriber.getIin())) {
                LoggingChanges log = new LoggingChanges("ИИН", previousSubscriber.getIin(), newSubscriber.getIin());
                loggingChanges.add(log);
            }
            if (newSubscriber.getPassport() != null && !newSubscriber.getPassport().equals(previousSubscriber.getPassport())) {
                LoggingChanges log = new LoggingChanges("Паспорт", previousSubscriber.getPassport(), newSubscriber.getPassport());
                loggingChanges.add(log);
            }
            if (newSubscriber.getDateOfIssue() == null && previousSubscriber.getDateOfIssue() != null) {
                LoggingChanges log = new LoggingChanges("Дата выдачи", previousSubscriber.getDateOfIssue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), "");
                loggingChanges.add(log);
            } else if (newSubscriber.getDateOfIssue() != null && previousSubscriber.getDateOfIssue() == null) {
                LoggingChanges log = new LoggingChanges("Дата выдачи", "", newSubscriber.getDateOfIssue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                loggingChanges.add(log);
            } else if (newSubscriber.getDateOfIssue() != null && previousSubscriber.getDateOfIssue() != null) {
                if (!newSubscriber.getDateOfIssue().minusDays(1).equals(previousSubscriber.getDateOfIssue())) {
                    LoggingChanges log = new LoggingChanges("Дата выдачи", previousSubscriber.getDateOfIssue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                            newSubscriber.getDateOfIssue().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    loggingChanges.add(log);
                }
            }

            if (newSubscriber.getBirthday() == null && previousSubscriber.getBirthday() != null) {
                LoggingChanges log = new LoggingChanges("День рождения", previousSubscriber.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), "");
                loggingChanges.add(log);
            } else if (newSubscriber.getBirthday() != null && previousSubscriber.getBirthday() == null) {
                LoggingChanges log = new LoggingChanges("День рождения", "", newSubscriber.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                loggingChanges.add(log);
            } else if (newSubscriber.getBirthday() != null && previousSubscriber.getBirthday() != null) {
                if (!newSubscriber.getBirthday().minusDays(1).equals(previousSubscriber.getBirthday())) {
                    LoggingChanges log = new LoggingChanges("День рождения", previousSubscriber.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                            newSubscriber.getBirthday().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    loggingChanges.add(log);
                }
            }

            List<SubscriberType> previousSubscriberTypes = previousSubscriber.getSubscriberTypes();
            List<SubscriberType> newSubscriberTypes = newSubscriber.getSubscriberTypes();
            boolean found = false;
            if (newSubscriberTypes.size() >= previousSubscriberTypes.size()) {
                for (SubscriberType newType : newSubscriberTypes) {
                    found = false;
                    for (SubscriberType previousType : previousSubscriberTypes) {
                        if (newType.getId().equals(previousType.getId())) {
                            found = true;
                        }
                    }
                    if (!found) {
                        LoggingChanges log = new LoggingChanges("Кем является", "", newType.getName());
                        loggingChanges.add(log);
                    }
                }
            } else {
                for (SubscriberType previousType : previousSubscriberTypes) {
                    found = false;
                    for (SubscriberType newType : newSubscriberTypes) {
                        if (previousType.getId().equals(newType.getId())) {
                            found = true;
                        }
                    }
                    if (!found) {
                        LoggingChanges log = new LoggingChanges("Кем является", previousType.getName(), "");
                        loggingChanges.add(log);
                    }
                }
            }

            List<Address> previousAddresses = previousSubscriber.getAddresses();
            List<Address> newAddresses = newSubscriber.getAddresses();
            Address prevAddressRegistration = null;
            Address prevAddressResidential = null;
            for (Address address : previousAddresses) {
                if (address.getLivingPlace() == LivingPlace.REGISTRATION_AND_RESIDENCE_PLACE) {
                    prevAddressRegistration = address;
                } else if (address.getLivingPlace() == LivingPlace.REGISTRATION_PLACE) {
                    prevAddressRegistration = address;
                } else {
                    prevAddressResidential = address;
                }
            }
            Address newAddressRegistration = null;
            Address newAddressResidential = null;
            for (Address address : newAddresses) {
                if (address.getLivingPlace() == LivingPlace.REGISTRATION_AND_RESIDENCE_PLACE) {
                    newAddressRegistration = address;
                } else if (address.getLivingPlace() == LivingPlace.REGISTRATION_PLACE) {
                    newAddressRegistration = address;
                } else {
                    newAddressResidential = address;
                }
            }
            if (prevAddressRegistration != null && newAddressRegistration != null) {
                if (!prevAddressRegistration.getMailIndex().equals(newAddressRegistration.getMailIndex())) {
                    LoggingChanges log = new LoggingChanges("Индекс рег", prevAddressRegistration.getMailIndex(),
                            newAddressRegistration.getMailIndex());
                    loggingChanges.add(log);
                }
                if (!prevAddressRegistration.getRegion().getId().equals(newAddressRegistration.getRegion().getId())) {
                    LoggingChanges log = new LoggingChanges("Регион рег", prevAddressRegistration.getRegion().getName(),
                            newAddressRegistration.getRegion().getName());
                    loggingChanges.add(log);
                }
                if (!prevAddressRegistration.getCity().getId().equals(newAddressRegistration.getCity().getId())) {
                    LoggingChanges log = new LoggingChanges("Город рег", prevAddressRegistration.getCity().getName(),
                            newAddressRegistration.getCity().getName());
                    loggingChanges.add(log);
                }
                if (!prevAddressRegistration.getDistrict().getId().equals(newAddressRegistration.getDistrict().getId())) {
                    LoggingChanges log = new LoggingChanges("Район рег", prevAddressRegistration.getDistrict().getName(),
                            newAddressRegistration.getDistrict().getName());
                    loggingChanges.add(log);
                }
                if (!prevAddressRegistration.getHouse().getStreet().getId().equals(newAddressRegistration.getHouse().getStreet().getId())) {
                    LoggingChanges log = new LoggingChanges("Улица рег", prevAddressRegistration.getHouse().getStreet().getName(),
                            newAddressRegistration.getHouse().getStreet().getName());
                    loggingChanges.add(log);
                }
                if (!prevAddressRegistration.getHouse().getHouseNumber().equals(newAddressRegistration.getHouse().getHouseNumber())) {
                    LoggingChanges log = new LoggingChanges("Дом рег", prevAddressRegistration.getHouse().getHouseNumber(),
                            newAddressRegistration.getHouse().getHouseNumber());
                    loggingChanges.add(log);
                }
                if (!prevAddressRegistration.getHouse().getCorps().equals(newAddressRegistration.getHouse().getCorps())) {
                    LoggingChanges log = new LoggingChanges("Корпус рег", prevAddressRegistration.getHouse().getCorps(),
                            newAddressRegistration.getHouse().getCorps());
                    loggingChanges.add(log);
                }
                if (!prevAddressRegistration.getHouse().getLetter().equals(newAddressRegistration.getHouse().getLetter())) {
                    LoggingChanges log = new LoggingChanges("Буква рег", prevAddressRegistration.getHouse().getLetter(),
                            newAddressRegistration.getHouse().getLetter());
                    loggingChanges.add(log);
                }
                if (!prevAddressRegistration.getFlat().equals(newAddressRegistration.getFlat())) {
                    LoggingChanges log = new LoggingChanges("Квартира рег", prevAddressRegistration.getFlat(),
                            newAddressRegistration.getFlat());
                    loggingChanges.add(log);
                }


            }

            if (prevAddressResidential != null && newAddressResidential != null) {
                if (!prevAddressResidential.getMailIndex().equals(newAddressResidential.getMailIndex())) {
                    LoggingChanges log = new LoggingChanges("Индекс", prevAddressResidential.getMailIndex(),
                            newAddressResidential.getMailIndex());
                    loggingChanges.add(log);
                }
                if (!prevAddressResidential.getRegion().getId().equals(newAddressRegistration.getRegion().getId())) {
                    LoggingChanges log = new LoggingChanges("Регион", prevAddressResidential.getRegion().getName(),
                            newAddressResidential.getRegion().getName());
                    loggingChanges.add(log);
                }
                if (!prevAddressResidential.getCity().getId().equals(newAddressResidential.getCity().getId())) {
                    LoggingChanges log = new LoggingChanges("Город", prevAddressResidential.getCity().getName(),
                            newAddressResidential.getCity().getName());
                    loggingChanges.add(log);
                }
                if (!prevAddressResidential.getDistrict().getId().equals(newAddressResidential.getDistrict().getId())) {
                    LoggingChanges log = new LoggingChanges("Район", prevAddressResidential.getDistrict().getName(),
                            newAddressResidential.getDistrict().getName());
                    loggingChanges.add(log);
                }
                if (!prevAddressResidential.getHouse().getStreet().getId().equals(newAddressResidential.getHouse().getStreet().getId())) {
                    LoggingChanges log = new LoggingChanges("Улица", prevAddressResidential.getHouse().getStreet().getName(),
                            newAddressResidential.getHouse().getStreet().getName());
                    loggingChanges.add(log);
                }
                if (!prevAddressResidential.getHouse().getHouseNumber().equals(newAddressResidential.getHouse().getHouseNumber())) {
                    LoggingChanges log = new LoggingChanges("Дом", prevAddressResidential.getHouse().getHouseNumber(),
                            newAddressResidential.getHouse().getHouseNumber());
                    loggingChanges.add(log);
                }
                if (!prevAddressResidential.getHouse().getCorps().equals(newAddressResidential.getHouse().getCorps())) {
                    LoggingChanges log = new LoggingChanges("Корпус", prevAddressResidential.getHouse().getCorps(),
                            newAddressResidential.getHouse().getCorps());
                    loggingChanges.add(log);
                }
                if (!prevAddressResidential.getHouse().getLetter().equals(newAddressResidential.getHouse().getLetter())) {
                    LoggingChanges log = new LoggingChanges("Буква", prevAddressResidential.getHouse().getLetter(),
                            newAddressResidential.getHouse().getLetter());
                    loggingChanges.add(log);
                }
                if (!prevAddressResidential.getFlat().equals(newAddressResidential.getFlat())) {
                    LoggingChanges log = new LoggingChanges("Квартира", prevAddressResidential.getFlat(),
                            newAddressResidential.getFlat());
                    loggingChanges.add(log);
                }

            } else if (prevAddressResidential != null && newAddressResidential == null) {
                loggingChanges.add(new LoggingChanges("Индекс", prevAddressResidential.getMailIndex(), ""));
                loggingChanges.add(new LoggingChanges("Регион", prevAddressResidential.getRegion().getName(), ""));
                loggingChanges.add(new LoggingChanges("Город", prevAddressResidential.getCity().getName(), ""));
                loggingChanges.add(new LoggingChanges("Район", prevAddressResidential.getDistrict().getName(), ""));
                loggingChanges.add(new LoggingChanges("Улица", prevAddressResidential.getHouse().getStreet().getName(), ""));
                loggingChanges.add(new LoggingChanges("Дом", prevAddressResidential.getHouse().getHouseNumber(), ""));
                loggingChanges.add(new LoggingChanges("Корпус", prevAddressResidential.getHouse().getCorps(), ""));
                loggingChanges.add(new LoggingChanges("Буква", prevAddressResidential.getHouse().getLetter(), ""));
                loggingChanges.add(new LoggingChanges("Квартира", prevAddressResidential.getFlat(), ""));

            } else if (prevAddressResidential == null && newAddressResidential != null) {
                loggingChanges.add(new LoggingChanges("Индекс", "", newAddressResidential.getMailIndex()));
                loggingChanges.add(new LoggingChanges("Регион", "", newAddressResidential.getRegion().getName()));
                loggingChanges.add(new LoggingChanges("Город", "", newAddressResidential.getCity().getName()));
                loggingChanges.add(new LoggingChanges("Район", "", newAddressResidential.getDistrict().getName()));
                loggingChanges.add(new LoggingChanges("Улица", "", newAddressResidential.getHouse().getStreet().getName()));
                loggingChanges.add(new LoggingChanges("Дом", "", newAddressResidential.getHouse().getHouseNumber()));
                loggingChanges.add(new LoggingChanges("Корпус", "", newAddressResidential.getHouse().getCorps()));
                loggingChanges.add(new LoggingChanges("Буква", "", newAddressResidential.getHouse().getLetter()));
                loggingChanges.add(new LoggingChanges("Квартира", "", newAddressResidential.getFlat()));

            }

            if (loggingChanges.size() > 0) {
                main = loggingMainDao.save(main);
            } else {
                return true;
            }

            for (LoggingChanges log : loggingChanges) {
                log.setMain(main);
                loggingChangesDao.save(log);
            }

        } catch (Exception e) {
            System.out.println("Exception during logging operations related to Subscriber with ID=" + newSubscriber.getId());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean newSave(Subscriber newSubscriber) {

        LoggingMain main = new LoggingMain();
        main.setUser(SecurityUtil.get().getUser());
        main.setSubscriber(newSubscriber);
        main.setType(loggingTypeDao.get(SUBSCRIBER_TYPE));

        List<LoggingChanges> loggingChanges = new ArrayList<>();

        return false;
    }
}
