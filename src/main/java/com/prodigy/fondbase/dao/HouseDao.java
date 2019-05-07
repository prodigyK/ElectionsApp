package com.prodigy.fondbase.dao;


import com.prodigy.fondbase.model.House;

public interface HouseDao<T extends House> extends EntityDao {

    T find(T house);
}
