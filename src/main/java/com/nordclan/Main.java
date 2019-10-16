package com.nordclan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static List<Integer> CACHE = new ArrayList<>();

    static {
        CACHE.add(1);
        CACHE.add(1);
    }

    /**
     * Метод формирует строку, в которой записаны все числа из заданного диапазона
     * со следующими заменами: кратные 2 на "Two", кратные 7 на "Seven",
     * кратные 2 и 7 на "TwoSeven".
     *
     * @param limit ограничение диапазона справа
     * @return возвращает строку с заменами
     */
    private static String getNumbers(Integer limit) {
        if (limit < 2) {
            throw new IllegalArgumentException("Limit must be greater than or equal to 2");
        }
        // рассматривается только четный диапазон
        return IntStream.range(1, limit / 2 + 1).map(x -> x * 2)
                .mapToObj((i) -> {
                    // каждое значение формирует пару из текущего четного и
                    StringBuilder sb = new StringBuilder();
                    if ((i - 1) % 7 == 0) {
                        sb.append("seven");
                    } else {
                        sb.append(i - 1);
                    }
                    // предыдущего нечетного
                    if (i % 7 == 0) {
                        sb.append(" twoseven");
                    } else {
                        sb.append(" two");
                    }
                    return sb.toString();
                }).collect(Collectors.joining(" "));
    }

    /**
     * Метод рассчитывает количество сочетаний из m по r.
     *
     * @param m количество элементов
     * @param r количество элементов в одном сочетании
     * @return значение колчиества сочетаний
     */
    private static Integer combinations(Integer r, Integer m) {
        if ((r < 0) || (m < 0)) {
            throw new IllegalArgumentException("Params r and m must be positive");
        }
        if (CACHE.size() < m + 1) {
            for (int i = CACHE.size(); i < m + 1; i++) {
                CACHE.add(CACHE.get(i - 1) * i);
            }
        }
        System.out.println(CACHE);
        int d = 1;
        if (m - r >= 0) {
            d = CACHE.get(m - r);
        }
        return Integer.valueOf(CACHE.get(m) / CACHE.get(r) / d);
    }

    private static Map<String, Long> wordCounter(String text) {
        Map<String, Long> words = Stream.of(text.toLowerCase()
                .replaceAll("[,\\.]", "").split("\\s"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return words.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void main(String[] args) {
        System.out.println(getNumbers(100));
        System.out.println(combinations(3, 5));
        String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";
        System.out.print(wordCounter(text));
    }
}
