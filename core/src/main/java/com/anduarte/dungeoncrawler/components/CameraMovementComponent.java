package com.anduarte.dungeoncrawler.components;

import com.anduarte.dungeoncrawler.entities.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Componente responsável pelo movimento da câmara com as teclas de seta.
 */
public class CameraMovementComponent extends Component implements IUpdatable {

    private OrthographicCamera camera;

    /**
     * Método chamado uma vez, quando a entidade é inicializada.
     * Obtém o componente de câmara da entidade associada.
     */
    @Override
    public void start() {
        CameraComponent cameraComponent = entity.getComponent(CameraComponent.class);
        if (cameraComponent != null) {
            camera = cameraComponent.getCamera();
        }
    }

    /**
     * Atualiza a posição da câmara com base nas teclas pressionadas.
     */
    @Override
    public void update(float deltaTime) {
        if (camera == null) return;

        float speed = 300 * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) camera.translate(speed, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  camera.translate(-speed, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))    camera.translate(0, speed);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  camera.translate(0, -speed);
    }

    /**
     * Indica se este componente deve ser atualizado neste frame.
     * Neste caso, está sempre ativo.
     */
    @Override
    public boolean canUpdate() {
        return true;
    }
}
