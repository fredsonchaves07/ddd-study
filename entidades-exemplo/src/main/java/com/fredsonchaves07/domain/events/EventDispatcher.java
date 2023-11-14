package com.fredsonchaves07.domain.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class EventDispatcher<T extends Event<?>> {

    private HashMap<String, List<EventHandler<T>>> eventHandlers = new LinkedHashMap<>();

    public void notify(Event<?> event) {

    }

    public void register(String eventName, EventHandler<T> eventHandler) {
        if (!this.eventHandlers.containsKey(eventName)) {
            this.eventHandlers.put(eventName, new ArrayList<>());
        }
        this.eventHandlers.get(eventName).add(eventHandler);
    }

    public void unregister(String eventName) {
        this.eventHandlers.remove(eventName);
    }

    public void unregisterAll() {

    }

    public List<String> getEventHandlers() {
        return eventHandlers.keySet().stream().toList();
    }
}
