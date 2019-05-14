package com.prodigy.fondbase.dao.logging;

import com.prodigy.fondbase.model.logging.LoggingMain;

import java.time.LocalDateTime;
import java.util.List;

public interface LoggingMainDao {

    LoggingMain get(int id);

    LoggingMain save(LoggingMain main);

    List<LoggingMain> getAllForToday();
    
    List<LoggingMain> getByUserBetweenDates(int userId, LocalDateTime from, LocalDateTime to);

    List<LoggingMain> getBetweenDates(LocalDateTime from, LocalDateTime to);
}
