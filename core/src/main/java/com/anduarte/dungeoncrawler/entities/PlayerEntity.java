package com.anduarte.dungeoncrawler.entities;

import com.anduarte.dungeoncrawler.components.HealthComponent;
import com.anduarte.dungeoncrawler.components.MovementComponent;
import com.anduarte.dungeoncrawler.components.PlayerPathComponent;
import com.anduarte.dungeoncrawler.components.RenderComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Representa o jogador no mundo do jogo.
 * Inclui movimento, renderização, pathfinding e gestão de vida.
 */
public class PlayerEntity extends Entity {

    /**
     * Construtor base do jogador.
     *
     * @param x Posição inicial em X
     * @param y Posição inicial em Y
     */
    public PlayerEntity(float x, float y) {
        // Movimento inicial
        addComponent(new MovementComponent(x, y));

        // Sprite do jogador (protegido contra erro de ficheiro em falta)
        try {
            Texture playerTexture = new Texture(Gdx.files.internal("sprites/player.png"));
            addComponent(new RenderComponent(playerTexture));
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR 'sprites/player.png': " + e.getMessage());
        }

        // Pathfinding
        addComponent(new PlayerPathComponent());

        // Vida com cenário de teste:
        // Começa com 5 de vida máxima, mas 4 de vida atual
        HealthComponent health = new HealthComponent(5);
        health.decreaseHealth(); // Fica com 4/5
        addComponent(health);
    }
}
