package com.prodigy.fondbase.service.logging;

import com.prodigy.fondbase.model.logging.LoggingChanges;

public interface LoggingChangesService {

    LoggingChanges get(int id);

    LoggingChanges save(LoggingChanges changes);

}
