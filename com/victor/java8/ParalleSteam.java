import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

class ParalleStrema{
    public static void main(String[] args) {

        System.out.println("i.-----------------------------------------------");
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());

        System.out.println("ii.-----------------------------------------------");
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
            .parallelStream()
            .filter(s -> {
                System.out.format("filter: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n",
                s, Thread.currentThread().getName()));

        System.out.println("iii.-----------------------------------------------");
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
            .parallelStream()
            .filter(s -> {
                System.out.format("filter: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .sorted((s1, s2) -> {
                System.out.format("sort: %s <> %s [%s]\n",
                    s1, s2, Thread.currentThread().getName());
                return s1.compareTo(s2);
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n",
                s, Thread.currentThread().getName()));
    }
}