package com.anduarte.dungeoncrawler.events;

/**
 * Interface que deve ser implementada por todas as classes que desejam
 * escutar eventos emitidos no jogo.
 */
public interface GameEventListener {

    /**
     * Método chamado automaticamente sempre que um evento é emitido.
     * @param event Objeto GameEvent que foi emitido
     */
    void onEvent(GameEvent event);
}
