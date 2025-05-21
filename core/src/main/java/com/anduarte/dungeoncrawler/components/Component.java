package com.anduarte.dungeoncrawler.components;

import com.anduarte.dungeoncrawler.entities.Entity;

/**
 * Classe abstrata base para todos os componentes do sistema ECS.
 *
 * Cada componente representa uma funcionalidade isolada (ex: movimento, renderização, colisão),
 * e é associado a uma entidade. Esta classe fornece métodos que podem ser sobrescritos
 * para definir comportamento específico ao iniciar o componente.
 */
public abstract class Component {

    /**
     * Referência à entidade que possui este componente.
     * Protegida para ser acessível pelas subclasses.
     */
    protected Entity entity;

    /**
     * Método chamado para associar o componente à entidade.
     * Necessário para comunicação entre componentes.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Método chamado uma vez no início da aplicação.
     * Pode ser sobrescrito por subclasses para inicialização específica.
     */
    public void start() {}
}
