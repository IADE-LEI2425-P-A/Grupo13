package com.anduarte.dungeoncrawler;

import com.anduarte.dungeoncrawler.components.HeartItemComponent;
import com.anduarte.dungeoncrawler.components.HealthComponent;
import com.anduarte.dungeoncrawler.components.MovementComponent;
import com.anduarte.dungeoncrawler.components.PlayerPathComponent;
import com.anduarte.dungeoncrawler.entities.Entity;
import com.anduarte.dungeoncrawler.entities.PlayerEntity;
import com.anduarte.dungeoncrawler.events.EventManager;
import com.anduarte.dungeoncrawler.events.GameEvent;
import com.anduarte.dungeoncrawler.map.Graph;
import com.anduarte.dungeoncrawler.map.MapParser;
import com.anduarte.dungeoncrawler.map.Node;
import com.anduarte.dungeoncrawler.pathfinding.AStarPathfinder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe World representa o mundo do jogo.
 * Responsável por carregar o mapa, atualizar entidades e renderizar o jogo.
 */
public class World {

    private final List<Entity> entities = new ArrayList<>();
    private final MapParser parser;
    private final Graph graph;
    private final int tileSize = 32;

    private final OrthographicCamera camera;
    private final PlayerEntity player;
    private final PlayerPathComponent pathComponent;
    private final AStarPathfinder pathfinder = new AStarPathfinder();

    // Texturas da barra de vida (cheio e vazio)
    private final Texture heartFull = new Texture(Gdx.files.internal("sprites/heart_full.png"));
    private final Texture heartEmpty = new Texture(Gdx.files.internal("sprites/heart_empty.png"));

    /**
     * Construtor da classe World. Carrega o mapa, cria o jogador e entidades base.
     */
    public World(OrthographicCamera camera) {
        this.camera = camera;
        this.parser = new MapParser();
        this.graph = parser.loadMap("maps/map.txt", this);

        // Posição inicial do jogador a partir do mapa
        Node startNode = graph.getPlayerStartNode();
        if (startNode != null) {
            float playerX = startNode.getX() * tileSize;
            float playerY = startNode.getY() * tileSize;

            this.player = new PlayerEntity(playerX, playerY);
            this.pathComponent = player.getComponent(PlayerPathComponent.class);
            addEntity(player);

            MovementComponent movement = player.getComponent(MovementComponent.class);
            if (movement != null) {
                movement.setGraph(graph);
            }
        } else {
            throw new IllegalStateException("Mapa inválido: sem posição 'P' para o jogador.");
        }

        // Cria um coração no mapa (posição fixa para teste)
        Entity heart = new Entity();
        heart.addComponent(new MovementComponent(5 * tileSize, 5 * tileSize));
        heart.addComponent(new HeartItemComponent());
        addEntity(heart);

        // Listener para eventos de recolha de item
        EventManager.getInstance().registerListener(event -> {
            if (event.getType() == GameEvent.Type.ITEM_COLLECTED) {
                HealthComponent health = player.getComponent(HealthComponent.class);
                if (health != null) {
                    health.increaseHealth();
                    System.out.println("Coração apanhado! Vida atual: " + health.getCurrentHealth());
                }
            }
        });
    }

    /**
     * Adiciona uma nova entidade ao fim da lista.
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Adiciona uma entidade ao início da lista (renderizada no fundo).
     */
    public void addEntityToBack(Entity entity) {
        entities.add(0, entity);
    }

    /**
     * Inicializa todas as entidades do mundo.
     */
    public void start() {
        for (Entity entity : entities) {
            entity.start();
        }
    }

    /**
     * Atualiza todas as entidades e processa cliques do rato.
     *
     * @param deltaTime tempo decorrido desde o último frame
     */
    public void update(float deltaTime) {
        // Clique do rato → movimenta jogador com A*
        if (Gdx.input.justTouched()) {
            Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(click);

            int cellX = (int) (click.x / tileSize);
            int cellY = (int) (click.y / tileSize);

            MovementComponent movement = player.getComponent(MovementComponent.class);
            if (movement != null) {
                Node start = graph.getNode((int) (movement.getX() / tileSize), (int) (movement.getY() / tileSize));
                Node goal = graph.getNode(cellX, cellY);

                if (start != null && goal != null && goal.isWalkable()) {
                    pathComponent.setPath(pathfinder.findPath(graph, start, goal));
                }
            }
        }

        // Atualiza lógica das entidades
        for (Entity entity : entities) {
            entity.update(deltaTime);
        }

        // Verifica colisões com itens (ex: corações)
        for (Entity entity : entities) {
            HeartItemComponent heart = entity.getComponent(HeartItemComponent.class);
            if (heart != null && heart.canRender()) {
                MovementComponent heartMove = entity.getComponent(MovementComponent.class);
                MovementComponent playerMove = player.getComponent(MovementComponent.class);

                if (heartMove != null && playerMove != null) {
                    float dx = Math.abs(heartMove.getX() - playerMove.getX());
                    float dy = Math.abs(heartMove.getY() - playerMove.getY());

                    if (dx < 32 && dy < 32) {
                        heart.collect();
                    }
                }
            }
        }
    }

    /**
     * Renderiza o mundo e a barra de vida acima do jogador.
     */
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Renderiza todas as entidades
        for (Entity entity : entities) {
            entity.render(batch);
        }

        // Renderiza a barra de vida acima do jogador
        HealthComponent health = player.getComponent(HealthComponent.class);
        MovementComponent move = player.getComponent(MovementComponent.class);

        if (health != null && move != null) {
            int spacing = 36;
            float startX = move.getX() - (spacing * health.getMaxHealth() / 2f) + 16;
            float startY = move.getY() + 42;

            for (int i = 0; i < health.getMaxHealth(); i++) {
                Texture icon = (i < health.getCurrentHealth()) ? heartFull : heartEmpty;
                batch.draw(icon, startX + (i * spacing), startY, 32, 32);
            }
        }

        batch.end();
    }

    /**
     * Devolve o grafo do mundo atual.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Devolve a entidade principal do jogador.
     */
    public PlayerEntity getPlayer() {
        return player;
    }
}
