# Chapter 5 - Working with streams

## Filtering and slicing

- **filter** method takes a *predicate* and returns a stream including all elements that match the predicate
- **distinct** method returns a stream of unique elements (according to hashCode and equals)
- **limit** method returns another stream that is no longer than the given size
- **skip** method returns a stream that discards the first *n* elements.