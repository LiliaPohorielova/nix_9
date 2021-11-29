package ua.com.alevel.salesman;

import java.util.*;

public class DijkstraAlgorithm {

    private final List<CityNode> nodes;
    private final List<CityRelation> edges;
    private Set<CityNode> settledNodes;
    private Set<CityNode> unSettledNodes;
    private Map<CityNode, CityNode> predecessors;
    private Map<CityNode, Integer> distance;

    public DijkstraAlgorithm(CityGraph graph) {
        this.nodes = new ArrayList<CityNode>(graph.getCityNodes());
        this.edges = new ArrayList<CityRelation>(graph.getCityRelations());
    }

    public void execute(CityNode source) {
        settledNodes = new HashSet<CityNode>();
        unSettledNodes = new HashSet<CityNode>();
        distance = new HashMap<CityNode, Integer>();
        predecessors = new HashMap<CityNode, CityNode>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            CityNode node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(CityNode node) {
        List<CityNode> adjacentNodes = getNeighbors(node);
        for (CityNode target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    private int getDistance(CityNode node, CityNode target) {
        for (CityRelation edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<CityNode> getNeighbors(CityNode node) {
        List<CityNode> neighbors = new ArrayList<CityNode>();
        for (CityRelation edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private CityNode getMinimum(Set<CityNode> vertexes) {
        CityNode minimum = null;
        for (CityNode vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(CityNode vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(CityNode destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<CityNode> getPath(CityNode target) {
        LinkedList<CityNode> path = new LinkedList<CityNode>();
        CityNode step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}
