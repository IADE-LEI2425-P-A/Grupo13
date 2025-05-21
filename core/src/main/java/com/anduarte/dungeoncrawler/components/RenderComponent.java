package com.anduarte.dungeoncrawler.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.anduarte.dungeoncrawler.entities.Entity;

/**
 * Componente responsável por desenhar graficamente a entidade no ecrã.
 *
 * Aplica o princípio de separação entre lógica (posição) e visualização (imagem).
 * Este componente desenha uma imagem (Texture) redimensionada conforme necessário.
 * A posição é obtida através do MovementComponent.
 */
public class RenderComponent extends Component implements IRenderable {

    private Texture texture;

    // Largura e altura desejadas da imagem no ecrã
    private int width = 32;
    private int height = 32;

    /**
     * Construtor padrão: usa a imagem completa e desenha com tamanho 32x32.
     *
     * @param texture a imagem a ser desenhada
     */
    public RenderComponent(Texture texture) {
        this.texture = texture;
    }

    /**
     * Construtor personalizado: permite definir manualmente o tamanho de renderização.
     *
     * @param texture a imagem a ser desenhada
     * @param width   largura desejada no ecrã (em píxeis)
     * @param height  altura desejada no ecrã (em píxeis)
     */
    public RenderComponent(Texture texture, int width, int height) {
        this.texture = texture;
        this.width = width;
        this.height = height;
    }

    /**
     * Desenha a imagem da entidade no ecrã, redimensionada conforme o tamanho definido.
     *
     * @param batch SpriteBatch utilizado para desenhar
     */
    @Override
    public void render(SpriteBatch batch) {
        if (entity == null || texture == null) return;

        MovementComponent movement = entity.getComponent(MovementComponent.class);
        if (movement != null) {
            float x = movement.getX();
            float y = movement.getY();
            batch.draw(texture, x, y, width, height);
        }
    }

    /**
     * Indica se o componente deve ser desenhado no ecrã.
     * Só renderiza se a textura estiver carregada com sucesso.
     *
     * @return true se deve ser renderizado; false caso contrário
     */
    @Override
    public boolean canRender() {
        return texture != null;
    }

    /**
     * Associa este componente à entidade a que pertence.
     *
     * @param entity A entidade que contém este componente
     */
    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
