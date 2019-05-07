package com.prodigy.fondbase.service.commission;

import com.prodigy.fondbase.model.commission.Election;

import java.util.List;

public interface ElectionService {

    Election save(Election election);

    Election get(int id);

    List<Election> getAll();

    void enable(int id, boolean enabled);
}
