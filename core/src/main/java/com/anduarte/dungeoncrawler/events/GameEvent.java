package com.anduarte.dungeoncrawler.events;

/**
 * Representa um evento genérico que pode ser emitido durante o jogo.
 * Cada evento possui um tipo pré-definido através do enum Type.
 */
public class GameEvent {

    /**
     * Enumeração dos tipos possíveis de eventos que podem ocorrer no jogo.
     */
    public enum Type {
        ITEM_COLLECTED,
        PLAYER_HIT,
        DOOR_TRIGGERED,
        LEVEL_COMPLETED
    }

    private final Type type;

    /**
     * Construtor do GameEvent.
     * @param type Tipo do evento a ser criado
     */
    public GameEvent(Type type) {
        this.type = type;
    }

    /**
     * Devolve o tipo do evento.
     * @return Tipo do evento (ITEM_COLLECTED, PLAYER_HIT, etc.)
     */
    public Type getType() {
        return type;
    }
}
