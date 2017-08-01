# Chapter 3 - Lambda expressions

## Lambdas in a nutshell

- lambda expressions let you represent behaviour or pass code in a concise way
- it doesn't have a name (anonymous)
- it has a list of parameters, a body and a return type
- it can list exceptions that can be thrown
- it can be passed around as an argument to a method or stored in a variable

```
   Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```

Basic syntax:  
```
   (parameters) -> expression

or  

   (parameters) -> { statements; }
```

- You can use lambdas in the context of a functional interface such as Predicate<T> or Comparator<T>. These are interfaces that specify exactly ONE abstract method.
- The interface may have default methods as long as there is only one abstract method.
- @FunctionalInterface annotation is used to indicate interfaces that are intended to be used as functional interfaces.
- Java 8 introdued several functional interfaces in package *java.util.function*

## java.util.function

- Predicate - carries out an true/false evaluation based on the object passed to it
```
   boolean test(T t);
```
- Consumer - takes an object and returns no result - used when you need to perform some operation on an object
```
   void accept(T t);
```
- Function - takes an object and returns another object - useful when mapping info from input to output for instance
```
   R apply(T t);
```
- Supplier - takes no arguments and returns an object as a result
- UnaryOperator - takes an object and returns an object of the same type
- BinaryOperator - takes 2 objects of the same type and returns one object of the same type
- BiPredicate - takes 2 objects an returns a boolean
- BiConsumer - takes 2 objects and returns no result
- BiFunction - takes 2 objects and returns another object

## Primitive Specializations

To avoid the memory performance overhead of autoboxing when passing primitives to Predicate, Consumer, Function, etc Java 8 provides sprecializations that take primitives as arguments. e.g. IntPredicate takes an int as an argument to the test method.

## Exceptions

None of the predefined functional interfaces allow for a checked exception to be thrown. You have 2 options if you need to throw a checked exception:
- define your own functional interface
- wrap the lambda in a try/catch block