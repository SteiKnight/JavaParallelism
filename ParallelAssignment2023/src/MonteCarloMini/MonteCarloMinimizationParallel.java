package MonteCarloMini;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// Parallel program to use Monte Carlo method to locate the minimum
// function. This is the parallel version based on Michelle Kuttel's sequential reference version.
// @author Delan Gono (steiknight) 2023, UCT.

public class MonteCarloMinimizationParallel extends RecursiveAction {

  static final int SEQUENTIAL_CUTOFF = 1000;
  static int min = Integer.MAX_VALUE; //Shared variable amongst all devices
  static int mainFinder = -1;
  int local_min = Integer.MAX_VALUE;
  int lo, hi;
  Search[] searches; // Array of searches

  //timers in milliseconds

  //Constructor
  MonteCarloMinimizationParallel(Search[] searches, int lo, int hi) {
    this.lo = lo;
    this.hi = hi;
    this.searches = searches;
  }

  public void compute() {
    if (hi - lo <= SEQUENTIAL_CUTOFF) {
      for (int id = lo; id < hi; id++) {
        local_min = searches[id].find_valleys();
        if ((!searches[id].isStopped()) && (local_min < min)) { //don't look at  those who stopped because hit exisiting path
          min = local_min;
          mainFinder = id;
        }
      }
    } else {
      MonteCarloMinimizationParallel left = new MonteCarloMinimizationParallel(
        searches,
        lo,
        (hi + lo) / 2
      ); //first half
      MonteCarloMinimizationParallel right = new MonteCarloMinimizationParallel(
        searches,
        (hi + lo) / 2,
        hi
      ); //second half
      left.fork(); //give first half to new thread
      right.compute();
      left.join();
    }
  }

  public static void main(String[] args) {
    int rows, columns; //grid size
    double xmin, xmax, ymin, ymax; //x and y terrain limits
    TerrainArea terrain; //object to store the heights and grid points visited by searches
    double searches_density; // Density - number of Monte Carlo  searches per grid position - usually less than 1!
    int num_searches; // Number of searches
    Search[] searches; // Array of searches
    Random rand = new Random(); //the random number generator

    System.out.println("Enter the test values:");
    Scanner sc = new Scanner(System.in);
    String data = sc.nextLine();
    String[] values_data = data.split(" ");

    rows = Integer.parseInt(values_data[0]);
    columns = Integer.parseInt(values_data[1]);
    xmin = Double.parseDouble(values_data[2]);
    xmax = Double.parseDouble(values_data[3]);
    ymin = Double.parseDouble(values_data[4]);
    ymax = Double.parseDouble(values_data[5]);
    searches_density = Double.parseDouble(values_data[6]);

    terrain = new TerrainArea(rows, columns, xmin, xmax, ymin, ymax);
    num_searches = (int) (rows * columns * searches_density);
    searches = new Search[num_searches];

    for (int i = 0; i < num_searches; i++) {
      searches[i] =
        new Search(i + 1, rand.nextInt(rows), rand.nextInt(columns), terrain);
    }

    //start timer
    int startTime = (int) System.currentTimeMillis();

    //all searches
      final ForkJoinPool fjPool = new ForkJoinPool();
      fjPool.invoke(
        new MonteCarloMinimizationParallel(searches, 0, num_searches)
      );
    //end timer
    int endTime = (int) System.currentTimeMillis();

    System.out.printf("Run parameters\n");
    System.out.printf("\t Rows: %d, Columns: %d\n", rows, columns);
    System.out.printf("\t x: [%f, %f], y: [%f, %f]\n", xmin, xmax, ymin, ymax);
    System.out.printf(
      "\t Search density: %f (%d searches)\n",
      searches_density,
      num_searches
    );

    /*  Total computation time */
    System.out.printf("Time: %d ms\n", endTime - startTime);
    int tmp = terrain.getGrid_points_visited();
    System.out.printf(
      "Grid points visited: %d  (%2.0f%s)\n",
      tmp,
      (tmp / (rows * columns * 1.0)) * 100.0,
      "%"
    );
    tmp = terrain.getGrid_points_evaluated();
    System.out.printf(
      "Grid points evaluated: %d  (%2.0f%s)\n",
      tmp,
      (tmp / (rows * columns * 1.0)) * 100.0,
      "%"
    );

    /* Results*/
    System.out.printf(
      "Global minimum: %d at x=%.1f y=%.1f\n\n",
      min,
      terrain.getXcoord(searches[mainFinder].getPos_row()),
      terrain.getYcoord(searches[mainFinder].getPos_col())
    );
  }
}
    // if (args.length != 7) {
    //   System.out.println(
    //     "Incorrect number of command line arguments provided."
    //   );
    //   System.exit(0);
    // }

    // rows = Integer.parseInt(args[0]);
    // columns = Integer.parseInt(args[1]);
    // xmin = Double.parseDouble(args[2]);
    // xmax = Double.parseDouble(args[3]);
    // ymin = Double.parseDouble(args[4]);
    // ymax = Double.parseDouble(args[5]);
    // searches_density = Double.parseDouble(args[6]);