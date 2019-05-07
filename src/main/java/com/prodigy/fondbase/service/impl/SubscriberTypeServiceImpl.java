package com.prodigy.fondbase.service.impl;

import com.prodigy.fondbase.model.SubscriberType;
import com.prodigy.fondbase.service.SubscriberTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriberTypeServiceImpl implements SubscriberTypeService {

    @Autowired
    private SubscriberTypeService subscriberTypeService;

    @Override
    public SubscriberType get(int id) {
        return subscriberTypeService.get(id);
    }
}
