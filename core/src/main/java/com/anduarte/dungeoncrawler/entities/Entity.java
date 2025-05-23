package com.anduarte.dungeoncrawler.entities;

import com.anduarte.dungeoncrawler.components.Component;
import com.anduarte.dungeoncrawler.components.IUpdatable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.anduarte.dungeoncrawler.components.IRenderable;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma entidade no jogo.
 * Uma entidade pode conter múltiplos componentes responsáveis por comportamento e visual.
 */
public class Entity {

    private final List<Component> components = new ArrayList<>();

    /**
     * Adiciona um componente à entidade e associa esta entidade ao mesmo.
     *
     * @param component Componente a adicionar
     */
    public void addComponent(Component component) {
        component.setEntity(this);
        components.add(component);
        System.out.println("Adicionado: " + component.getClass().getSimpleName());

        // Regista o componente se for atualizável ou renderizável
        if (component instanceof IUpdatable updatable && updatable.canUpdate()) {
            System.out.println("Registado como Updatable");
        }

        if (component instanceof IRenderable renderable && renderable.canRender()) {
            System.out.println("Registado como Renderable");
        }
    }

    /**
     * Inicializa todos os componentes desta entidade.
     */
    public void start() {
        for (Component component : components) {
            component.start();
        }
    }

    /**
     * Atualiza todos os componentes que implementam IUpdatable.
     *
     * @param deltaTime Tempo decorrido desde o último frame
     */
    public void update(float deltaTime) {
        for (Component component : components) {
            if (component instanceof IUpdatable updatable && updatable.canUpdate()) {
                updatable.update(deltaTime);
            }
        }
    }

    /**
     * Renderiza todos os componentes que implementam IRenderable.
     *
     * @param batch SpriteBatch utilizado para desenhar
     */
    public void render(SpriteBatch batch) {
        for (Component component : components) {
            if (component instanceof IRenderable renderable && renderable.canRender()) {
                renderable.render(batch);
            }
        }
    }

    /**
     * Devolve um componente específico da entidade, com base na classe.
     *
     * @param componentClass Classe do componente pretendido
     * @return O componente correspondente, ou null se não existir
     */
    public <T> T getComponent(Class<T> componentClass) {
        //System.out.println("Método getComponent() foi chamado!");
        for (Component component : components) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }
        return null;
    }
}
