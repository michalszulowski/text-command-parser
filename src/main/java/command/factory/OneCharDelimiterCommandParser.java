package command.factory;

import command.TextCommand;

import java.util.*;
import java.util.stream.Collectors;

public class OneCharDelimiterCommandParser implements TxtCommandParser {
    private char delimiter;
    private char combinedArgDelimiter;
    private String input;

    public OneCharDelimiterCommandParser(char delimiter, char combinedArgDelimiter) {
        this.delimiter = delimiter;
        this.combinedArgDelimiter = combinedArgDelimiter;
    }

    @Override
    public TextCommand parse(String input) {
        this.input = input;
        List<Integer> breaks = findBreaks();
        return splitByBreaks(breaks);
    }

    private TextCommand splitByBreaks(List<Integer> breaks) {
        List<String> tokens = new ArrayList<>();
        int iter = 0;
        while (iter + 1 < breaks.size()) {
            int from = breaks.get(iter) + 1;
            int to = breaks.get(iter + 1);
            cutToken(from, to).ifPresent(tokens::add);
            iter += 1;
        }
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("No valids token in given input");
        }
        return new TextCommand(tokens.get(0), tokens.subList(1, tokens.size()));
    }

    private Optional<String> cutToken(int from, int to) {
        String token = input.substring(from, to);
        if (token.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(token);
    }

    private List<Integer> findBreaks() {
        Deque<Integer> delimiterOccurrences = findOccurrence(input, delimiter);
        delimiterOccurrences.add(input.length());
        Deque<Integer> combinedDelimiterOccurrences = findOccurrence(input, combinedArgDelimiter);
        addOccurrenceAtTheEndIfPairLacking(combinedDelimiterOccurrences);
        Deque<Integer> merged = mergeOccurrences(combinedDelimiterOccurrences, delimiterOccurrences);
        merged.addFirst(-1);
        return new ArrayList<>(merged);
    }

    private void addOccurrenceAtTheEndIfPairLacking(Collection<Integer> occurrences) {
        if (occurrences.size() % 2 != 0) {
            occurrences.add(input.length());
        }
    }

    private Deque<Integer> findOccurrence(String in, char of) {
        Deque<Integer> occurrences = new ArrayDeque<>();
        for (int index = 0; index < in.length(); index++) {
            if (in.charAt(index) == of) {
                occurrences.addLast(index);
            }
        }
        return occurrences;
    }

    // occ1 more important than occ2
    private Deque<Integer> mergeOccurrences(Deque<Integer> occ1, Deque<Integer> occ2) {
        int[] arr1 = occ1.stream().mapToInt(i -> i).toArray();
        int[] arr2 = occ2.stream().mapToInt(i -> i).toArray();
        int[] breaks = new ArrayBetweenSimpleMerger().merge(arr1, arr2);
        Deque<Integer> result = Arrays.stream(breaks).boxed().collect(Collectors.toCollection(ArrayDeque::new));
        return result;
    }

    public static void main(String[] args) {
        String txt1 = "abc";
        String txt2 = txt1.substring(-1, txt1.length());
        System.out.println(txt2);
    }
}
