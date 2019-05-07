package com.prodigy.fondbase.dao.commission;

import com.prodigy.fondbase.model.commission.Election;

import java.util.List;

public interface ElectionDao {

    Election save(Election election);

    Election get(int id);

    List<Election> getAll();

}
