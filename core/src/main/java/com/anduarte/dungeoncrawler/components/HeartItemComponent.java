package com.anduarte.dungeoncrawler.components;

import com.anduarte.dungeoncrawler.events.EventManager;
import com.anduarte.dungeoncrawler.events.GameEvent;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Componente que representa um item de coração recolhível no mundo do jogo.
 * Quando o jogador colide com esta entidade, o item é recolhido
 * e lança um evento do tipo ITEM_COLLECTED.
 */
public class HeartItemComponent extends Component implements IRenderable {

    private final Texture texture;
    private boolean collected = false;

    /**
     * Construtor do item. Carrega a textura do coração.
     */
    public HeartItemComponent() {
        texture = new Texture("sprites/heart.png"); // Garante que este ficheiro existe
    }

    /**
     * Renderiza o coração no mundo se ainda não tiver sido recolhido.
     * @param batch SpriteBatch utilizado para desenhar
     */
    @Override
    public void render(SpriteBatch batch) {
        if (collected || entity == null) return;

        MovementComponent move = entity.getComponent(MovementComponent.class);
        if (move != null) {
            batch.draw(texture, move.getX(), move.getY(), 32, 32);
        }
    }

    /**
     * Indica se o coração deve ser renderizado no mundo.
     * @return true se ainda não foi recolhido, false caso contrário
     */
    @Override
    public boolean canRender() {
        return !collected;
    }

    /**
     * Marca o item como recolhido e emite o evento ITEM_COLLECTED.
     * Este método é chamado quando há colisão com o jogador.
     */
    public void collect() {
        if (!collected) {
            collected = true;
            EventManager.getInstance().emit(new GameEvent(GameEvent.Type.ITEM_COLLECTED));
        }
    }

    /**
     * Liberta a textura da memória.
     */
    public void dispose() {
        texture.dispose();
    }
}
