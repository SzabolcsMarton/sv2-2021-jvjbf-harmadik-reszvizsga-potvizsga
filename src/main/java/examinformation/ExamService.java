package examinformation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamService {

    private static final int LIMIT_PERCENT_TO_PASS = 51;

    private int theoryMax;
    private int practiceMax;
    private int minPointsToPassTheory;
    private int minPointsToPassPractice;


    private Map<String, ExamResult> results = new TreeMap<>();

    public void readFromFIle(Path path) {
        try {
            parseLines(Files.readAllLines(path));
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: " + path, ioe);
        }
    }

    public List<String> findPeopleFailed() {
        return results.entrySet()
                .stream()
                .filter(e -> e.getValue().getTheory() <= minPointsToPassTheory
                        || e.getValue().getPractice() <= minPointsToPassPractice)
                .map(Map.Entry::getKey)
                .toList();
    }

    public String findBestPerson() {
        return results.entrySet()
                .stream()
                .filter(e -> !findPeopleFailed().contains(e.getKey()))
                .max(Comparator.comparing(e -> e.getValue().getSumOfPoints()))
                .orElseThrow(() -> new IllegalStateException("Can not find")).getKey();
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public Map<String, ExamResult> getResults() {
        return new TreeMap<>(results);
    }

    private void parseLines(List<String> lines) {
        parseMaxPoints(lines);
        for (String actual : lines) {
            addToMap(actual);
        }
    }

    private void addToMap(String actualLine) {
        String[] parts = actualLine.split(";");
        String[] partsOfPoints = parts[1].split(" ");
        results.put(parts[0], new ExamResult(Integer.parseInt(partsOfPoints[1]), Integer.parseInt(partsOfPoints[0])));
    }

    private void parseMaxPoints(List<String> lines) {
        String[] lineParts = lines.get(0).split(" ");
        this.theoryMax = Integer.parseInt(lineParts[0]);
        this.practiceMax = Integer.parseInt(lineParts[1]);
        lines.remove(0);
        this.minPointsToPassPractice = getPointLimitToPass(practiceMax);
        this.minPointsToPassTheory = getPointLimitToPass(theoryMax);
    }

    private int getPointLimitToPass(int maxPoints) {
        return (int) (maxPoints * (LIMIT_PERCENT_TO_PASS / 100.0));
    }

}
