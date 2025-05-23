package com.anduarte.dungeoncrawler;

import com.anduarte.dungeoncrawler.components.*;
import com.anduarte.dungeoncrawler.entities.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

/**
 * Classe principal da aplicação.
 * Usa a estrutura World para organizar entidades e gerir o ciclo de jogo.
 */
public class Main extends ApplicationAdapter {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private World world;

    /**
     * Método chamado no início da aplicação.
     * Cria o mundo, a câmara e adiciona as entidades iniciais (background, câmara).
     * O jogador já é criado automaticamente no World, com base no mapa.
     */
    @Override
    public void create() {
        // Inicializa o batch de renderização
        batch = new SpriteBatch();

        // Cria a câmara ortográfica com base no tamanho da janela
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Cria o mundo do jogo, passando a câmara
        world = new World(camera);

        // Cria e adiciona o background (plano de fundo)
        try {
            Texture backgroundTexture = new Texture(Gdx.files.internal("sprites/background.png")); // Garante que está em assets/sprites/
            Entity background = new Entity();
            background.addComponent(new BackgroundComponent(backgroundTexture));
            world.addEntityToBack(background); // Adiciona ao início da lista (renderizado primeiro)
        } catch (Exception e) {
            System.err.println("⚠ERRO ao carregar background.png (opcional): " + e.getMessage());
        }

        // Cria a entidade da câmara que segue o jogador
        Entity cameraEntity = new Entity();
        CameraComponent cameraComponent = new CameraComponent(camera);
        cameraComponent.setTarget(world.getPlayer()); // Aponta para o jogador criado pelo World
        cameraEntity.addComponent(cameraComponent);
        cameraEntity.addComponent(new CameraMovementComponent());
        world.addEntity(cameraEntity);

        // Inicializa todos os componentes das entidades
        world.start();
    }

    /**
     * Método chamado a cada frame.
     * Atualiza a lógica do jogo e desenha o mundo.
     */
    @Override
    public void render() {
        // Atualiza a câmara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Limpa o ecrã com uma cor escura azulada
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Atualiza o mundo e desenha tudo (inclui o batch.begin() / end() internamente)
        float deltaTime = Gdx.graphics.getDeltaTime();
        world.update(deltaTime);
        world.render(batch);
    }

    /**
     * Método chamado ao fechar o jogo.
     * Liberta recursos gráficos utilizados manualmente.
     */
    @Override
    public void dispose() {
        batch.dispose();
        // Adiciona aqui a libertação de outras texturas carregadas fora de componentes, se houver
    }
}
