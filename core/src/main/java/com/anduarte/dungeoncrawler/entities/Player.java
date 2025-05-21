package com.anduarte.dungeoncrawler.entities;

import com.anduarte.dungeoncrawler.enums.PlayerState;

// Classe que representa o jogador do jogo
public class Player {
    private String name;             // Nome do jogador
    private int health = 100;        // Vida inicial
    private PlayerState state;       // Estado atual do jogador

    // Construtor que define o nome e inicia o estado como IDLE
    public Player(String name) {
        this.name = name;
        this.state = PlayerState.IDLE;
    }

    // Método que atualiza o comportamento do jogador consoante o estado
    public void update() {
        System.out.println("🎮 Estado atual: " + state + " | Vida: " + healthBar());

        switch (state) {
            case IDLE:
                System.out.println(name + " está parado.");
                state = PlayerState.MOVING;  // Próximo estado
                break;
            case MOVING:
                System.out.println(name + " está a mover-se.");
                state = PlayerState.ATTACKING;
                break;
            case ATTACKING:
                System.out.println(name + " está a atacar!");
                health -= 5;  // Simula perda de vida ao atacar
                state = PlayerState.IDLE;
                break;
        }
    }

    // Método auxiliar para representar graficamente a barra de vida
    private String healthBar() {
        int total = 20; // Total de "blocos" visuais na barra
        int filled = (int) ((health / 100.0) * total);
        return "[" + "#".repeat(filled) + "-".repeat(total - filled) + "]";
    }
}
