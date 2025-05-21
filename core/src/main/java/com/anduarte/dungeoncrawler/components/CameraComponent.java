package com.anduarte.dungeoncrawler.components;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.anduarte.dungeoncrawler.entities.Entity;

/**
 * Componente que permite associar uma câmara a uma entidade.
 * A câmara segue automaticamente a posição de outra entidade alvo.
 * Normalmente usada para seguir o jogador.
 */
public class CameraComponent extends Component implements IUpdatable {

    private OrthographicCamera camera;
    private Entity targetToFollow;

    /**
     * Construtor que recebe a câmara a ser controlada.
     *
     * @param camera Instância da OrthographicCamera usada no jogo.
     */
    public CameraComponent(OrthographicCamera camera) {
        this.camera = camera;
    }

    /**
     * Define qual entidade deve ser seguida pela câmara.
     * Normalmente usada para seguir o jogador.
     *
     * @param target Entidade a ser seguida (ex: player).
     */
    public void setTarget(Entity target) {
        this.targetToFollow = target;
    }

    /**
     * Atualiza a posição da câmara com base na entidade seguida.
     * Este método é chamado a cada frame pelo sistema de jogo.
     *
     * @param deltaTime Tempo decorrido desde o último frame (em segundos).
     */
    @Override
    public void update(float deltaTime) {
        if (targetToFollow != null) {
            MovementComponent movement = targetToFollow.getComponent(MovementComponent.class);
            if (movement != null) {

                // Versão ajustada: centra a câmara no meio da textura do jogador (assumindo 32x32 pixels)
                camera.position.set(
                    movement.getX() + 16,
                    movement.getY() + 16,
                    0
                );

                /*
                // Versão original (sem ajuste de centro):
                // A câmara segue o canto inferior esquerdo da textura do jogador
                camera.position.set(movement.getX(), movement.getY(), 0);
                */

                camera.update();
            }
        }
    }

    /**
     * Indica se este componente deve ser atualizado no ciclo de jogo.
     *
     * @return true para permitir atualização.
     */
    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Devolve a instância da câmara associada a este componente.
     *
     * @return OrthographicCamera usada para projeção do mundo.
     */
    public OrthographicCamera getCamera() {
        return camera;
    }
}
