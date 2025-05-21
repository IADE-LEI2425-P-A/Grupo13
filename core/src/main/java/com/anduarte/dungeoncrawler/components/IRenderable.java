package com.anduarte.dungeoncrawler.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interface para componentes que sabem desenhar-se no ecrã.
 */
public interface IRenderable {
    void render(SpriteBatch batch);
    boolean canRender();
}
