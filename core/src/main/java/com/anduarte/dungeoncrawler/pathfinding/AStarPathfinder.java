package com.anduarte.dungeoncrawler.pathfinding;

import com.anduarte.dungeoncrawler.map.Graph;
import com.anduarte.dungeoncrawler.map.Node;

import java.util.*;

/**
 * Classe que implementa o algoritmo A* (A-Star) para encontrar o caminho mais curto entre dois nós do grafo.
 */
public class AStarPathfinder {

    /**
     * Calcula o caminho mais curto entre dois nós usando A*.
     *
     * @param graph Grafo onde estão os nós
     * @param start Nó de início
     * @param goal  Nó de destino
     * @return Lista de nós representando o caminho do início até ao fim (inclusive)
     */
    public List<Node> findPath(Graph graph, Node start, Node goal) {
        Set<Node> closedSet = new HashSet<>();
        Set<Node> openSet = new HashSet<>();
        openSet.add(start);

        Map<Node, Node> cameFrom = new HashMap<>();

        Map<Node, Float> gScore = new HashMap<>();
        gScore.put(start, 0f);

        Map<Node, Float> fScore = new HashMap<>();
        fScore.put(start, heuristic(start, goal));

        while (!openSet.isEmpty()) {
            Node current = getLowestFScore(openSet, fScore);

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Node neighbor : current.getNeighbors()) {
                if (closedSet.contains(neighbor)) continue;

                float tentativeG = gScore.getOrDefault(current, Float.MAX_VALUE) + 1;

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeG >= gScore.getOrDefault(neighbor, Float.MAX_VALUE)) {
                    continue;
                }

                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentativeG);
                fScore.put(neighbor, tentativeG + heuristic(neighbor, goal));
            }
        }

        return new ArrayList<>(); // Nenhum caminho encontrado
    }

    private float heuristic(Node a, Node b) {
        // Distância de Manhattan
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private Node getLowestFScore(Set<Node> openSet, Map<Node, Float> fScore) {
        Node lowest = null;
        float lowestScore = Float.MAX_VALUE;
        for (Node node : openSet) {
            float score = fScore.getOrDefault(node, Float.MAX_VALUE);
            if (score < lowestScore) {
                lowest = node;
                lowestScore = score;
            }
        }
        return lowest;
    }

    private List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
        List<Node> totalPath = new ArrayList<>();
        totalPath.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }
        return totalPath;
    }
}
