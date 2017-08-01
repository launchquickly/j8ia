# Chapter 2 - Passing code with behaviour parameterization

## Behaviour parameterisation

- A block of code that can be made available without executing it
- Helps to make your code less verbose
- Helps you to maintain DRY (Don't Repeat Yourself) code

## Predicate

- A function that returns a boolean
- Often used when implementing the 'Strategy pattern'

## Soring with a Comparator

sorting a list with a lambda
'''
inventory.sort(
  (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
'''

## Runnable

lambda expression being used to run a thread
'''
final Thread t = new Thread(() -> System.out.println("Hello world"));
'''

### GUI event handling

'''
button.setAction((ActionEvent event) -> label.setText("Sent!!"));
'''

