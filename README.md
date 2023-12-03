# Java is not dead YET!

## Immutability

5 simple rules:
* Don’t provide any methods that modify the object’s state
* Ensure that the class can’t be extended
* Make all fields final
* Make all fields private.
* Ensure exclusive access to any mutable components.

Prons:
* Immutable objects are simple. An immutable object can be in exactly one state, the state in which it was
created. Make sure that all constructors establish class invariants, then it is
guaranteed that these invariants will remain true for all time, with no further effort
on your part or on the part of the programmer who uses the class.
* Immutable objects are inherently thread-safe; they require no synchronization. They cannot be corrupted by multiple threads accessing them concurrently. 
* An immutable class can provide static factories that cache frequently requested instances

Cons:
* They require separate object for each distinct value (memory consumption)

## In memory cache
* A cache is an area of local memory that holds a copy of frequently accessed data that is otherwise expensive to get or compute. Examples: result of a query to a database, disk file or a report.
* Consider simple implementation instead of using external libraries

## Enums

## Optionals
* Wrapper
* Null-references have been historically introduced in programming languages to generally signal the absence of a value.
* Java 8 introduces the class java.util.Optional<T> to model the presence or absence of a value.
* You can create Optional objects with the static factory methods **Optional.empty**, **Optional.of**, and **Optional.ofNullable**.
* Using Optional can help you design better APIs in which, just by reading the signature of a method, users can tell whether to expect an optional value.
