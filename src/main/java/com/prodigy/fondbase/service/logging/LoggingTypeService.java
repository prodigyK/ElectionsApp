package com.prodigy.fondbase.service.logging;

import com.prodigy.fondbase.model.logging.LoggingMain;
import com.prodigy.fondbase.model.logging.LoggingType;

public interface LoggingTypeService {

    LoggingType get(int id);

    LoggingType save(LoggingType type);

}
