package com.prodigy.fondbase.service.logging;

import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.model.logging.LoggingMain;

import java.time.LocalDate;
import java.util.List;

public interface LoggingMainService {

    LoggingMain get(int id);

    LoggingMain save(LoggingMain main);

    boolean compareAndSave(Subscriber previousSubscriber, Subscriber newSubscriber) throws Exception;

    boolean newSave(Subscriber newSubscriber);

    List<LoggingMain> getAllForToday();

    List<LoggingMain> getByUserBetweenDates(Integer userId, LocalDate from, LocalDate to);

}
