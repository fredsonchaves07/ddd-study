package com.fredsonchaves07.domain.events;

public interface EventHandler<T extends Event<?>> {

    void handler(Event<T> event);
}
