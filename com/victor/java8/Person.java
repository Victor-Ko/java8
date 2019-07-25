import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {

        System.out.println("i.-----------------------------------------------");
        List<Person> persons =
            Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        List<Person> filtered = persons
            .stream()
            .filter(p -> p.name.startsWith("P"))
            .collect(Collectors.toList());

        System.out.println(filtered);

        System.out.println("ii.-----------------------------------------------");
        Map<Integer, List<Person>> personsByAge = persons
            .stream()
            .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
            .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

        System.out.println("iii.-----------------------------------------------");
        Double averageAge = persons
            .stream()
            .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge); 

        System.out.println("iv.-----------------------------------------------");
        IntSummaryStatistics ageSummary = persons
            .stream()
            .collect(Collectors.summarizingInt(p -> p.age));
        
        System.out.println(ageSummary);
        //System.out.println(ageSummary.getAverage());

        System.out.println("v.-----------------------------------------------");
        String phrase = persons
            .stream()
            .filter(p -> p.age >= 18)
            .map(p -> p.name)
            //구획 문자, 추가적으로 접두사와 접미사
            .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);

        System.out.println("vi.-----------------------------------------------");
        Map<Integer, String> map = persons
            .stream()
            .collect(Collectors.toMap(
                p -> p.age,
                p -> p.name,
                (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);

        System.out.println("vii.-----------------------------------------------");
        Collector<Person, StringJoiner, String> personNameCollector =
            Collector.of(
                () -> new StringJoiner(" | "),          // supplier
                (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                (j1, j2) -> j1.merge(j2),               // combiner
                StringJoiner::toString);                // finisher

        String names = persons
            .stream()
            .collect(personNameCollector);

        System.out.println(names); 

        System.out.println("viii.-----------------------------------------------");
            persons
        .stream()
        .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
        .ifPresent(System.out::println);

        System.out.println("ix.-----------------------------------------------");
        Person result = persons
            .stream()
            .reduce(new Person("", 0), (p1, p2) -> {
                p1.age += p2.age;
                p1.name += p2.name;
                return p1;
            });

        System.out.format("name=%s; age=%s", result.name, result.age);
        System.out.println();

        System.out.println("xi.-----------------------------------------------");
        Integer ageSum = persons
            .stream()
            .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

        System.out.println(ageSum);
    }
}