package eu.mithril.javafunctional.kata03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LambdasDeeperDiveTest {

    @Test
    @Tag("TODO")
    @Order(1)
    @DisplayName("use a BiFunction")
    public void useBiFunction() {

        final int integer1 = 3;
        final int integer2 = 4;

        // TODO:
        //  Replace the MyBiFunction class with a BiFunction named add.
        //  Replace the max with a sum() function.
        //  -------------------
        //  Hint: BiFunction<Integer, Integer, Integer> adder = (x, y) -> //do something
        //  Check API: java.util.function.BiFunction
        //  Check API: java.util.function.BiFunction.apply
        class MyBiFunction {
            int apply(int x, int y) {
                return Integer.max(x, y);
            }
        }
        MyBiFunction adder = new MyBiFunction();
        //  -------------------

        assertEquals(7, adder.apply(integer1, integer2),
                "The function should produce a result of 7");
    }

    @Test
    @Tag("TODO")
    @Order(2)
    public void partialFunctions() {
        // Partial application describes the conversion of a multi-argument function
        // into one that accepts fewer arguments, with values for the elided arguments
        // supplied in advance. It partially applies some arguments to a function,
        // returning a function with a signature that consists of the remaining arguments.
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;

        // TODO:
        //  Replace the below Function to create a partial function from the above BiFunction
        //  Create an addOne, which passes in a 1 and a int n to the add function and
        //  returns a partial application of 1, n.
        //  HINT: use the add.apply(?, ?)
        Function<Integer, Integer> addOne = n -> 0;

        // TODO:
        //  Replace the 6 with a call to addOne
        //  HINT: use the addOne.apply() to return a value of 7
        assertEquals(7, addOne.apply(6),
                "The function should return 7");
    }

    /**
     * @see Calculator - An interface with a single calculate() abstract method.
     */
    @Test
    @Tag("TODO")
    @Order(3)
    public void customSingleAbstractFunctions() {
        // TODO:
        //  Replace the below Function to a lambda
        //  Replace the zero with a valid function call
        Calculator addition = new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return 0;
            }
        };

        // TODO:
        //  Replace the below Function to a lambda
        //  Replace the zero with a valid function call
        Calculator subtraction = new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return 0;
            }
        };

        // TODO:
        //  Replace the below Function to a lambda
        //  Replace the zero with a valid function call
        Calculator multiplication = new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return 0;
            }
        };

        assertEquals(7, addition.calculate(6, 1), "The addition should return a value of 7");

        assertEquals(7, subtraction.calculate(10, 3), "The subtraction should return a value of 7");

        assertEquals(49, multiplication.calculate(7, 7), "The multiplication should return a value of 49");
    }

    @Test
    @Tag("TODO")
    @Order(4)
    public void sortNames() {
        List<Person> persons = List.of(Person.ALICE, Person.BOB, Person.CATHY, Person.EMILY);
        List<Person> expectedList = List.of(Person.EMILY, Person.BOB, Person.ALICE, Person.CATHY);

        // TODO:
        //  Replace the anonymous class with a lambda.
        //  Replace the postions of o2 and o1 to pass the test as well
        Comparator<Person> nameSorter = new Comparator<>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getLastName().compareTo(o1.getLastName());
            }
        };
        List<Person> actualList = new ArrayList<>();
        actualList.addAll(persons);
        Collections.sort(actualList, nameSorter);

        assertEquals(expectedList, actualList, "The sorted lists should match");
    }
}