package com.anduarte.dungeoncrawler.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Componente responsável por renderizar uma parede no mundo do jogo.
 * Cada parede é representada por uma textura estática.
 */
public class WallComponent extends Component implements IRenderable {

    private Texture texture;

    /**
     * Construtor da parede. Carrega a textura da parede.
     */
    public WallComponent() {
        // Carregar a textura da parede (ajusta o caminho se necessário)
        texture = new Texture("sprites/wall.png");
    }

    /**
     * Método chamado para desenhar a parede no ecrã.
     * @param batch SpriteBatch utilizado para desenhar texturas
     */
    @Override
    public void render(SpriteBatch batch) {
        if (entity == null) return;

        MovementComponent movement = entity.getComponent(MovementComponent.class);
        if (movement != null) {
            float x = movement.getX();
            float y = movement.getY();
            batch.draw(texture, x, y);
        }
    }

    /**
     * Indica se esta parede pode ser renderizada.
     * @return true se for para renderizar, false caso contrário
     */
    @Override
    public boolean canRender() {
        return true;
    }

    /**
     * Liberta os recursos utilizados por este componente.
     */

    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
