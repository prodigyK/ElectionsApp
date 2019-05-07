package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.AbstractRootTest;
import com.prodigy.fondbase.model.City;
import com.prodigy.fondbase.model.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CityTest extends AbstractRootTest {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RegionDao regionDao;

    @Test
    void saveTest() {
        int cityId = 1003;
        int regionId = 1001;
        City expected = new City(cityId, "New");
        expected.setRegion((Region)regionDao.get(Region.class, regionId));
        expected = (City)cityDao.save(expected);
        City actual = (City)cityDao.get(City.class, cityId);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void deleteConstraintViolationTest() {
        assertThrows(PersistenceException.class, () -> cityDao.delete(City.class, 1001));
    }

    @Test
    void getTest() {
        City city = (City)cityDao.get(City.class, 1001);
        assertTrue("Одесса".equals(city.getName()));
    }

    @Test
    void getAllTest() {
        assertTrue(cityDao.getAll(City.class).size() == 2);
    }

    @Test
    void updateTest() {
        int id = 1001;
        City existed = new City(id, "Рени");
        existed.setRegion((Region)regionDao.get(Region.class, id));
        City updated = (City)cityDao.save(existed);
        assertThat(updated).isEqualTo(existed);
    }
}