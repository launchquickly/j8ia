# Chapter 5 - Working with streams

## Filtering and slicing

- **filter** method takes a *predicate* and returns a stream including all elements that match the predicate
- **distinct** method returns a stream of unique elements (according to hashCode and equals)
- **limit** method returns another stream that is no longer than the given size
- **skip** method returns a stream that discards the first *n* elements.

## Mapping

Selecting information from objects.  

- **map** method takes a *function* as an argument, which is then applied to each element, mapping it to a new element
- **flatMap** method concatenates all the generated streams into a single stream

## Finding and matching

- **anyMatch** method will determine if any element in a stream matches a predicate
- **allMatch** method will determine if all elements in a stream match a predicate
- **findAny** method returns an arbitrary element in the stream
- **findFirst** method returns the first element from a stream that has *encounter order* such as a List

Using **findAny** as opposed to **findFirst** is less constraining when using parallel streams.


### Short-circuiting evaluation

Some operations don't have to process the whole stream to produce a result. These include *allMatch*, *noneMatch*, *findFirst* and *findAny* as well as *limit*. 

### java.util.Optioinal

Used to represent the existence or absence of a value

## Reducing

Reduce queries combine all the elements in a stream repeatedly to produce a single value. In functional programming-languages this is referred to as a *fold*.

- **reduce** method takes 2 arguments: initial value and BinaryOperator<T> to combine 2 elements and produce a new value
- **reduce** method that takes 1 argument but returns an Optional object to indicate a result may be absent

Using *reduce* instead of step-by-step iteration has the benefit of being able to be run in parallel. This does however mean that the lambdas passed to reduce cannot change state.

## Stateless vs stateful

It is important to recognise that some stream operations such as *sorted* or *distinct* are stateful which can be problematic if the data stream is large or infinite.

## Numeric Streams

*IntStream*, *DoubleStream*, and *LongStream* are *primitive stream specializations* of the stream interface that avoid boxing costs by having specialized elements of int, double and long respectively. They also have new methods to perform common numeric reductions, along with methods to convert back to streams of objects.

### Specialized methods

- sum
- max
- min
- average

- *boxed* method converts numeric stream to a Stream

Primitive specialisations of Optional which may be used by the primitive stream specialisations:
- OptionalInt
- OptionalDouble
- OptionalLong

The **orElse** of these objects can be used to explicitly provide a default value if desired.

### Numeric ranges

- *range* - creates range of values excluding end value
- *rangeClosed* - creates range of values including end value

### Mapping to numeric stream

This is done via the *mapToInt*, *mapToDouble*, and *mapToLong* methods of streams

## Building Streams

- Stream.of(.., .., ..)
- Stream.empty()
- Files.lines 
- Stream.iterate
- Stream.generate
