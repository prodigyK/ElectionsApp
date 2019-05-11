package com.prodigy.fondbase.service.logging;


import com.prodigy.fondbase.dao.logging.LoggingTypeDao;
import com.prodigy.fondbase.model.logging.LoggingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoggingTypeServiceImpl implements LoggingTypeService{

    @Autowired
    private LoggingTypeDao loggingTypeDao;

    @Override
    public LoggingType get(int id) {
        return loggingTypeDao.get(id);
    }

    @Override
    @Transactional
    public LoggingType save(LoggingType type) {
        return loggingTypeDao.save(type);
    }
}
