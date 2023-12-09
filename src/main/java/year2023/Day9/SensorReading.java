package year2023.Day9;

import java.util.ArrayList;
import java.util.List;

public class SensorReading {
    private Long[] sequence;
    private List<List<Long>> extrapolations;
    public SensorReading(Long[] sequence) {
        this.sequence = sequence;
        this.extrapolations = new ArrayList<>();
    }

    public void extrapolate() {
        List<Long> extrapolation = new ArrayList<>();
        extrapolations.add(new ArrayList<>(List.of(sequence)));
        for (int i = 0; i < sequence.length-1; i++) {
            extrapolation.add(i, sequence[i+1] - sequence[i]);
        }
        extrapolations.add(extrapolation);
        boolean allZero = extrapolation.stream().allMatch(x -> x == 0);
        while(!allZero) {
            List<Long> lastExtrapolation = extrapolations.get(extrapolations.size()-1);
            extrapolation = new ArrayList<>();
            for(int i = 0; i < lastExtrapolation.size()-1; i++) {
                extrapolation.add(i, lastExtrapolation.get(i+1) - lastExtrapolation.get(i));
            }
            extrapolations.add(extrapolation);
            allZero = extrapolation.stream().allMatch(x -> x == 0);
        }
    }

    public long predictNextValue() {
        long summingFactor = 0;
        for(int i = extrapolations.size()-1; i >= 0; i--) {
            List<Long> extrapolation = extrapolations.get(i);
            long previousValue = extrapolation.get(extrapolation.size()-1);
            extrapolation.add(previousValue + summingFactor);
            summingFactor = previousValue + summingFactor;
        }
        return extrapolations.get(0).get(extrapolations.get(0).size()-1);
    }

    public long predictBackwards() {
        long summingFactor = 0;
        for(int i = this.extrapolations.size()-1; i >= 0; i--) {
            List<Long> extrapolation = extrapolations.get(i);
            long previousValue = extrapolation.get(0);
            extrapolation.add(0,  previousValue- summingFactor);
            summingFactor = previousValue - summingFactor;
        }
        return extrapolations.get(0).get(0);

    }
}
