package com.prodigy.fondbase.service.impl;

import com.prodigy.fondbase.model.*;
import com.prodigy.fondbase.service.AbstractRootService;
import com.prodigy.fondbase.service.SubscriberService;
import com.prodigy.fondbase.to.SearchSubscriberTo;
import com.prodigy.fondbase.to.SubscriberTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SubscriberServiceImpl extends AbstractRootService implements SubscriberService {

    @Override
    @Transactional
    public Subscriber save(Subscriber subscriber) {
        return subscriberDao.save(subscriber);
    }

    @Override
    public List<Subscriber> searchPeople(SearchSubscriberTo searchSubscriberTo) {

        Subscriber subscriber = new Subscriber();
        Address address = new Address();

        String iin = null;
        if (!"".equals(searchSubscriberTo.getIin()) && searchSubscriberTo.getIin() != null) {
            iin = searchSubscriberTo.getIin();
            subscriber.setIin(iin);
        }
        String passport = null;
        if (!"".equals(searchSubscriberTo.getPassport()) && searchSubscriberTo.getPassport() != null) {
            passport = searchSubscriberTo.getPassport();
            subscriber.setPassport(passport);
        }
        String lastname = null;
        if (!"".equals(searchSubscriberTo.getLastname()) && searchSubscriberTo.getLastname() != null) {
            lastname = searchSubscriberTo.getLastname();
            subscriber.setLastname(lastname);
        }
        String firstname = null;
        if (!"".equals(searchSubscriberTo.getFirstname()) && searchSubscriberTo.getFirstname() != null) {
            firstname = searchSubscriberTo.getFirstname();
            subscriber.setFirstname(firstname);
        }
        String middlename = null;
        if (!"".equals(searchSubscriberTo.getMiddlename()) && searchSubscriberTo.getMiddlename() != null) {
            middlename = searchSubscriberTo.getMiddlename();
            subscriber.setMiddlename(middlename);
        }
        Region region = null;
        if (!"".equals(searchSubscriberTo.getRegion()) && searchSubscriberTo.getRegion() != null) {
            region = (Region) regionDao.get(Region.class, Integer.parseInt(searchSubscriberTo.getRegion()));
            address.setRegion(region);
        }
        City city = null;
        if (!"".equals(searchSubscriberTo.getCity()) && searchSubscriberTo.getCity() != null) {
            city = (City) cityDao.get(City.class, Integer.parseInt(searchSubscriberTo.getCity()));
            address.setCity(city);
        }
        District district = null;
        if (!"".equals(searchSubscriberTo.getDistrict()) && searchSubscriberTo.getDistrict() != null) {
            district = (District) districtDao.get(District.class, Integer.parseInt(searchSubscriberTo.getDistrict()));
            address.setDistrict(district);
        }
        Street street = null;
        if (!"".equals(searchSubscriberTo.getStreet()) && searchSubscriberTo.getStreet() != null) {
            street = (Street) streetDao.getByName(searchSubscriberTo.getStreet());
            if (street != null) {
                House house = new House();
                house.setStreet(street);
                address.setHouse(house);
            }
        }

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        subscriber.setAddresses(addresses);

        List<Subscriber> result = subscriberDao.searchPeople(subscriber);

        return result;


    }

    @Override
    public List<String> searchByLastname(String lastname) {
        return subscriberDao.searchByLastname(lastname);
    }

    @Override
    public List<String> searchByFirstname(String firstname) {
        return subscriberDao.searchByFirstname(firstname);
    }

    @Override
    public List<String> searchByMiddlename(String middlename) {
        return subscriberDao.searchByMiddlename(middlename);
    }

    @Override
    public Subscriber get(int id) {
        return (Subscriber) subscriberDao.get(Subscriber.class, id);
    }

    @Override
    public SubscriberTo getTo(int id) {

        Subscriber subscriber = (Subscriber) subscriberDao.get(Subscriber.class, id);

        return convertEntity(subscriber);
    }

    @Override
    public List<SearchSubscriberTo> convertSearchEntityList(List<Subscriber> subscriberList) {
        List<SearchSubscriberTo> searchSubscriberToList = new ArrayList<>();
        for (Subscriber subscriber : subscriberList) {
            searchSubscriberToList.add(convertSearchEntity(subscriber));
        }
        return searchSubscriberToList;

    }

    @Override
    public SearchSubscriberTo convertSearchEntity(Subscriber subscriber) {
        SearchSubscriberTo to = new SearchSubscriberTo();
        to.setId(subscriber.getId());
        to.setLastname(subscriber.getLastname());
        to.setFirstname(subscriber.getFirstname());
        to.setMiddlename(subscriber.getMiddlename());


        List<Address> addresses = addressDao.getBySubscriberId(subscriber.getId());

        to.setRegion(String.valueOf(addresses.get(0).getRegion().getId()));
        to.setCity(String.valueOf(addresses.get(0).getCity().getId()));
        to.setDistrict(addresses.get(0).getDistrict().getName());
        to.setStreet(addresses.get(0).getHouse().getStreet().getName());
        to.setHouse(addresses.get(0).getHouse().getHouseNumber());
        to.setFlat(addresses.get(0).getFlat());

        return to;
    }

    @Override
    public SubscriberTo convertEntity(Subscriber subscriber) {
        SubscriberTo to = new SubscriberTo();

        to.setId(subscriber.getId());
        to.setLastname(subscriber.getLastname());
        to.setFirstname(subscriber.getFirstname());
        to.setMiddlename(subscriber.getMiddlename());
        to.setBirthday(subscriber.getBirthday());
        to.setEmail(subscriber.getEmail());
        to.setIin(subscriber.getIin());
        to.setPassport(subscriber.getPassport());
        to.setDateOfIssue(subscriber.getDateOfIssue());

        Phone phone = subscriber.getPhone();
        to.setPhone((phone == null) ? "" : phone.getCellPhone());

        List<Address> addresses = subscriber.getAddresses();//addressDao.getBySubscriberId(subscriber.getId());
        for (Address address : addresses) {
            if (address.getLivingPlace() == LivingPlace.REGISTRATION_AND_RESIDENCE_PLACE) {
                to.setLivingForRegistration(true);
                to.setAddressRegistration(address);
            } else if (address.getLivingPlace() == LivingPlace.REGISTRATION_PLACE) {
                to.setAddressRegistration(address);
            } else if (address.getLivingPlace() == LivingPlace.RESIDENCE_PLACE) {
                to.setAddressResidential(address);
            }
        }
        for (SubscriberType type : subscriber.getSubscriberTypes()) {
            if (type.getId() == SubscriberType.STORONNIK_TYPE) {
                to.setStoronnik(true);
            }
        }
        return to;

    }

    @Override
    @Transactional
    public Subscriber convertToAndSave(SubscriberTo to) {

        // Block for logging
        // ***************************************************
        Subscriber dbSubscriber = (to.getId() != null) ? (Subscriber) subscriberDao.get(Subscriber.class, to.getId()) : null;
        Subscriber previousSubscriber = null;
        if (dbSubscriber != null) {
            previousSubscriber = new Subscriber();
            previousSubscriber.setId(dbSubscriber.getId());
            previousSubscriber.setLastname(dbSubscriber.getLastname() == null ? "" : dbSubscriber.getLastname());
            previousSubscriber.setFirstname(dbSubscriber.getFirstname() == null ? "" : dbSubscriber.getFirstname());
            previousSubscriber.setMiddlename(dbSubscriber.getMiddlename() == null ? "" : dbSubscriber.getMiddlename());
            previousSubscriber.setBirthday(dbSubscriber.getBirthday());
            previousSubscriber.setPhone(dbSubscriber.getPhone() == null ? null : new Phone(dbSubscriber.getPhone().getCellPhone()));
            previousSubscriber.setEmail(dbSubscriber.getEmail() == null ? "" : dbSubscriber.getEmail());
            previousSubscriber.setIin(dbSubscriber.getIin() == null ? "" : dbSubscriber.getIin());
            previousSubscriber.setPassport(dbSubscriber.getPassport() == null ? "" : dbSubscriber.getPassport());
            previousSubscriber.setDateOfIssue(dbSubscriber.getDateOfIssue());
            List<SubscriberType> subscriberTypes = new ArrayList<>();
            for (SubscriberType type : dbSubscriber.getSubscriberTypes()) {
                subscriberTypes.add(new SubscriberType(type.getId(), type.getName()));
            }
            previousSubscriber.setSubscriberTypes(subscriberTypes);
            previousSubscriber.setAddresses(dbSubscriber.getAddresses());

            List<Address> previousAddresses = previousSubscriber.getAddresses();
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
            previousAddresses = new ArrayList<>();
            if (prevAddressRegistration != null) {
                previousAddresses.add(new Address(prevAddressRegistration));
            }
            if (prevAddressResidential != null) {
                previousAddresses.add(new Address(prevAddressResidential));
            }
            previousSubscriber.setAddresses(previousAddresses);


        }
        //****************************************************

        Subscriber subscriber = new Subscriber();

        if (to.getId() != null) {
            subscriber = (Subscriber) subscriberDao.get(Subscriber.class, to.getId());
        }
        subscriber.setLastname(to.getLastname());
        subscriber.setFirstname(to.getFirstname());
        subscriber.setMiddlename(to.getMiddlename());
        subscriber.setBirthday((to.getBirthday() == null) ? null : to.getBirthday().plusDays(1));
        subscriber.setEmail(to.getEmail());
        subscriber.setIin(to.getIin());
        subscriber.setPassport(to.getPassport());
        subscriber.setDateOfIssue(to.getDateOfIssue() == null ? null : to.getDateOfIssue().plusDays(1));

        List<SubscriberType> subscriberTypes = new ArrayList<>();
        subscriber.setSubscriberTypes(null);
        if (to.isStoronnik()) {
            SubscriberType type = subscriberTypeDao.get(1000);
            subscriberTypes.add(type);
        }
        subscriber.setSubscriberTypes(subscriberTypes);

        List<Address> addresses = new ArrayList<>();
        Address addressRegistration = new Address();
        if (to.getAddressRegistration().getId() != null) {
            addressRegistration = (Address) addressDao.get(Address.class, to.getAddressRegistration().getId());
        }
        Region region = (Region) regionDao.get(Region.class, to.getAddressRegistration().getRegion().getId());
        City city = (City) cityDao.get(City.class, to.getAddressRegistration().getCity().getId());
        District district = (District) districtDao.get(District.class, to.getAddressRegistration().getDistrict().getId());
/*
        if (to.getAddressRegistration().getId() != null) {
            house = (House) houseDao.get(House.class, to.getAddressRegistration().getHouse().getId());
        }
*/
//        house = (house != null) ? house : new House();
        House house = new House();
        Street street = (Street) streetDao.get(Street.class, to.getAddressRegistration().getHouse().getStreet().getId());
        String houseNumber = to.getAddressRegistration().getHouse().getHouseNumber();
        String corps = to.getAddressRegistration().getHouse().getCorps();
        String letter = to.getAddressRegistration().getHouse().getLetter();
        house.setStreet(street);
        house.setHouseNumber(houseNumber);
        house.setCorps(corps);
        house.setLetter(letter);
        house.setDistrict(district);
        House foundHouse = houseDao.find(house);
        if (foundHouse != null) {
            house = foundHouse;
        } else {
            house = (House) houseDao.save(house);
        }
        String flat = to.getAddressRegistration().getFlat();
        String mailIndex = to.getAddressRegistration().getMailIndex();

        addressRegistration.setRegion(region);
        addressRegistration.setCity(city);
        addressRegistration.setDistrict(district);
        addressRegistration.setHouse(house);
        addressRegistration.setFlat(flat);
        addressRegistration.setMailIndex(mailIndex);

        Area area = addressRegistration.getArea();
        if (area != null) {
            area.setCity(city);
            Area dbArea = (Area) areaDao.getByName(Area.class, area.getName());
            area = (dbArea != null) ? dbArea : (Area) areaDao.save(area);
            addressRegistration.setArea(area);
        } else {
            Area ar = new Area(null, "empty");
            ar.setCity(city);
            Area dbArea = (Area) areaDao.getByName(Area.class, ar.getName());
            ar = (dbArea != null) ? dbArea : (Area) areaDao.save(ar);
            addressRegistration.setArea(ar);
        }


        if (to.isLivingForRegistration()) {
            addressRegistration.setLivingPlace(LivingPlace.REGISTRATION_AND_RESIDENCE_PLACE);
            addresses.add(addressRegistration);

            Address addressResidential = new Address();
            if (to.getAddressResidential().getId() != null) {
                addressDao.delete(Address.class, to.getAddressResidential().getId());
            }

        } else {
            addressRegistration.setLivingPlace(LivingPlace.REGISTRATION_PLACE);

            Address addressResidential = new Address();
            if (to.getAddressResidential().getId() != null) {
                addressResidential = (Address) addressDao.get(Address.class, to.getAddressResidential().getId());
            }
            Region region1 = (Region) regionDao.get(Region.class, to.getAddressResidential().getRegion().getId());
            City city1 = (City) cityDao.get(City.class, to.getAddressResidential().getCity().getId());
            District district1 = (District) districtDao.get(District.class, to.getAddressResidential().getDistrict().getId());
            House house1 = new House();
            if (to.getAddressResidential().getId() != null) {
                house1 = (House) houseDao.get(House.class, to.getAddressResidential().getHouse().getId());
            }
            house1 = (house1 != null) ? house1 : new House();
            Street street1 = (Street) streetDao.get(Street.class, to.getAddressResidential().getHouse().getStreet().getId());
            String houseNumber1 = to.getAddressResidential().getHouse().getHouseNumber();
            String corps1 = to.getAddressResidential().getHouse().getCorps();
            String letter1 = to.getAddressResidential().getHouse().getLetter();
            house1.setStreet(street1);
            house1.setHouseNumber(houseNumber1);
            house1.setCorps(corps1);
            house1.setLetter(letter1);
            house1.setDistrict(district1);
            House foundHouse1 = houseDao.find(house1);
            if (foundHouse1 != null) {
                house1 = foundHouse1;
            } else {
                house1 = (House) houseDao.save(house1);
            }
            String flat1 = to.getAddressResidential().getFlat();
            String mailIndex1 = to.getAddressResidential().getMailIndex();

            addressResidential.setRegion(region1);
            addressResidential.setCity(city1);
            addressResidential.setDistrict(district1);
            addressResidential.setHouse(house1);
            addressResidential.setFlat(flat1);
            addressResidential.setMailIndex(mailIndex1);

            Area newArea = addressResidential.getArea();
            if (newArea != null) {
                newArea.setCity(city);
                Area dbArea = (Area) areaDao.getByName(Area.class, newArea.getName());
                newArea = (dbArea != null) ? dbArea : (Area) areaDao.save(newArea);
                addressResidential.setArea(newArea);

            } else {
                Area ar = new Area(null, "empty");
                ar.setCity(city1);
                Area dbArea = (Area) areaDao.getByName(Area.class, ar.getName());
                ar = (dbArea != null) ? dbArea : (Area) areaDao.save(ar);
                addressResidential.setArea(ar);
            }

            addressResidential.setLivingPlace(LivingPlace.RESIDENCE_PLACE);

            addresses.add(addressRegistration);
            addresses.add(addressResidential);
        }

        subscriber.setAddresses(null);
        subscriber = save(subscriber);

        // Add phone number
        if (!"".equals(to.getPhone().trim())) {
            Phone phone = (subscriber.getPhone() != null) ? subscriber.getPhone() : new Phone(to.getPhone());
            phone.setCellPhone(to.getPhone());
            phone.setSubscriber(subscriber);
            phone = (Phone) phoneDao.save(phone);
            subscriber.setPhone(phone);
        }

        // Add addresses
        List<Address> newAddresses = new ArrayList<>();
        for (Address address : addresses) {
            address.setSubscriber(subscriber);
            address = (Address) addressDao.save(address);
            newAddresses.add(address);
        }
        subscriber.setAddresses(newAddresses);
//        subscriber = save(subscriber);

        try {
            loggingMainService.compareAndSave(previousSubscriber, subscriber);
        } catch (Exception e) {
            System.out.println("Exception during logging operations related to Subscriber with ID=" + subscriber.getId());
            System.out.println(e.getMessage());
        }

        return subscriber;
    }

    @Override
    @Transactional
    public Subscriber addTo(SubscriberTo subscriberTo) {

        Subscriber subscriber = convertToAndSave(subscriberTo);
        List<Address> addresses = subscriber.getAddresses();
        subscriber = save(subscriber);

        // Add phone number
        if (!"".equals(subscriberTo.getPhone().trim())) {
            Phone phone = new Phone(subscriberTo.getPhone());
            phone.setSubscriber(subscriber);
            phone = (Phone) phoneDao.save(phone);
            subscriber.setPhone(phone);
        }
        List<Address> newAddresses = new ArrayList<>();
        for (Address address : addresses) {
            address.setSubscriber(subscriber);
            address = (Address) addressDao.save(address);
            newAddresses.add(address);
        }
        subscriber.setAddresses(newAddresses);
        subscriber = save(subscriber);

        return subscriber;
    }
}
