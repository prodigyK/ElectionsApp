package com.prodigy.fondbase.service;

import com.prodigy.fondbase.model.House;
import com.prodigy.fondbase.model.Subscriber;

import java.util.List;
import java.util.Map;


public interface DownloadService {

    Subscriber save(Subscriber subscriber);

    boolean saveBatch(List<Subscriber> subscribers);

    Subscriber get(int id);

    boolean analizeData(Map<String, String> streets, List<Subscriber> subscribers);
}
