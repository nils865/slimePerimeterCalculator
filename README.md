# Slime Chunk Finder

This searches for the best place to build a Slime Farm with a given Seed Radius

Outputs in a custom .scf file, just like a text file

## Arguments

- `Color` `true` or `false` enables the colored output

> default: true
>
- `updateTime` `time in ms` chooses the interval of uptdates

> default: 60000

- `start` starts the finder

## Build Instructions

1. install maven dependencies with

    ```bash
    mvn install dependency:copy-dependencies
    ```

    this only has to be done once if the dependencies dont change

2. build your jar

    ```bash
    mvn package
    ```
