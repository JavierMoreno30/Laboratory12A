package domain;

import domain.list.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DirectedAdjacencyListGraphTest {

    private DirectedAdjacencyListGraph graph;
    private final Random random = new Random();

    @BeforeEach
    void setUp() {
        // i. Crear e instanciar un objeto tipo DirectedAdjacencyListGraph
        graph = new DirectedAdjacencyListGraph(10);
    }

    @Test
    void testCompleteGraphOperations() throws GraphException, ListException, StackException, QueueException {
        System.out.println("===== PRUEBA DE DIRECTED ADJACENCY LIST GRAPH =====");

        // j. Agregar vértices (A-M para cubrir todos los mencionados)
        String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};

        // Asegurarse de no exceder el tamaño del grafo
        int verticesCount = Math.min(vertices.length, 10);

        for (int i = 0; i < verticesCount; i++) {
            graph.addVertex(vertices[i]);
            System.out.println("Añadido vértice " + vertices[i]);
        }

        // k. Añadir aristas con pesos aleatorios entre 10 y 50
        addEdgeWithRandomWeight("A", "B");
        addEdgeWithRandomWeight("B", "C");
        addEdgeWithRandomWeight("C", "D");
        addEdgeWithRandomWeight("D", "E");
        addEdgeWithRandomWeight("E", "F");
        addEdgeWithRandomWeight("F", "G");
        addEdgeWithRandomWeight("G", "H");
        addEdgeWithRandomWeight("H", "I");
        addEdgeWithRandomWeight("I", "J");
        addEdgeWithRandomWeight("J", "K");
        addEdgeWithRandomWeight("K", "L");
        addEdgeWithRandomWeight("L", "M");
        addEdgeWithRandomWeight("H", "K"); // Para eliminar después
        addEdgeWithRandomWeight("I", "L"); // Para eliminar después
        addEdgeWithRandomWeight("J", "M"); // Para eliminar después

        // l. Mostrar información con toString()
        System.out.println("\n=== GRAFO INICIAL ===");
        System.out.println(graph.toString());

        // m. Probar recorridos dfs() y bfs()
        try {
            System.out.println("\n=== RECORRIDO DFS ===");
            System.out.println(graph.dfs());

            System.out.println("\n=== RECORRIDO BFS ===");
            System.out.println(graph.bfs());
        } catch (Exception e) {
            System.out.println("Error en los recorridos: " + e.getMessage());
        }

        // n. Suprimir vértices E, F, G
        System.out.println("\n=== ELIMINANDO VÉRTICES E, F, G ===");
        try {
            graph.removeVertex("E");
            System.out.println("Vértice E eliminado");

            graph.removeVertex("F");
            System.out.println("Vértice F eliminado");

            graph.removeVertex("G");
            System.out.println("Vértice G eliminado");

            // Verificar la eliminación
            assertFalse(graph.containsVertex("E"));
            assertFalse(graph.containsVertex("F"));
            assertFalse(graph.containsVertex("G"));
        } catch (Exception e) {
            System.out.println("Error al eliminar vértices: " + e.getMessage());
        }

        // o. Suprimir aristas H-K, I-L, J-M
        System.out.println("\n=== ELIMINANDO ARISTAS H-K, I-L, J-M ===");
        try {
            if (graph.containsEdge("H", "K")) {
                graph.removeEdge("H", "K");
                System.out.println("Eliminada arista H-K");
            }

            if (graph.containsEdge("I", "L")) {
                graph.removeEdge("I", "L");
                System.out.println("Eliminada arista I-L");
            }

            if (graph.containsEdge("J", "M")) {
                graph.removeEdge("J", "M");
                System.out.println("Eliminada arista J-M");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar aristas: " + e.getMessage());
        }

        // p. Mostrar el grafo final
        System.out.println("\n=== GRAFO FINAL ===");
        System.out.println(graph.toString());
    }

    /**
     * Añade una arista con peso aleatorio entre 10 y 50
     */
    private void addEdgeWithRandomWeight(Object source, Object target) {
        try {
            int weight = random.nextInt(41) + 10; // Rango [10, 50]
            graph.addEdgeWeight(source, target, weight);
            System.out.println("Añadida arista " + source + " -> " + target + " con peso " + weight);
        } catch (Exception e) {
            System.out.println("No se pudo añadir la arista " + source + " -> " + target + ": " + e.getMessage());
        }
    }
}