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

## Enums

## Optionals


