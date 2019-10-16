package com.nordclan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static List<Integer> image = new ArrayList<>();
    static {
        image.add(1);
    }

    private static String getNumbers(Integer limit) {
        return IntStream.range(1, limit / 2 + 1).map(x -> x * 2)
                .mapToObj((i) -> {
                    StringBuilder sb = new StringBuilder();
                    if ((i - 1) % 7 == 0) sb.append("seven");
                    else sb.append(i - 1);
                    if (i > 13 && (i % 7 == 0)) sb.append(" twoseven");
                    else sb.append(" two");
                    return sb.toString();
                }).collect(Collectors.joining(" "));
    }

    private static Integer combinations(Integer r, Integer m) {
        if (image.size() < m) {
            for (int i = image.size() - 1; i < m; i++) {
                image.add(image.get(i) * (i + 2));
            }
        }
        int d = 1;
        if (m - r - 1 >= 0)
            d = image.get(m - r - 1);
        return Integer.valueOf(image.get(m - 1) / image.get(r - 1) / d);
    }

    private static Map<String, Long> wordCounter(String text) {
        Map<String, Long> words = Stream.of(text.toLowerCase()
                .replaceAll("[,\\.]", "").split("\\s"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return words.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void main(String[] args) {
        System.out.println(getNumbers(100));
        System.out.println(combinations(2, 4));
        String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";
        System.out.print(wordCounter(text));
    }
}
