package com.anduarte.dungeoncrawler.map;

import com.anduarte.dungeoncrawler.World;
import com.anduarte.dungeoncrawler.components.MovementComponent;
import com.anduarte.dungeoncrawler.components.WallComponent;
import com.anduarte.dungeoncrawler.entities.Entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por carregar o mapa do jogo a partir de um ficheiro de texto
 * e construir a estrutura de dados correspondente (grafo).
 */
public class MapParser {

    /**
     * Lê um ficheiro de mapa e gera a estrutura de grafo (Graph) correspondente.
     * Cada célula válida do ficheiro será convertida num nó (Node) com ligações
     * aos seus vizinhos (cima, baixo, esquerda, direita).
     *
     * Além disso, adiciona entidades reais de parede no mundo ao encontrar '#'.
     *
     * @param filePath Caminho explícito do ficheiro do mapa (ex: "maps/map.txt")
     * @param world Referência ao mundo atual (para adicionar entidades visuais como paredes)
     * @return Objeto Graph com todos os nós e ligações
     */
    public Graph loadMap(String filePath, World world) {
        Graph graph = new Graph();
        List<String> lines = new ArrayList<>();

        // Lê o ficheiro linha a linha para uma lista
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
            ClassLoader.getSystemResourceAsStream(filePath)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (Exception e) {
            System.err.println("Erro ao ler o mapa: " + e.getMessage());
            return graph; // Retorna grafo vazio em caso de erro
        }

        int height = lines.size();
        int width = lines.get(0).length();

        // 1ª Passagem: Criar todos os nós e entidades do mapa
        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                char symbol = line.charAt(x);
                int posX = x;
                int posY = height - 1 - y; // Inverter Y para coordenadas gráficas

                boolean walkable = (symbol == ' ' || symbol == 'P');
                Node node = new Node(posX, posY, walkable);

                if (symbol == 'P') {
                    graph.setPlayerStartNode(node);
                }

                // Criar entidade de parede quando encontra '#'
                if (symbol == '#') {
                    Entity wall = new Entity();
                    wall.addComponent(new MovementComponent(posX * 32, posY * 32)); // Cada tile = 32px
                    wall.addComponent(new WallComponent());
                    world.addEntity(wall);
                }

                graph.addNode(node);
            }
        }

        // 2ª Passagem: Ligar vizinhos (cima, baixo, esquerda, direita)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node node = graph.getNode(x, height - 1 - y);
                if (node == null || !node.isWalkable()) continue;

                for (int[] offset : new int[][]{
                    {0, 1}, {0, -1}, {-1, 0}, {1, 0} // cima, baixo, esquerda, direita
                }) {
                    int nx = x + offset[0];
                    int ny = y + offset[1];
                    Node neighbor = graph.getNode(nx, height - 1 - ny);
                    if (neighbor != null && neighbor.isWalkable()) {
                        node.addNeighbor(neighbor);
                    }
                }
            }
        }

        return graph;
    }
}
