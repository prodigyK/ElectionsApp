package com.prodigy.fondbase.service.logging;

import com.prodigy.fondbase.dao.logging.LoggingChangesDao;
import com.prodigy.fondbase.model.logging.LoggingChanges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoggingChangesServiceImpll implements LoggingChangesService{

    @Autowired
    private LoggingChangesDao loggingChangesDao;

    @Override
    public LoggingChanges get(int id) {
        return loggingChangesDao.get(id);
    }

    @Override
    @Transactional
    public LoggingChanges save(LoggingChanges changes) {
        return loggingChangesDao.save(changes);
    }
}
