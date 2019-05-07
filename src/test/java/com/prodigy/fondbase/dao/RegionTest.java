package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.AbstractRootTest;
import com.prodigy.fondbase.model.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RegionTest extends AbstractRootTest {

    @Autowired
    private RegionDao dao;

    @Test
    void saveTest() {
        Region region = (Region)dao.save(new Region(1003, "Ренийский"));
        assertTrue(dao.getAll(Region.class).size() == 3);
    }

    @Test
    void deleteConstraintViolationTest() {
        assertThrows(PersistenceException.class, () -> dao.delete(Region.class, 1001));
    }

    @Test
    void getTest() {
        Region region = (Region)dao.get(Region.class, 1001);
        assertTrue("Одесская".equals(region.getName()));
    }

    @Test
    void getAllTest() {
        List<Region> regions = dao.getAll(Region.class);
        assertTrue(regions.size() == 2);
    }

    @Test
    void updateTest(){
        String name = "Ренийский";
        Region region = (Region)dao.save(new Region(1002, name));
        assertTrue(name.equals(region.getName()));
    }
}