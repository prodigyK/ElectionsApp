package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.RegionDao;
import com.prodigy.fondbase.model.Region;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class RegionDaoImpl extends EntityDaoImpl implements RegionDao {


}
