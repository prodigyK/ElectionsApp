package com.prodigy.fondbase.dao.logging;

import com.prodigy.fondbase.model.logging.LoggingChanges;

public interface LoggingChangesDao {

    LoggingChanges get(int id);

    LoggingChanges save(LoggingChanges changes);
}
