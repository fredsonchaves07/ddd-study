package com.fredsonchaves07.domain;

public interface EventHandler<T extends Event> {

    void handler(Event event);
}
