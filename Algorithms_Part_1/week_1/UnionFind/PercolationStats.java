import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  
  private int trials;
  private int size;
  private double[] trialsArray;
  private Percolation percolationObj;
  
  private void printTrialsArray() {
    System.out.println("trails array is : ");
    for(int i = 0; i < trials; i++) {
      System.out.print(trialsArray[i] + " ");
    }
    System.out.println();
  }
  public PercolationStats(int n, int trials) {
    this.percolationObj = new Percolation(n);
    this.trials = trials;
    this.size = n; 
    this.trialsArray = new double[trials];
  }
  
  public double mean() {
    return StdStats.mean(trialsArray);
  }

  public double stddev() {
    return StdStats.stddev(trialsArray);
  }

  public double confidenceLo() {
    return mean() - (1.96*stddev()/Math.sqrt(trials));
  }
  
  public double confidenceHi() {
    return mean() + (1.96*stddev()/Math.sqrt(trials));

  }

  public static void main(String[] args) {

    // System.out.println("size is " + Integer.parseInt(args[0]));
    // System.out.println("trail is " + Integer.parseInt(args[1]));

    PercolationStats obj = new PercolationStats( Integer.parseInt(args[0]),
                                                 Integer.parseInt(args[1]));
    for(int i = 0; i < obj.trials; i++) {
      obj.percolationObj.percolates();
      int openSites = obj.percolationObj.numberOfOpenSites();
      // System.out.println(openSites);
      obj.trialsArray[i] = ((double)openSites)/(double)(obj.size*obj.size); 
    }

    // obj.printTrialsArray();
    System.out.println("mean                    = " + obj.mean());
    System.out.println("stddev                  = " + obj.stddev());
    System.out.println("95% confidence interval = [" + obj.confidenceLo() + ", " + 
                       obj.confidenceHi() + "]");

  }
}
