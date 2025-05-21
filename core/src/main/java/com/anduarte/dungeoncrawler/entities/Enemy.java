package com.anduarte.dungeoncrawler.entities;

// Enum que representa os possíveis estados de um inimigo
enum EnemyState {
    IDLE,       // À espera
    CHASING,    // A perseguir o jogador
    ATTACKING,  // A atacar
    DEAD        // Morto
}

// Classe que representa um inimigo no jogo
public class Enemy {
    private String name;           // Nome do inimigo
    private int health = 50;       // Vida inicial
    private EnemyState state;      // Estado atual do inimigo

    // Construtor que define o nome e inicia o estado como IDLE
    public Enemy(String name) {
        this.name = name;
        this.state = EnemyState.IDLE;
    }

    // Método que atualiza o comportamento do inimigo com base no estado
    public void update() {
        switch (state) {
            case IDLE:
                System.out.println(name + " está à espera...");
                state = EnemyState.CHASING;
                break;

            case CHASING:
                System.out.println(name + " está a perseguir o jogador.");
                state = EnemyState.ATTACKING;
                break;

            case ATTACKING:
                System.out.println(name + " ataca o jogador!");
                health -= 10; // Perde vida ao atacar (simulação)
                if (health <= 0) {
                    state = EnemyState.DEAD;
                    System.out.println(name + " morreu.");
                } else {
                    state = EnemyState.IDLE;
                }
                break;

            case DEAD:
                // Inimigo morto não faz nada
                break;
        }
    }

    // Verifica se o inimigo ainda está vivo
    public boolean isAlive() {
        return state != EnemyState.DEAD;
    }
}
