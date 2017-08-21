# Chapter 7 - Parallel data processing and performance

When using streams one of the most important benefits is the possibility of executing a pipeline of operations on these collections that automatically makes use of multiple cores.

## Parallel streams

A *parallel* stream is a stream that splits its elements into multiple chunks, processing each chunk with a different thread.

- **parallel** method of Stream turns a sequential stream into a parallel one following the invocation of *parallel*. 
- **sequential** method of Stream turns a parallel stream into a sequential one following the invocation of *sequential*

**Note:** the last call to *parallel* or *sequential* wins and affects the pipeline globally so multiple invocations does not make sense.

- Internally parallel streams use the *ForkJoinPool*
- Some stream operations are more parallelizable than others
- Specifically the Stream.*iterate* is hard to split into chunks that can be executed in parallel as the input of one function depends on the output of the previous
- Generated boxed objects which need to be unboxed as part of anS operations are an overhead
- Marking a stream as parallel can make it slower if not used in the right cases
- Side effects that change mutable state cannot be used when processing streams in parallel

- **LongStream.rangeClosed** is better than Stream.iterate as:
  - works on primitive *long* numbers directly so no boxing/unboxing overhead
  - produces ranges of numbers which can easily be split into independent chunks
  
  **Important:** choosing the right data structure is often the more important than parallelizing the algorithm that uses them  
  
  Parallelization process requires that you:
  - recursively partition the stream
  - assign the reduction operation of each substream to a different thread
  - combine the results of these operations in a single value
  
  **Important:** the work done in parallel on another core needs to take longer than the time required to transfer the data from one core to another for there to be a benefit
  
### Effectively using parallel streams

- **measure** - when choosing between sequential and parallel streams always check their performance with an appropriate benchmark
- watch out for boxing as it hurts performance. Consider using IntStream, LongStream, DoubleStream if appropriate
- some operations naturally perform worse on a parallel stream, i.e. limit, findFirst that rely on order. Consider whether methods like **findAny** or **unordered** can be used to work round this
- consider total computational cost of pipeline of operations on a stream. Particularly the approximate cost of processing one element. The higher this is the better chance of good performance when using a parallel stream when the number of elements is significant
- for small amounts of data a parallel stream is unlikely to be worthwhile
- consider how well the data structure underlying the stream decomposes - ArrayList - good, LinkedList - poorly
- characteristics of a stream and how intermediate operations through the pipeline modify them should be considered
- does the terminal operation have a cheap or expensive merge step
