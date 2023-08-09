package MonteCarloMini;

// Parallel program to use Monte Carlo method to locate the minimum
// function. This is the parallel version based on Michelle Kuttel's sequential reference version.
// @author Delan Gono (steiknight) 2023, UCT.

public class MonteCarloMinimizationParallel {

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
}
