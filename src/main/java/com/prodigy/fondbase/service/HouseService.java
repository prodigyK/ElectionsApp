package com.prodigy.fondbase.service;

import com.prodigy.fondbase.model.House;


public interface HouseService {

    House get(int id);

    House save(House house);

    void delete(int id);

}
