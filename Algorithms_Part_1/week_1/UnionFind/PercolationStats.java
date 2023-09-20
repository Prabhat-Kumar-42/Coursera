import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

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
    
    if( n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }

    this.size = n; 
    this.trials = trials;
    this.trialsArray = new double[trials];
    
    int row, col;
    for(int i = 0; i < trials; i++) {
      this.percolationObj = new Percolation(size);
      while(!percolationObj.percolates()) {
        // System.out.println("Inside Row Col Assignment loop");
        do {
          row = StdRandom.uniformInt(1, size+1);
          col = StdRandom.uniformInt(1, size+1);
        }while(percolationObj.isOpen(row, col));
        // System.out.println("Outside Row Col Assignment loop");
        // System.out.println("Row is: " + row);
        // System.out.println("Col is: " + col);
        percolationObj.open(row, col);
      }
      int openSites = percolationObj.numberOfOpenSites();
      // System.out.println(openSites);
      trialsArray[i] = ((double)openSites)/(double)(size*size); 
    }
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
    PercolationStats obj = new PercolationStats( Integer.parseInt(args[0]), Integer.parseInt(args[1]));
;
    System.out.println("mean                    = " + obj.mean());
    System.out.println("stddev                  = " + obj.stddev());
    System.out.println("95% confidence interval = [" + obj.confidenceLo() + ", " + 
                       obj.confidenceHi() + "]");

  }
}
