package com.prodigy.fondbase.service;


import com.prodigy.fondbase.model.Region;
import java.util.List;

public interface RegionService {

    Region get(int id);

    Region save(Region region);

    List<Region> getAll();

    void delete(int id);

}
