package com.anduarte.dungeoncrawler.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma célula do mapa sob a forma de nó no grafo.
 * Cada nó conhece a sua posição no mundo (x, y), se é navegável ou não,
 * e os seus vizinhos (usado para pathfinding).
 */
public class Node {

    private final int x; // Posição X no mapa
    private final int y; // Posição Y no mapa
    private final boolean walkable; // Indica se o nó é navegável (sem parede)

    private final List<Node> neighbors = new ArrayList<>(); // Ligações a nós adjacentes

    /**
     * Construtor de um nó.
     *
     * @param x Coordenada X no mapa
     * @param y Coordenada Y no mapa
     * @param walkable True se for um espaço navegável; False se for parede
     */
    public Node(int x, int y, boolean walkable) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    /**
     * Adiciona um nó vizinho a este nó.
     *
     * @param neighbor Nó vizinho (cima, baixo, esquerda ou direita)
     */
    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ") " + (walkable ? "livre" : "parede");
    }
}
