package year2023.Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MapSolver {
    public static void main(String[] args) throws FileNotFoundException {
        List<RightLeft> directions = readSteps("./src/main/java/year2023/Day8/input");
        Map<String, String[]> nodes = readNodes("./src/main/java/year2023/Day8/input");
        System.out.println("#steps: " +  calculateAmountOfSteps(directions, nodes));

       directions = readSteps("./src/main/java/year2023/Day8/input_pt2");
       nodes = readNodes("./src/main/java/year2023/Day8/input_pt2");
       System.out.println("#steps: " +  calculateAmountOfStepsMultiPath(directions, nodes));
    }

    static long calculateAmountOfStepsMultiPath(List<RightLeft> directions, Map<String, String[]> nodes) {
        boolean endIsReached = false;
        long count = 0;
        int index = 0;
        List<String> currentNodes = findNodesContaining(nodes, "A");
        while(!endIsReached) {
            endIsReached = true;
            List<String> nextNodes = new ArrayList<>();
            for(String node : currentNodes) {
                String[] children = nodes.get(node);
                String nextNode = children[directions.get(index).index];
                if(!nextNode.endsWith("Z")) endIsReached = false;
                nextNodes.add(nextNode);
            }
            currentNodes = nextNodes;
            if(index == directions.size() - 1) index = 0;
            else index++;
            count ++;
        }
        return count;

    }

    private static List<String> findNodesContaining(Map<String, String[]> nodes, String a) {
        List<String> nodesContaining = new ArrayList<>();
        for(String key : nodes.keySet()) {
            if(key.endsWith(a)) nodesContaining.add(key);
        }
        return nodesContaining;
    }

    static long calculateAmountOfSteps(List<RightLeft> directions, Map<String, String[]> nodes) {
        boolean endIsReached = false;
        long count = 0;
        int index = 0;
        String currentNode = "AAA";
        while(!endIsReached) {
            String[] children = nodes.get(currentNode);
            count ++;
            String nextNode = children[directions.get(index).index];
            if(nextNode.equals("ZZZ")) endIsReached = true;
            currentNode = nextNode;
            if(index == directions.size() - 1) index = 0;
            else index++;
        }
        return count;
    }

    static Map<String, String[]> readNodes(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        Map<String, String[]> nodes = new LinkedHashMap<>();
        sc.nextLine(); sc.nextLine();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" = ");
            String node = line[0];

            String[] children = line[1].replace("(", "").replace(")", "").split(", ");
            nodes.put(node, children);
        }

        return nodes;
    }

    static List<RightLeft> readSteps(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        List<RightLeft> directions = new ArrayList<>();
        char[] sequence = sc.nextLine().toCharArray();
        for (char c : sequence) {
            if (c == 'R') {
                directions.add(RightLeft.RIGHT);
            } else if (c == 'L') {
                directions.add(RightLeft.LEFT);
            }
        }
        return directions;
    }
}
