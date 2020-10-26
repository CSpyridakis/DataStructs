# DataStructs
This repository contains two separate exercises that both of them are about an educational introduction in Data Structures and Files I developed during my studies.

### [First](./First)
The first exercise has to do with Dictionaries, Indexes, and storing data to memory. Specifically, we had to read an unknown number of files, create a paged Index containing the file, and the location in 'bytes' from the beginning of the file that each word appears (a page of the Index has multiple appearances but refers only to a specific vocable) repeatedly. Then, generate a Dictionary containing each unique vocable and the page in the Index that their information exists. Finally, storing Dictionary and Index to non-volatile-memory and let the user search information for these files using generated records.

The source files of this exercise are available [here](./First/src/).

### [Second](./Second)
The second exercise has to do with dynamic hashing both in the main memory and disk. In this task, we had to create a hash table, insert/search/delete elements to it, repeat this procedure for a different number of items, and compare the results for each experiment.

The source files of this exercise are available [here](./Second/src/).


## Usage
Move to the src/ directory of each exercise, then follow the instructions below:
1. In both of those projects in order to create .class files that contain java bytecode run in a UNIX-based machine:
```
make
```

2. In order to execute bytecode of each project on Java Virtual Machine (JVM) run:
```
make run
```

3. To clean executables and generated files
```
make clean
```

### Known Bug
- Fix OutOfBoundsException that sometimes occurs when handling a large number of elements.
