package com.prodigy.fondbase.dao;

import com.prodigy.fondbase.model.Phone;

import java.util.List;

public interface PhoneDao<T extends Phone> extends EntityDao{

    List<Phone> getBySubscriberId(int subscriberId);
}