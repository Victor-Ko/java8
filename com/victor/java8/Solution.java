import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
    public static void main(String[] args) {
        //.forEach(System.out::println);
        //.forEach(e -> System.out.print(e + " "));
        System.out.println("i.-----------------------------------------------");
        Arrays.stream(new int[] {1, 2, 3})
        .map(n -> 2 * n + 1)
        .average()
        .ifPresent(System.out::println);  // 5.0

        System.out.println("ii.-----------------------------------------------");
        Stream.of("a1", "a2", "a3")
        .map(s -> s.substring(1))
        .mapToInt(Integer::parseInt)
        .max()
        .ifPresent(System.out::println);  // 3
        
        System.out.println("iii.-----------------------------------------------");
        IntStream.range(1, 4)
        .mapToObj(i -> "a" + i)
        .forEach(System.out::println);

        System.out.println("iv.-----------------------------------------------");
        Stream.of("d2", "b1", "a2", "b3", "c")
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        })
        .anyMatch(s -> {
            System.out.println("anyMatch: " + s);
            return s.startsWith("A");
        });
    }

}