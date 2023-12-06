package year2023.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BoatRace {
    public static void main(String[] args) throws FileNotFoundException {
        List[] timesDistances = readGameInput("./src/main/java/year2023/Day6/input");
        long possibilities = calculateRacePossibilities(timesDistances);
        System.out.println("score: " + possibilities);

        timesDistances = enlargeInput(timesDistances);
        possibilities = calculateRacePossibilities(timesDistances);
        System.out.println("score: " + possibilities);
    }

    static List[] enlargeInput(List[] timesDistances) {
        List<Long> duration = timesDistances[0];
        List<Long> record = timesDistances[1];

        List<Long> newDuration = new ArrayList<>();
        List<Long> newRecord = new ArrayList<>();

        assert duration.size() == record.size();

        StringBuilder sb = new StringBuilder();
        for(long i : duration) {
            sb.append(i);
        }
        newDuration.add(Long.parseLong(sb.toString()));

        sb = new StringBuilder();
        for(long i : record) {
            sb.append(i);
        }
        newRecord.add(Long.parseLong(sb.toString()));

        return new List[]{newDuration, newRecord};
    }

    static long calculateRacePossibilities(List[] timesDistances) {
        List<Long> duration = timesDistances[0];
        List<Long> record = timesDistances[1];

        assert duration.size() == record.size();

        long totalPossibilities = 1;
        for(int i = 0; i < duration.size(); i++) {
            long time = duration.get(i);
            long distance = record.get(i);
            long currentPossibilities = 0;

            for(long timeButtonPress = 0; timeButtonPress <= time; timeButtonPress++) {
                long speed = timeButtonPress;
                long remainingTime = time - timeButtonPress;
                long distanceTraveled = speed * remainingTime;
                if(distanceTraveled > distance) {
                    currentPossibilities++;
                }
            }
            totalPossibilities *= currentPossibilities;
        }
        return totalPossibilities;
    }

    static List[] readGameInput(String s) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(s));
        
        List<Long> duration = (Arrays.stream(sc.nextLine().split(":\\s+")[1].split("\\s+")).mapToLong(Long::parseLong).boxed().toList());
        List<Long> record = (Arrays.stream(sc.nextLine().split(":\\s+")[1].split("\\s+")).mapToLong(Long::parseLong).boxed().toList());

        return new List[]{duration, record};
    }
}
