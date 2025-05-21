package com.anduarte.dungeoncrawler.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por gerir e propagar eventos para os ouvintes registados.
 * Implementa o padrão Singleton para garantir uma única instância global.
 */
public class EventManager {

    private static final EventManager instance = new EventManager();
    private final List<GameEventListener> listeners = new ArrayList<>();

    /**
     * Construtor privado para garantir padrão Singleton.
     */
    private EventManager() {
    }

    /**
     * Obtém a instância única do EventManager.
     * @return Instância do EventManager
     */
    public static EventManager getInstance() {
        return instance;
    }

    /**
     * Regista um ouvinte para começar a receber eventos.
     * @param listener Classe que implementa GameEventListener
     */
    public void registerListener(GameEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Emite um evento para todos os ouvintes registados.
     * @param event Evento a ser propagado
     */
    public void emit(GameEvent event) {
        for (GameEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
