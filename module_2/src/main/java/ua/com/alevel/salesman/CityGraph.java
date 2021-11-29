package ua.com.alevel.salesman;

import java.io.IOException;
import java.util.*;

public class CityGraph {

    private List<CityRelation> relations;
    private List<CityNode> nodes;

    public CityGraph(List<CityRelation> relations, List<CityNode> nodes) {
        this.relations = relations;
        this.nodes = nodes;
    }

    public List<CityRelation> getCityRelations() {
        return relations;
    }

    public List<CityNode> getCityNodes() {
        return nodes;
    }

    public CityNode findCityNodeByName(String name) throws IOException {
        for (CityNode node : nodes) {
            if (node.getName().equals(name))
                return node;
        }
        throw new IOException("not found node with such name");
    }

    public int findCostByRel(String name1, String name2) throws IOException {
        for (CityRelation rel : relations) {
            if (rel.getSourceName().equals(name1) && rel.getDestinationName().equals(name2))
                return rel.getWeight();
        }
        throw new IOException("not found rel with such name");
    }
}
