package com.fredsonchaves07.domain.events;

import java.time.LocalDateTime;

public interface Event<T> {

    LocalDateTime dataTimeOcurred();

    T eventData();
}
