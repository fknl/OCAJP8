package com.tuygun.sandbox.other.ocajp8.optional;

import java.util.Optional;
import java.util.stream.Stream;

public class Optionals {
    public static void main(String[] args) {
        Person p1 = new Person("serhan", Optional.empty());
        if(!p1.getAddress().isPresent()){
            System.out.println("No address assigned for " + p1.getName());//No address assigned for serhan
            //orElse requires <T>
            System.out.println(p1.getName() + " may live in " +
                    p1.getAddress().orElse(new Address("Atasehir")).getStreet());//serhan may live in Atasehir
            System.out.println("or");//or
            //the below orElseGet requires Supplier
            System.out.println(p1.getName() + " may live in " +
                    p1.getAddress().orElseGet(()->new Address("Kadikoy")).getStreet());//serhan may live in Kadikoy
        }
        p1.getAddress().ifPresent((x) -> System.out.println("It is present" + x.getStreet()));//no output

        Person p2 = new Person("ufuk", Optional.ofNullable(new Address("Alibeykoy")));
        Stream<Person> personStream = Stream.of(p1, p2);
        personStream.filter(p -> p.getAddress().isPresent()).peek(p -> System.out.println(p.getName()
                + " lives in " + p.getAddress().get().getStreet())).forEach((p) -> {/*no effect to trigger terminal*/});//ufuk lives in Alibeykoy
    }

    public static class Person {
        private final String name;
        private Optional<Address> address = Optional.empty();

        public Person(String name, Optional<Address> address) {
            this.name = name;
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return this.address;
        }

        public void setAddress(Optional<Address> address) {
            this.address = address;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class Address {
        private String street;

        public Address(String street) {
            this.street = street;
        }

        public String getStreet() {
            return this.street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }
}
