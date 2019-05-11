package com.prodigy.fondbase.dao.logging;

import com.prodigy.fondbase.model.logging.LoggingMain;

public interface LoggingMainDao {

    LoggingMain get(int id);

    LoggingMain save(LoggingMain main);
}
