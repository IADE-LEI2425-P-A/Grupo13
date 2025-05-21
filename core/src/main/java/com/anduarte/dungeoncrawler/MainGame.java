// NOTA IMPORTANTE:
// Este ficheiro foi usado para simulações em consola no início do projeto (antes de usar libGDX).
// Atualmente, o ponto de entrada visual e funcional do jogo é o ficheiro Main.java,
// que utiliza o motor gráfico libGDX com o padrão Entity-Component-System (ECS).
// Este ficheiro vai ser mantido apenas como referência de lógica antiga ou removido se necessário.


package com.anduarte.dungeoncrawler;
import com.anduarte.dungeoncrawler.entities.Player;
import com.anduarte.dungeoncrawler.entities.Enemy;


// MainGame.java - ponto de entrada do jogo
public class MainGame {

    // Instância do jogador
    static Player player;

    // Array de inimigos (para este exemplo usamos 3)
    static Enemy[] enemies;

    // Método principal - ponto de arranque do jogo
    public static void main(String[] args) {

        // Inicializa o jogador com o nome "André"
        player = new Player("André");

        // Cria e inicializa 3 inimigos com nomes diferentes
        enemies = new Enemy[3];
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy("Inimigo" + (i + 1));
        }

        // Simula um ciclo de jogo com 10 "frames"
        for (int frame = 0; frame < 10; frame++) {
            System.out.println("--- Frame " + frame + " ---");

            // Atualiza o estado do jogador
            player.update();

            // Atualiza o estado de cada inimigo, se estiver vivo
            for (Enemy enemy : enemies) {
                if (!enemy.isAlive()) continue; // Ignora inimigos mortos
                enemy.update();
            }
        }
    }
}
