package year2023.Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class MapSolver {
    public static void main(String[] args) throws FileNotFoundException {
        List<RightLeft> directions = readSteps("./src/main/java/year2023/Day8/input");
        Map<String, String[]> nodes = readNodes("./src/main/java/year2023/Day8/input");
        System.out.println("#steps: " +  calculateAmountOfSteps("AAA", "ZZZ", directions, nodes));

       directions = readSteps("./src/main/java/year2023/Day8/input_pt2");
       nodes = readNodes("./src/main/java/year2023/Day8/input_pt2");
       System.out.println("#steps: " +  calculateAmountOfStepsMultiPath(directions, nodes));
    }

    static long calculateAmountOfStepsMultiPath(List<RightLeft> directions, Map<String, String[]> nodes) {
        List<String> currentNodes = findNodesContaining(nodes, "A");
        List<Long> steps = new ArrayList<>();
        for(int i = 0; i < currentNodes.size(); i++) {
            steps.add(calculateAmountOfSteps(currentNodes.get(i), "Z", directions, nodes));
        }
        return getCommunalDivider(steps);
    }

    public static long lcm(long a, long b) {
        if (b == 0) {
            return a;
        }
        return lcm(b, a % b);
    }

    public static long getCommunalDivider(List<Long> steps) {
        long lcm = steps.get(0);
        for (int i = 1; i < steps.size(); i++) {
            long num1 = lcm;
            long num2 = steps.get(i);
            long gcd_val = lcm(num1, num2);
            lcm = (lcm * steps.get(i)) / gcd_val;
        }
        return lcm;
    }

    private static List<String> findNodesContaining(Map<String, String[]> nodes, String a) {
        List<String> nodesContaining = new ArrayList<>();
        for(String key : nodes.keySet()) {
            if(key.endsWith(a)) nodesContaining.add(key);
        }
        return nodesContaining;
    }

    static long calculateAmountOfSteps(String startNode, String endNode, List<RightLeft> directions, Map<String, String[]> nodes) {
        boolean endIsReached = false;
        long count = 0;
        int index = 0;
        String currentNode = startNode;
        while(!endIsReached) {
            String[] children = nodes.get(currentNode);
            count ++;
            String nextNode = children[directions.get(index).index];
            if(nextNode.endsWith(endNode)) endIsReached = true;
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
