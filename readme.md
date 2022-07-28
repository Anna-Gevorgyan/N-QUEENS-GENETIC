N-QUEENS PROBLEM

The software provides a solution for N-Queens problem using Genetic Algorithm.The main purpose is to make the logic of the algorithm more understandable.In addition, the algorithm will be compared with other solutions provided by the group.

The class Chromosome is created to keep the states of the chessboard (positions of the queens on the board).

Methods: 

1. generateChromosome- takes an integer as an argument and generates one chromosome of the given size.
2. countFittness - takes a Chromosome as an argument and calculates its Fittnes(the number of attacking queens). 
3. crossover - takes two Chromosomes as an argument and returns two children Chromosomes for the new population
4. mutation - takes a Chromosome as an argument and with some possibility swaps two qeens of the taken chromosome. 
5. point and find - these are helper methods for the method crossover 
6. solveNQueens - the method that solves the problem by initializing a population, calculating the fitness of all individuals, sorting the population, calling crossover and mutation operations. The arguments of this method are population of Chromosomes, mutation probability and the number of maximum allowable iterations. 
7. main - start point 

How to use: 

1. You need Java 8+ to build and execute the source code
2. Or preferable to have Java 11 as the software was build with JDK 11