package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.AbstractRootTest;
import com.prodigy.fondbase.model.City;
import com.prodigy.fondbase.model.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DistrictTest extends AbstractRootTest {

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private CityDao cityDao;


    @Test
    void saveTest() {
        int id = 1007;
        int cityId = 1001;
        District district = new District(id, "new");
        district.setCity((City)cityDao.get(City.class, cityId));
        District created = (District) districtDao.save(district);
        assertThat(created).isEqualTo(district);
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
        int id = 1002;
        District expected = new District(id, "Приморский");
        District actual = (District) districtDao.get(District.class, id);
        assertThat(actual).isEqualToIgnoringGivenFields(actual, "city").isEqualTo(expected);
    }

    @Test
    void getAll() {
    }
}