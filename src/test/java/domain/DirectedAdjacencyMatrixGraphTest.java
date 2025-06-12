package domain;

import domain.list.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DirectedAdjacencyMatrixGraphTest {

    private DirectedAdjacencyMatrixGraph graph;
    private final Random random = new Random();

    @BeforeEach
    void setUp() {
        // a. Crear e instanciar un objeto tipo DirectedAdjacencyMatrixGraph
        graph = new DirectedAdjacencyMatrixGraph(10);
    }

    @Test
    void testCompleteGraphOperations() throws GraphException, ListException, StackException, QueueException {
        System.out.println("===== PRUEBA DE DIRECTED ADJACENCY MATRIX GRAPH =====");

        // b. Añadir vértices del 0 al 5
        for (int i = 0; i <= 5; i++) {
            graph.addVertex(i);
            System.out.println("Añadido vértice " + i);
        }

        // Verificar la cantidad de vértices
        assertEquals(6, graph.size());

        // c. Añadir aristas con pesos aleatorios entre 50 y 300
        addEdgeWithRandomWeight(0, 1);
        addEdgeWithRandomWeight(0, 5);
        addEdgeWithRandomWeight(1, 2);
        addEdgeWithRandomWeight(1, 3);
        addEdgeWithRandomWeight(2, 0);
        addEdgeWithRandomWeight(2, 4);
        addEdgeWithRandomWeight(3, 2);
        addEdgeWithRandomWeight(4, 3);
        addEdgeWithRandomWeight(5, 4);

        // d. Mostrar información con toString()
        System.out.println("\n=== GRAFO INICIAL ===");
        System.out.println(graph);

        // e. Probar recorridos dfs() y bfs()
        System.out.println("\n=== RECORRIDO DFS ===");
        System.out.println(graph.dfs());

        System.out.println("\n=== RECORRIDO BFS ===");
        System.out.println(graph.bfs());

        // f. Suprimir vértices 1, 3, 4
        System.out.println("\n=== ELIMINANDO VÉRTICES 1, 3, 4 ===");
        graph.removeVertex(1);
        System.out.println("Vértice 1 eliminado");
        graph.removeVertex(3);
        System.out.println("Vértice 3 eliminado");
        graph.removeVertex(4);
        System.out.println("Vértice 4 eliminado");

        // Verificar la eliminación
        assertFalse(graph.containsVertex(1));
        assertFalse(graph.containsVertex(3));
        assertFalse(graph.containsVertex(4));

        // g. Eliminar todas las aristas de los vértices 2 y 5
        System.out.println("\n=== ELIMINANDO ARISTAS DE VÉRTICES 2 Y 5 ===");
        removeAllEdgesFrom(2);
        removeAllEdgesFrom(5);

        // h. Mostrar el grafo final
        System.out.println("\n=== GRAFO FINAL ===");
        System.out.println(graph);
    }

    /**
     * Añade una arista con peso aleatorio entre 50 y 300
     */
    private void addEdgeWithRandomWeight(Object source, Object target) throws GraphException, ListException {
        int weight = random.nextInt(251) + 50; // Rango [50, 300]
        graph.addEdgeWeight(source, target, weight);
        System.out.println("Añadida arista " + source + " -> " + target + " con peso " + weight);
    }

    /**
     * Elimina todas las aristas que salen del vértice especificado
     */
    private void removeAllEdgesFrom(Object vertex) throws GraphException, ListException {
        if (!graph.containsVertex(vertex)) {
            return;
        }

        // Recorre todos los posibles vértices destino y elimina las aristas
        for (int i = 0; i <= 5; i++) {
            if (graph.containsVertex(i) && graph.containsEdge(vertex, i)) {
                graph.removeEdge(vertex, i);
                System.out.println("Eliminada arista " + vertex + " -> " + i);
            }
        }
    }

    @Test
    void size() throws ListException {
        assertEquals(0, graph.size());
    }
}