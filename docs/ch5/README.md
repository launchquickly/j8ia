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

## java.util.Optioinal

Used to represent the existence or absence of a value