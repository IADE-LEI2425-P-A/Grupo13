
package com.anduarte.dungeoncrawler.factories;

import com.anduarte.dungeoncrawler.components.MovementComponent;
import com.anduarte.dungeoncrawler.components.RenderComponent;
import com.anduarte.dungeoncrawler.components.PlayerPathComponent;
import com.anduarte.dungeoncrawler.entities.PlayerEntity;
import com.badlogic.gdx.graphics.Texture;

/**
 * Fábrica de entidades do jogo.
 * Fornece métodos utilitários para criar entidades como jogador, inimigos, etc.
 */
public class EntityFactory {

    // Tamanho base para renderização de entidades (ex: igual ao tileSize)
    public static final int DEFAULT_ENTITY_SIZE = 32;

    /**
     * Cria e devolve uma entidade do tipo jogador (PlayerEntity),
     * já configurada com os componentes de movimento, renderização e pathfinding.
     *
     * @param x Posição X inicial do jogador (em pixels)
     * @param y Posição Y inicial do jogador (em pixels)
     * @return PlayerEntity pronta a ser adicionada ao mundo
     */
    public static PlayerEntity createPlayer(float x, float y) {
        PlayerEntity player = new PlayerEntity(x, y);

        // Substitui os componentes diretamente no construtor
        player.addComponent(new MovementComponent(x, y));
        player.addComponent(new RenderComponent(new Texture("player.png"), DEFAULT_ENTITY_SIZE, DEFAULT_ENTITY_SIZE));
        player.addComponent(new PlayerPathComponent());

        return player;
    }
}
