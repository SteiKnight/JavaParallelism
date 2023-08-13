####Parallel Monte Carlo Function Optimization####
This repository contains a parallelized Java implementation of the Monte Carlo function optimization algorithm using the Fork/Join framework. The goal is to find the minimum of a two-dimensional mathematical function within a specified range.

####Compilation#####
Compile the Java source files using the provided Makefile:

```bash
make

#### Running the Program ####
To run the parallel Monte Carlo optimization program, use the following command: java MonteCarloMinimizationParallel rows columns xmin xmax ymin ymax search_density

Replace the following placeholders:
    rows: Number of rows in the discrete grid representing the function.
    columns: Number of columns in the discrete grid.
    xmin, xmax: Boundaries of the rectangular area for the terrain along the x-axis.
    ymin, ymax: Boundaries of the rectangular area for the terrain along the y-axis.
    search_density: Number of searches per grid point.

Example command:
<java MonteCarloMinimizationParallel 100 100 -2 2 -2 2 10>

#### Output ####
After running the program, it will output the following information:
    Run parameters (rows, columns, xmin, xmax, ymin, ymax, search density)
    Total time taken for execution
    Number of grid points visited/evaluated
    Location and value of the global minimum found

#### Report and Results ####
For a detailed analysis of the parallelization and benchmarking results, please refer to the assignment report report.pdf.