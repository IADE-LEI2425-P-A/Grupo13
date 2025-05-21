package com.anduarte.dungeoncrawler.components;

import com.anduarte.dungeoncrawler.entities.Entity;
import com.anduarte.dungeoncrawler.map.Graph;
import com.anduarte.dungeoncrawler.map.Node;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Componente responsável por armazenar e atualizar a posição de uma entidade.
 * Neste caso, a posição pode ser alterada manualmente com as setas do teclado,
 * mas esse controlo manual será automaticamente desativado se a entidade
 * estiver a seguir um caminho definido pelo sistema de pathfinding (A*).
 * Além disso, o movimento manual com setas só ocorre para células válidas (walkable).
 */
public class MovementComponent extends Component implements IUpdatable {

    private float x;
    private float y;
    private float speed = 200f; // Velocidade de movimento em pixels por segundo
    private Graph graph; // Referência ao grafo para validar movimento
    private final int tileSize = 32; // Tamanho de cada célula do mapa (em pixels)

    /**
     * Construtor que define a posição inicial.
     *
     * @param x posição X inicial
     * @param y posição Y inicial
     */
    public MovementComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Liga este componente ao grafo do mundo, necessário para validar colisões com paredes.
     *
     * @param graph Grafo de navegação (mapa com células walkable ou não)
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * Atualiza a posição da entidade com base nas setas do teclado,
     * desde que o jogador não esteja a seguir um caminho automático (A*).
     * Só move se a célula de destino for válida no mapa (walkable).
     *
     * @param deltaTime Tempo decorrido desde o último frame (em segundos)
     */
    @Override
    public void update(float deltaTime) {
        if (!canMoveManually() || graph == null || entity == null) return;

        int currentX = (int) (x / tileSize);
        int currentY = (int) (y / tileSize);

        // Cima (seta ↑)
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            tryMoveTo(currentX, currentY + 1, 0, 1, deltaTime);
        }

        // Baixo (seta ↓)
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            tryMoveTo(currentX, currentY - 1, 0, -1, deltaTime);
        }

        // Esquerda (seta ←)
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            tryMoveTo(currentX - 1, currentY, -1, 0, deltaTime);
        }

        // Direita (seta →)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            tryMoveTo(currentX + 1, currentY, 1, 0, deltaTime);
        }
    }

    /**
     * Tenta mover para uma nova célula, se for válida (walkable).
     *
     * @param targetX  Coordenada X da célula destino
     * @param targetY  Coordenada Y da célula destino
     * @param dirX     Direção horizontal do movimento (-1, 0 ou 1)
     * @param dirY     Direção vertical do movimento (-1, 0 ou 1)
     * @param deltaTime Tempo desde o último frame
     */
    private void tryMoveTo(int targetX, int targetY, int dirX, int dirY, float deltaTime) {
        Node node = graph.getNode(targetX, targetY);
        if (node != null && node.isWalkable()) {
            x += dirX * speed * deltaTime;
            y += dirY * speed * deltaTime;
        }
    }

    /**
     * Verifica se é permitido controlar o movimento manualmente.
     * Só retorna true se não estiver a seguir um caminho com A*.
     *
     * @return true se o jogador pode mover-se com as setas; false se estiver em movimento automático
     */
    private boolean canMoveManually() {
        if (entity == null) return true;

        PlayerPathComponent pathComponent = entity.getComponent(PlayerPathComponent.class);
        return pathComponent == null || pathComponent.isIdle();
    }

    /**
     * Define se o componente deve ser atualizado automaticamente.
     */
    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Obtém a posição atual X da entidade.
     */
    public float getX() {
        return x;
    }

    /**
     * Obtém a posição atual Y da entidade.
     */
    public float getY() {
        return y;
    }

    /**
     * Define a posição X da entidade (usado externamente, ex: por A*).
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Define a posição Y da entidade (usado externamente, ex: por A*).
     */
    public void setY(float y) {
        this.y = y;
    }
}
