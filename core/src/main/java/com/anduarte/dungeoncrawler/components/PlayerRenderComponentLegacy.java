/**
 * [LEGACY] Este componente foi utilizado numa versão inicial para desenhar o jogador,
 * antes da adoção do padrão ECS com ligação ao MovementComponent.
 * Substituído por PlayerComponent.java com IRenderable.
 */


package com.anduarte.dungeoncrawler.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Componente responsável por desenhar o jogador no ecrã.
 */
public class PlayerRenderComponentLegacy extends Component implements IRenderable {

    private Texture texture;
    private Vector2 position;
    private boolean visible = true;

    @Override
    public void start() {
        // Carrega a imagem do jogador (coloca o nome correto do ficheiro na pasta assets)
        texture = new Texture("player.png");  // substitui por um ficheiro que já tenhas
        position = new Vector2(100, 100);     // posição inicial arbitrária
    }

    @Override
    public void render(SpriteBatch batch) {
        System.out.println("Render do jogador chamado");
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public boolean canRender() {
        return visible;
    }

    // Podes ativar/desativar renderização dinamicamente
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // Atualiza a posição do sprite, se necessário
    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }
}
