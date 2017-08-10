# Chapter 4 - Introducing streams

- Declarative - Streams let you manipulate collections of data in a declarative way
- Composable - You chain together building-block operations into data processing pipelines
- Parallelizable - They can be processed in *parallel transparently*

# java.util.stream.Stream

A stream is a sequence of elements from a source that supports data processing operations.

Generating a stream from an ordered collection preserves the ordering.  

Patterns:
- filtering
- slicing
- finding
- matching
- mapping
- reducing

- Pipelining - many stream operations return a stream which allows them to be chained together.
- Internal iteration
- Computed on demand - producer - consumer relationship when processing
- Traversable only once

## methods/operations

- filter - conditionally exclude elements form the stream
- map - transform an element into another one or extract information
- limit - truncate stream to set number of elements
- collect - convert Stream into another form

There are 2 types of stream operations:
- *intermediate operations* - operations that can be connected
- *terminal operations* - closes a stream

### Intermediate operations

- return another stream
- can be connected to form a query
- lazy - intermediate operations do not perform any processing until a terminal operation is invoked on the stream pipeline - allows operations to be merged and processed in a single pass by the terminal operation
  - short-circuiting
  - loop fusion - merging separate operations
  
filter, map, limit, sorted, distinct are intermediate operations.  
  
### Terminal operations

Produce a result from a stream pipeline

forEach, count, collect are terminal operations.


