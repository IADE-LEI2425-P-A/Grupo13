package com.anduarte.dungeoncrawler.components;

import com.anduarte.dungeoncrawler.entities.Entity;
import com.anduarte.dungeoncrawler.map.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Componente responsável por mover automaticamente a entidade ao longo de um caminho (lista de nós do grafo).
 * Este componente é usado pelo jogador quando se clica no mapa para definir um destino com pathfinding (A*).
 * Também informa o MovementComponent quando o caminho está ativo ou concluído.
 */
public class PlayerPathComponent extends Component implements IUpdatable {

    private final float speed = 100f; // Velocidade de movimento (pixels por segundo)
    private final LinkedList<Node> path = new LinkedList<>();

    /**
     * Define um novo caminho a ser seguido pela entidade.
     * Substitui qualquer caminho anterior que ainda não tenha terminado.
     *
     * @param newPath Lista de nós do grafo a percorrer
     */
    public void setPath(List<Node> newPath) {
        path.clear();
        path.addAll(newPath);
    }

    /**
     * Verifica se a entidade está parada (sem caminho a seguir).
     *
     * @return true se não há caminho ativo; false se estiver em movimento automático
     */
    public boolean isIdle() {
        return path.isEmpty();
    }

    /**
     * Atualiza a posição da entidade em direção ao próximo nó do caminho.
     * O movimento é contínuo e interpolado com base na velocidade e tempo.
     *
     * @param deltaTime Tempo decorrido desde o último frame (em segundos)
     */
    @Override
    public void update(float deltaTime) {
        if (path.isEmpty()) return;

        MovementComponent movement = entity.getComponent(MovementComponent.class);
        if (movement == null) return;

        Node target = path.peek(); // próximo nó de destino
        float tx = target.getX() * 32;
        float ty = target.getY() * 32;

        float dx = tx - movement.getX();
        float dy = ty - movement.getY();
        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        if (dist < speed * deltaTime) {
            // Chegou ao nó de destino, avança para o próximo
            movement.setX(tx);
            movement.setY(ty);
            path.poll(); // remove o nó já alcançado
        } else {
            // Move-se em direção ao nó com velocidade constante
            float dirX = dx / dist;
            float dirY = dy / dist;
            movement.setX(movement.getX() + dirX * speed * deltaTime);
            movement.setY(movement.getY() + dirY * speed * deltaTime);
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }
}
