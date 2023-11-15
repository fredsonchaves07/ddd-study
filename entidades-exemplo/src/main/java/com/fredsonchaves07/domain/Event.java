package com.fredsonchaves07.domain;

import java.time.LocalDateTime;

public interface Event<T> {

    LocalDateTime dataTimeOcurred();

    T eventData();

    String getEventName();
}
