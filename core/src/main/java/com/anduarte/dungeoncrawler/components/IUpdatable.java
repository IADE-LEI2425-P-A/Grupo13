package com.anduarte.dungeoncrawler.components;

/**
 * Interface para componentes que devem ser atualizados a cada frame.
 */
public interface IUpdatable {
    /**
     * Método chamado a cada frame para atualizar o componente.
     *
     * @param deltaTime Tempo em segundos desde o último frame.
     */
    void update(float deltaTime);

    /**
     * Define se o componente deve ser atualizado neste frame.
     *
     * @return true se for para atualizar, false caso contrário.
     */
    boolean canUpdate();
}
