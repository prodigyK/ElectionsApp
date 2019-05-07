package com.prodigy.fondbase.dao.impl;

import com.prodigy.fondbase.dao.AreaDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class AreaDaoImpl extends EntityDaoImpl implements AreaDao {
}
