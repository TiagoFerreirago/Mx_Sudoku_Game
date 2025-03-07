package br.com.mx.sudoku.service;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifierService implements EventListener{

    private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>(){{
        put(EventEnum.CLEAR_SPACE, new ArrayList<>());
    }};

    public void subscribe(final EventEnum eventType, EventListener listener){
        var selectedListeners = listeners.get(eventType);
        selectedListeners.add(listener);
    }

    public void notify(final EventEnum eventType){
        listeners.get(eventType).forEach(l -> {

            l.u
        }); 
    }

}
