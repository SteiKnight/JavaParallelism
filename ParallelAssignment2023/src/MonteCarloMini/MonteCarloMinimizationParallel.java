package MonteCarloMini;

import java.util.Random;

// Parallel program to use Monte Carlo method to locate the minimum
// function. This is the parallel version based on Michelle Kuttel's sequential reference version.
// @author Delan Gono (steiknight) 2023, UCT.

public class MonteCarloMinimizationParallel implements Runnable {

  static final boolean DEBUG = false;
  static long startTime = 0;
  static long endTime = 0;

  //timers in milliseconds
  private static void tick() {
    startTime = System.currentTimeMillis();
  }

  private static void tock() {
    endTime = System.currentTimeMillis();
  }

  int rows, columns; //grid size
  double xmin, xmax, ymin, ymax; //x and y terrain limits
  TerrainArea terrain; //object to store the heights and grid points visited by searches
  double searches_density; // Density - number of Monte Carlo  searches per grid position - usually less than 1!
  int num_searches; // Number of searches
  Search[] searches; // Array of searches
  Random rand = new Random(); //the random number generator

  //Constructor
  MonteCarloMinimizationParallel(
    int rows,
    int columns,
    double xmin,
    double xmax,
    double ymin,
    double ymax,
    double searches_density
  ) {
    this.rows = rows;
    this.columns = columns;
    this.xmin = xmin;
    this.xmax = xmax;
    this.ymax = ymax;
    this.ymin = ymin;
    this.searches_density = searches_density;
    terrain = new TerrainArea(rows, columns, xmin, xmax, ymin, ymax);
    num_searches = (int) (rows * columns * searches_density);
    searches = new Search[num_searches];
  }

  public void run() {}
}
