package com.prodigy.fondbase.service.impl;

import com.prodigy.fondbase.download.AnalyzeDataResult;
import com.prodigy.fondbase.model.*;
import com.prodigy.fondbase.service.AbstractRootService;
import com.prodigy.fondbase.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class DownloadServiceImpl extends AbstractRootService implements DownloadService {

    private static final Logger log = LoggerFactory.getLogger(DownloadServiceImpl.class);

    @Override
    @Transactional
    public Subscriber save(Subscriber subscriber) {

        Address address = (subscriber.getAddresses().size() > 0) ? subscriber.getAddresses().get(0) : null;
        if(address == null){
            return null;
        }

        //Add new region if it does not exist
        Region region = address.getRegion();
        Region dbRegion = (Region) regionDao.getByName(Region.class, region.getName());
        region = (dbRegion != null) ? dbRegion : (Region) regionDao.save(region);

        //Add new city if it does not exist
        City city = address.getCity();
        city.setRegion(region);
        City dbCity = (City) cityDao.getByName(City.class, city.getName());
        city = (dbCity != null) ? dbCity : (City) cityDao.save(city);

        //Add new district if it does no exist
        District district = address.getDistrict();
        district.setCity(city);
        District dbDistrict = (District) districtDao.getByName(District.class, district.getName());
        district = (dbDistrict != null) ? dbDistrict : (District) districtDao.save(district);

        House house = address.getHouse();

        //Add new street if it does not exist
        Street street = house.getStreet();
        street.setCity(city);
        Street dbStreet = (Street) streetDao.getByName(Street.class, street.getName());
        street = (dbStreet != null) ? dbStreet : (Street) streetDao.save(street);

        house.setStreet(street);
        house.setDistrict(district);

        //find existing house
        House dbHouse = houseDao.find(house);

        //add house if it does not exist
        house = (dbHouse != null) ? dbHouse : (House) houseDao.save(house);

        //add new area if it does not exist
        Area area = address.getArea();
        area.setCity(city);
        Area dbArea = (Area) areaDao.getByName(Area.class, area.getName());
        area = (dbArea != null) ? dbArea : (Area) areaDao.save(area);

        subscriber.setAddresses(null);
        subscriber = (Subscriber) subscriberDao.save(subscriber);

        Phone phone = subscriber.getPhone();
        if (phone != null) {
            phone = (Phone) phoneDao.save(phone);
            phone.setSubscriber(subscriber);
        }
        /*else {
            Phone newPhone = new Phone();
            newPhone.setSubscriber(subscriber);
            phone = (Phone) phoneDao.save(newPhone);
        }*/

        address.setRegion(region);
        address.setCity(city);
        address.setDistrict(district);
        address.setHouse(house);
        address.setArea(area);
        address.setSubscriber(subscriber);
        Address dbAddress = (Address) addressDao.save(address);

        log.debug("Added Subscriber(id={}, lastname={}, firstname={}, middlename={}, birthday={}, subscribe_day={}, status={}, " +
                        "area_number={}, mailIndex={}, region={}, city={}, district={}, street={}," +
                        "house={}, corps={}, letter={}, flat={}, area={})",
                subscriber.getId(),
                subscriber.getLastname(), subscriber.getFirstname(), subscriber.getMiddlename(),
                subscriber.getBirthday(), subscriber.getSubscribeDay(), subscriber.getStatus(),
                area.getName(), address.getMailIndex(), region.getName(), city.getName(), district.getName(),
                street.getName(), house.getHouseNumber(), house.getCorps(), house.getLetter(), address.getFlat(),
                area.getName());

        return subscriber;
    }

    @Override
    public Subscriber get(int id) {
        return (Subscriber) subscriberDao.get(Subscriber.class, id);
    }

    @Override
    public boolean saveBatch(List<Subscriber> subscribers) {
        return false;
    }

    @Override
    public boolean analizeData(Map<String, String> streets, List<Subscriber> subscribers) {
/*
        AnalyzeDataResult analyzeDataResult = new AnalyzeDataResult();
        List<Subscriber> resultList = new ArrayList<>();

        for (Subscriber subscriber : subscribers) {

            String streetRus = subscriber.getAddresses().getHouse().getStreet().getName();
            String streetUkr = streets.get(streetRus);

            Street street = (Street) streetDao.getByName(Street.class, streetUkr);
            if (street == null) {
                log.info("{};{};{};{};{};{};{};{};{};NOT FOUND STREET",
                        subscriber.getLastname(), subscriber.getFirstname(), subscriber.getMiddlename(),
                        streetRus, subscriber.getAddresses().getHouse().getHouseNumber(),
                        subscriber.getAddresses().getHouse().getCorps(),
                        subscriber.getAddresses().getHouse().getLetter(), subscriber.getAddresses().getFlat());
                continue;
            }
            Address address = subscriber.getAddresses();

            House house = new House();
            house.setStreet(street);
            house.setHouseNumber(subscriber.getAddresses().getHouse().getHouseNumber());
            house.setCorps(subscriber.getAddresses().getHouse().getCorps());
            house.setLetter(subscriber.getAddresses().getHouse().getLetter());

            House houseOld = house;
            house = houseDao.find(house);
            if (house == null) {
                log.info("{};{};{};{};{};{};{};{};{};NOT FOUND",
                        subscriber.getLastname(), subscriber.getFirstname(), subscriber.getMiddlename(),
                        streetRus, houseOld.getHouseNumber(), houseOld.getCorps(), houseOld.getLetter(), address.getFlat(),
                        subscriber.getPhone().getCellPhone());
                continue;
            }

            address.setHouse(house);
            List<Address> addressList = addressDao.find(address);
            if (addressList.size() == 0) {
                log.info("{};{};{};{};{};{};{};{};{};NOT FOUND",
                        subscriber.getLastname(), subscriber.getFirstname(), subscriber.getMiddlename(),
                        streetRus, houseOld.getHouseNumber(), houseOld.getCorps(), houseOld.getLetter(), address.getFlat(),
                        subscriber.getPhone().getCellPhone());
                continue;
            }
            log.info("{};{};{};{};{};{};{};{};{};FOUND",
                    subscriber.getLastname(), subscriber.getFirstname(), subscriber.getMiddlename(),
                    streetRus, house.getHouseNumber(), house.getCorps(), house.getLetter(), address.getFlat(),
                    subscriber.getPhone().getCellPhone());

            for (Address addr : addressList) {
                Subscriber sub = addr.getSubscriber();
                log.info("{};{};{};{};{};{};{};{};{};FOUND MATCHING",
                        sub.getLastname(), sub.getFirstname(), sub.getMiddlename(),
                        streetUkr, addr.getHouse().getHouseNumber(), addr.getHouse().getCorps(),
                        addr.getHouse().getLetter(), addr.getFlat());
            }
        }

*/
        return true;

    }

}




















