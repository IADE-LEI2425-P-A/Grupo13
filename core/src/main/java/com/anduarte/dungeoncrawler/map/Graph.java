package com.anduarte.dungeoncrawler.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa um grafo composto por vários nós do tipo Node.
 * Usado para armazenar e navegar o mapa do jogo (ex: para pathfinding).
 */
public class Graph {

    // Mapa com coordenadas (x,y) como chave e o Node correspondente como valor
    private final Map<String, Node> nodes = new HashMap<>();

    // Referência à posição inicial do jogador (símbolo 'P' no mapa)
    private Node playerStartNode;

    /**
     * Adiciona um novo nó ao grafo.
     *
     * @param node Nó a adicionar
     */
    public void addNode(Node node) {
        String key = getKey(node.getX(), node.getY());
        nodes.put(key, node);
    }

    /**
     * Obtém o nó na posição indicada (se existir).
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return Node correspondente ou null
     */
    public Node getNode(int x, int y) {
        return nodes.get(getKey(x, y));
    }

    /**
     * Permite iterar sobre todos os nós do grafo.
     * Útil para desenhar o mapa ou para depuração.
     *
     * @return Iterable com todos os nós armazenados
     */
    public Iterable<Node> getAllNodes() {
        return nodes.values();
    }

    /**
     * Constrói a chave de identificação do nó no mapa.
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return String no formato "x,y"
     */
    private String getKey(int x, int y) {
        return x + "," + y;
    }

    public Node getPlayerStartNode() {
        return playerStartNode;
    }

    public void setPlayerStartNode(Node playerStartNode) {
        this.playerStartNode = playerStartNode;
    }
}
