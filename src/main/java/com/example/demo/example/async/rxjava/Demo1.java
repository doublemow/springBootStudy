package com.example.demo.example.async.rxjava;

import com.example.demo.example.async.Person;
import io.reactivex.Flowable;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) {
        List<Person> personList = makeList();

        Flowable.fromArray(personList.toArray(new Person[0]))
                .filter(person -> person.getAge() >= 3)
                .map(Person::getName)
                .subscribe(System.out::println);
    }

    public static List<Person> makeList(){
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person(1,"1");
        Person person2 = new Person(2,"2");
        Person person3 = new Person(3,"3");
        Person person4 = new Person(4,"4");
        Person person5 = new Person(5,"5");
        Person person6 = new Person(6,"6");
        Person person7 = new Person(7,"7");

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);
        return personList;
    }
}
