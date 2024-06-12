package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.System.*;


public class Main {

    public static void main(String[] args) throws Exception {
        long startTime = currentTimeMillis();

        File file = null;
        try {
             file = new File(args[0]);
        } catch (Exception e) {
            out.println("File path is not specified.");
        }

        List<Integer> allNumbers = new ArrayList<>();

        List<Integer> numbersFromFile = null;

        try {
            numbersFromFile = readNumbersFromFile(file);
            allNumbers.addAll(numbersFromFile);
        } catch (FileNotFoundException e) {
            out.println("Provided file in not exists.");
        } catch (NumberFormatException nfe) {
            out.println("File contains incorrect data");
        }

        if (allNumbers.isEmpty()) {
            out.println("No numbers found in the .txt files.");
        } else {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int sum = 0;
            for (int num : allNumbers) {
                sum += num;
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            double median = findMedian(allNumbers);
            double average = calculateAverage(sum, allNumbers.size());
            List<Integer> increasingSequence = findLongestSequence(allNumbers, true);
            List<Integer> decreasingSequence = findLongestSequence(allNumbers, false);

            out.println("Maximum number: " + max);
            out.println("Minimum number: " + min);
            out.println("Median: " + median);
            out.println("Average: " + average);
            out.println("Longest increasing sequence: " + increasingSequence);
            out.println("Longest decreasing sequence: " + decreasingSequence);
        }

        long endTime = currentTimeMillis();
        long executionTime = endTime - startTime;
        out.println("Execution time: " + executionTime + " ms");
    }

    private static List<Integer> readNumbersFromFile(File file) throws FileNotFoundException {
        List<Integer> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        }
        return numbers;
    }

    private static double findMedian(List<Integer> numbers) {
        int size = numbers.size();
        if (size % 2 == 0) {
            return (quickselect(numbers, size / 2 - 1) + quickselect(numbers, size / 2)) / 2.0;
        } else {
            return quickselect(numbers, size / 2);
        }
    }

    private static int quickselect(List<Integer> numbers, int k) {
        List<Integer> smaller = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        int pivot = numbers.get(numbers.size() / 2);
        for (int num : numbers) {
            if (num < pivot) {
                smaller.add(num);
            } else if (num > pivot) {
                greater.add(num);
            }
        }
        if (k < smaller.size()) {
            return quickselect(smaller, k);
        } else if (k >= numbers.size() - greater.size()) {
            return quickselect(greater, k - (numbers.size() - greater.size()));
        } else {
            return pivot;
        }
    }

    private static double calculateAverage(int sum, int count) {
        return (double) sum / count;
    }

    private static List<Integer> findLongestSequence(List<Integer> numbers, boolean increasing) {
        List<Integer> currentSequence = new ArrayList<>();
        List<Integer> longestSequence = new ArrayList<>();

        for (Integer number : numbers) {
            if (currentSequence.isEmpty() ||
                    (increasing && number > currentSequence.get(currentSequence.size() - 1)) ||
                    (!increasing && number < currentSequence.get(currentSequence.size() - 1))) {
                currentSequence.add(number);
            } else {
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = new ArrayList<>(currentSequence);
                }
                currentSequence = new ArrayList<>();
                currentSequence.add(number);
            }
        }
        if (currentSequence.size() > longestSequence.size()) {
            longestSequence = new ArrayList<>(currentSequence);
        }
        return longestSequence;
    }
}