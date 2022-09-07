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
                new Person("bob1", 24, 73000, Person.Position.ENGINEER, Person.Gender.MALE),
                new Person("bob2", 23, 82000, Person.Position.MANAGER, Person.Gender.MALE),
                new Person("bob3", 22, 73000, Person.Position.ENGINEER, Person.Gender.FEMALE),
                new Person("bob4", 25, 79900, Person.Position.ENGINEER, Person.Gender.MALE),
                new Person("bob5", 12, 82000, Person.Position.MANAGER, Person.Gender.MALE),
                new Person("bob6", 44, 100000, Person.Position.CEO, Person.Gender.FEMALE),
                new Person("bob7", 55, 82000, Person.Position.MANAGER, Person.Gender.MALE),
                new Person("bob8", 34, 73000, Person.Position.ENGINEER, Person.Gender.MALE),
                new Person("bob9", 30, 99000, Person.Position.ENGINEER, Person.Gender.MALE)
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

        System.out.println(
                personList.stream()
                        .filter(person -> person.getGender() == Person.Gender.MALE && person.getPosition() == Person.Position.ENGINEER)
                        .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                        .limit(3)
                        .sorted((o1, o2) -> (int) (o2.getSalary() - o1.getSalary()))
                        .map(Person::getName)
                        .collect(Collectors.toList())
        );
    }

    public static List<Person> findEldest(int n, List<Person> list) {
        return list.stream()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(n)
                .collect(Collectors.toList());
    }
}
