package com.prodigy.fondbase.service.impl;

import com.prodigy.fondbase.model.House;
import com.prodigy.fondbase.service.AbstractRootService;
import com.prodigy.fondbase.service.HouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseServiceImpl extends AbstractRootService implements HouseService {

    @Override
    public House get(int id) {
        return (House) houseDao.get(House.class, id);
    }

    @Override
    public House save(House house) {
        return (House) houseDao.save(house);
    }

    @Override
    public void delete(int id) {
        houseDao.delete(House.class, id);
    }
}
