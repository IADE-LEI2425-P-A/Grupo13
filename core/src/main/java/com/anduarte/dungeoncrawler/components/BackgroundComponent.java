package com.anduarte.dungeoncrawler.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Componente responsável por desenhar o fundo fixo do mundo.
 * Deve ser desenhado antes das entidades do jogo, como o jogador ou inimigos.
 */
public class BackgroundComponent extends Component implements IRenderable {

    private Texture backgroundTexture;

    /**
     * Construtor que recebe a textura do fundo a desenhar.
     *
     * @param texture Textura usada como fundo do mundo.
     */
    public BackgroundComponent(Texture texture) {
        this.backgroundTexture = texture;
    }

    /**
     * Desenha o fundo do jogo.
     * Este método é chamado a cada frame pelo sistema de renderização.
     *
     * @param batch SpriteBatch usado para desenhar texturas no ecrã.
     */
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(backgroundTexture, 0, 0);
    }

    /**
     * Indica se este componente deve ser renderizado.
     *
     * @return true para desenhar o fundo a cada frame.
     */
    @Override
    public boolean canRender() {
        return true;
    }
}
