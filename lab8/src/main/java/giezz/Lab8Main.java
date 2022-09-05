package giezz;

import java.util.*;
import java.util.stream.Collectors;

public class Lab8Main {

    public static void main(String[] args) {
//        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 4, 4, 5, 5, 5, 6));
        List<String> words = new ArrayList<>(Arrays.asList("a", "b", "c", "c", "d", "d", "d", "r"));

        Map<String, Long> wordsByCount = words.stream()
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));

        String prevalentWord = wordsByCount.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow().getKey();

        System.out.println(prevalentWord);

        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("bob1", 24, 73000, Person.Position.ENGINEER),
                new Person("bob2", 23, 82000, Person.Position.MANAGER),
                new Person("bob3", 22, 73000, Person.Position.ENGINEER),
                new Person("bob4", 24, 73000, Person.Position.ENGINEER),
                new Person("bob5", 12, 82000, Person.Position.MANAGER),
                new Person("bob6", 44, 100000, Person.Position.CEO),
                new Person("bob7", 55, 82000, Person.Position.MANAGER),
                new Person("bob8", 34, 73000, Person.Position.ENGINEER),
                new Person("bob9", 30, 73000, Person.Position.ENGINEER)
        ));

        System.out.println(
                personList.stream()
                        .mapToDouble(Person::getSalary)
                        .average()
                        .orElseThrow()
        );

        System.out.println(
                findEldest(3, personList).stream()
                        .map(Person::getName)
                        .collect(Collectors.toList()));
    }

    public static List<Person> findEldest(int n, List<Person> list) {
        return list.stream()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(n)
                .collect(Collectors.toList());
    }
}
