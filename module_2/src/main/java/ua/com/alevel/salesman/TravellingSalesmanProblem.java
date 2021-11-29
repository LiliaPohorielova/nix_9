package ua.com.alevel.salesman;

import ua.com.alevel.ChooseTask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TravellingSalesmanProblem {

    private static final List<CityNode> nodes = new ArrayList<>();
    private static final List<CityRelation> edges = new ArrayList<>();

    public static void run() throws IOException {
        String inputFile = "input_town.txt";
        String outputFile = "output_town.txt";
        BufferedReader bufferedReader = null;
        System.out.println("\n---------------------- Travelling Salesman Problem -------------------------");
        System.out.println("Reading input file: resources/input_town.txt");
        int countOfCities = 0;
        int countOfNeigbours;
        String line;
        String name;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(TravellingSalesmanProblem.class.getClassLoader().getResourceAsStream("starts/" + inputFile)));
            if ((line = bufferedReader.readLine()) != null) {
                countOfCities = Integer.parseInt(line);
            }
            if (countOfCities == 0) {
                System.out.println("File is empty or doesn't contain correct Count Of Cities! Renew file and try again!");
                new ChooseTask().run();
            }
            for (int i = 0; i < countOfCities; i++) {
                CityNode temp = new CityNode("Node_" + (i + 1), "Node_" + (i + 1));
                nodes.add(temp);
            }
            for (int i = 0; i < countOfCities; i++) {
                name = bufferedReader.readLine();
                nodes.get(i).setName(name);
                line = bufferedReader.readLine();
                countOfNeigbours = Integer.parseInt(line);
                for (int j = 0; j < countOfNeigbours; j++) {
                    line = bufferedReader.readLine();
                    String[] indexOfNeigbours = line.split(" ");
                    addRelation("rel_" + (i + 1) + "_" + (j + 1), i + 1, Integer.parseInt(indexOfNeigbours[0]), Integer.parseInt(indexOfNeigbours[1]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Analyzing cities...");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("module_2/src/main/resources/results/" + outputFile, true), StandardCharsets.UTF_8))) {
            findAndSaveShortestPath(bufferedReader, writer);
        } catch (FileNotFoundException e) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/results/" + outputFile, true), StandardCharsets.UTF_8))) {
                findAndSaveShortestPath(bufferedReader, writer);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
        new ChooseTask().run();
    }

    private static void findAndSaveShortestPath(BufferedReader bufferedReader, Writer writer) throws IOException {
        System.out.println("Finding the shortest path...");
        System.out.println("The shortest path is: ");
        String line;
        int countOfCitiesForFindingPath;
        CityGraph graph = new CityGraph(edges, nodes);
        line = bufferedReader.readLine();
        countOfCitiesForFindingPath = Integer.parseInt(line);
        for (int i = 0; i < countOfCitiesForFindingPath; i++) {
            line = bufferedReader.readLine();
            String[] namesOfFindingCities = line.split(" ");
            DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
            dijkstra.execute(graph.findCityNodeByName(namesOfFindingCities[0]));
            LinkedList<CityNode> path = dijkstra.getPath(graph.findCityNodeByName(namesOfFindingCities[1]));
            int finalCost = 0;
            for (int k = 0; k < path.size() - 1; k++) {
                finalCost += graph.findCostByRel(path.get(k).getName(), path.get(k + 1).getName());
            }
            writer.write(Integer.toString(finalCost));
            writer.write("\n");
            System.out.print(finalCost + " ");
        }
        bufferedReader.close();
        System.out.println("\nData writing successfully completed!");
        writer.write("\n");
        writer.close();
    }

    private static void addRelation(String laneId, int sourceLocNo, int destLocNo, int duration) {
        CityRelation relation = new CityRelation(laneId, nodes.get(sourceLocNo - 1), nodes.get(destLocNo - 1), duration);
        edges.add(relation);
    }
}
