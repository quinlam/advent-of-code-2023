# advent-of-code-2022

This package was created using the [Advent of Code Kotlin Template][template] which kind of sucked, so I made some enhancements.

You can generate new files for a given day by running the following command and setting the day number to the one you need;

```shell
./gradlew newDay -Pday=8 
```

This will generate a default implementation for the `Day<num>` class that you can modify easily.

The `main.kt` class uses reflection to run all the classes that implement `Day`. You can specify the exact day to run if you so choose.

## Goals

My goal for this advent of code is to write readable and non-brute force code, but not necessarily highly optimized. I would favor readability and maintainability
over optimization. I am also looking to favor functional operations for the solutions.

[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template
