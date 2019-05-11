package com.prodigy.fondbase.dao.logging;

import com.prodigy.fondbase.model.logging.LoggingType;

public interface LoggingTypeDao {

    LoggingType get(int id);

    LoggingType save(LoggingType type);


}
