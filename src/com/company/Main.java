package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // write your code here
        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6",
                "G53", "G49", "G68", "G56", "g64",
                "I26", "I17", "I29",
                "O71");
        List<String> gNumbrs = new ArrayList<>();
//        someBingoNumbers.forEach(number ->{
//            if (number.toUpperCase().startsWith("G")) {
//                gNumbrs.add(number);
////                System.out.println(number);
//            }
//        });
//        gNumbrs.sort(String::compareTo);
//        gNumbrs.forEach(s -> System.out.println(s));
        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);


        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> inNumberString = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
        Stream<String> concatString = Stream.concat(ioNumberStream, inNumberString);
        System.out.println("------------------------------------------");
        System.out.println(concatString.distinct().peek(System.out::println).count());

        Employee john = new Employee("John Marston", 30);
        Employee jack = new Employee("Jack Reaper", 25);
        Employee jason = new Employee("Jason Voorhees", 38);
        Employee jane = new Employee("Jane VanHooper", 22);

        Department hr = new Department("Human Resources");

        hr.addEmployee(jack);
        hr.addEmployee(jason);
        hr.addEmployee(jane);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments
                .stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);
        System.out.println("---------------------------------------------");
//        List<String>sortedGNumbers=someBingoNumbers
//                .stream()
//                .map(String::toUpperCase)
//                .filter(s -> s.startsWith("G"))
//                .sorted()
//                .collect(Collectors.toList());
        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        sortedGNumbers.forEach(System.out::println);
        //List by age
        Map<Integer, List<Employee>> groupedByAge = departments
                .stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        // return the youngest

        departments
                .stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        Stream.of("ABC", "AC", "BAA", "CCCC", "X", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
                .count();


        Runnable r = () -> {
            String mystring = "Let's split this up into array";
            String[] parts = mystring.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
        r.run();

        Function<String, String> lambdafunction = (s) -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };
        System.out.println(lambi(lambdafunction));

        Supplier<String> iLoveJava = () -> "I love java";

        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);

        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );

        topNames2015
                .stream()
                .map(s -> s.replace(s.substring(0, 1), s.substring(0, 1).toUpperCase()))
                .sorted()
                .forEach(System.out::println);

        long n = topNames2015
                .stream()
                .map(s -> s.replace(s.substring(0, 1), s.substring(0, 1).toUpperCase()))
                .filter(s -> s.startsWith("A"))
                .count();
        System.out.println(n);
        System.out.println("-------------------");
        topNames2015
                .stream()
                .map(s -> s.replace(s.substring(0, 1), s.substring(0, 1).toUpperCase()))
                .peek(System.out::println)
                .collect(Collectors.toList());


    }


    public static String lambi(Function<String, String> lambdaF) {
        return lambdaF.apply("1234567890");
    }


}






