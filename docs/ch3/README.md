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


