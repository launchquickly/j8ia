# Chapter 1 - Java 8: why should you care

- Behaviour parameterisation
- Streams
- Parallelism
- Default methods

## Behaviour parameterisation

The ability to pass a piece of code as an argument to a method as a first-class value.  

**method references** :: syntax indicates that the method should be used as a value that can be passed around:

```
  File[] hiddenFiles = new File(".").listFiles(File::isHidden);
```

these are 'named' methods.

**lambdas** (or anonymous functions) can also be used to communicate intent or in cases where the behaviour is only used once:

```
filterApples(inventory, (Apple a) -> "green".equals(a.getColor()) );
```

## Streams

A stream is a sequence of data items that are conceptually produced one at a time. The output stream of one program could well be the input stream of another.

The Stream API processes data in a very different way to that of the Collections API using **internal iteration**. Stream processing encourages that elements within a Stream can be processed in parallel.

## Parallelism

By writing side-effect free code and using the Streams API it is possible to expolit parallel programming on multicore processors.

## Default methods

Allow the writing of **more evolvable** interfaces. They allow an interface to contain method signatures that an implementing class may not provide an implementation for. In these cases the missing implementation is provided as part of the interface.



