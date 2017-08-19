# Chapter 6 - Collecting data with streams

Terminal operations consumer from a stream to produce a final result.

## java.util.stream.Collector

- **collect** method of Stream is a reduction operation that takes as an argument various recipes for accumulating elements of a stream into a summary result.
- **Collector** interface defines recipes.

## Predefined Collectors

Offer 3 main functionalities:
- Reduce and summarise a stream to a single value
- Group elements
- Partition elements

### Reducing and summarising

- **counting** method will count the number of elements in a stream
- **maxBy** method will determine the maximum value in a stream when supplied with a comparator
- **minBy** method will determine the minimum value in a stream when supplied with a comparator
- **summingInt**, **summingDouble**, **summingLong** methods sum together values from a stream
- **averagingInt**, **averagingDouble**, **averagingLong** methods average together values from a stream
- **summarizingInt**, **summarizingDouble**, **summarizingLong** methods determines sum, average, maximum, minimum of a value along with a count of the number of elements
- **joining** method concatenates strings into a single string with one version accepting a delimiter string

All the above methods are specializations of the **reducing** method. There are several forms of this but the most general takes 3 arguments:
- **Initial value**
- **Transformation function**
- **Aggregation function**

There are often multiple ways to perform the same operation. Choose the most specialised one that's general enough to solve the problem after exploring the largest number of solutions possible for the problem.

### Grouping

- **groupingBy** method takes a *classification function* and classifies the elements of a stream into different groups.
- **groupingBy** method that takes a second argument will use this to perform a further reduction operation on all elements in the stream classified into the same group
- **collectingAndThen** method is used to adapt the result returned by a collector to a different type
- **mapping** method is used adapt a collector accepting elements of a given type to one working on objects of a different type by applying a mapping function *prior* to accumulating them.

#### Multi-level grouping

Multi-level grouping is possible using multiple nested groupingBy to define multi-level criteria where n-level groupings have a result of n-level Map modeling an n-level tree structure.

### Partitioning

A special case of grouping.  

- **partitioningBy** method takes a predicate called a *partioning function* that classifies elements of a stream into either a true or a false group
- **partitioningBy** method that takes a second argument will use this to perform a further collector operation on all elements in the stream classified into the same partition
