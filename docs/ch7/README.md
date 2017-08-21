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

## fork/join framework

- designed to recursively spilt a parallelizable task into smaller tasks
- implementation of *ExecutorService* interface which distributes subtasks to worker threads in a thread pool called *ForkJoinPool*
- tasks for the pool are subclasses of *RecursiveTask<R>*, where R is the type of the result produced by the parallelized task
- this involves implementing a single method
```
         protected abstract R compute();
```
- this method defines:
  - the logic of splitting the task into subtasks
  - the algorithm to produce the result of a single subtask when it's no longer possible or convenient to further divide it
- pseudocode would be similar to 
```
   if (task is small enough or no longer divisible)
     compute task sequentially
   else
     split task in two subclasses
     call this method recursively possibly further splitting each subtask
     wait for completion of all subtasks
     combing the results of each subtask
```
- Having more than one instance of *ForkJoinPool* does not normally make sense and typically is a singleton

### Best practices for using fork/join framework

- **join** method blocks so should only be called *after* the computation of both subtasks have been started
- **invoke** method should never be used within a *RecursiveTask*
- **fork** method on a subtask is the way to schedule it on a *ForkJoinPool* but should not be called on the left and right task. Instead call *compute* on one of them to reuse the same thread and avoid overhead caused by unnecessary allocation of a further task on the pool
- call to *compute* occurs in a different thread so cannot be debugged using and IDE debugger
- fork/join may not be faster than a sequential alternative

**Work stealing** - forking a large number of fine-grained tasks is usually a good choice as it means that each subtask takes exactly the same amount of time. This allows tasks to more or less be evenly divided on all threads in teh *ForkJoinPool*. Work stealing allows these tasks to be redistributed and balanced among worker threads were one or more threads complete their work befor the other threads.

## java.util.Spliterator

*splitable iterator* - defines how a parallel stream can split the data it traverses

- **tryAdvance** method sequentially consume elements of the *Spliterator* one by one, returning true if there are more elements to be traversed
- **trySplit** method is used to partition off some of its elements to a second *Spliterator*
- **estimateSize** method provides an estimation of the number of elements remaining to be traversed
- **characeristics** method returns and int encoding the set of characteristics of the Spliterator itself
  - ORDERED
  - DISTINCT
  - SORTED
  - SIZED
  - NONNULL
  - IMMUTABLE
  - CONCURRENT
  - SUBSIZED
  
- they are used to traverse the elements of a stream in parallel
- Java 8 provide a default *Spliterator* implementation for all data structures in the Collection framework
- the splitting process is recursive, with null returned from **trySplit** when it can be split no more
