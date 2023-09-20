import edu.princeton.cs.algs4.StdStats;

public class PrecolationStats {
  
  private int trials;
  private int size;
  private double[] trialsArray;
  private Precolation precolationObj;
  
  private void printTrialsArray() {
    System.out.println("trails array is : ");
    for(int i = 0; i < trials; i++) {
      System.out.print(trialsArray[i] + " ");
    }
    System.out.println();
  }
  public PrecolationStats(int n, int trials) {
    this.precolationObj = new Precolation(n);
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

    PrecolationStats obj = new PrecolationStats( Integer.parseInt(args[0]),
                                                 Integer.parseInt(args[1]));
    for(int i = 0; i < obj.trials; i++) {
      obj.precolationObj.precolates();
      int openSites = obj.precolationObj.numberOfOpenSites();
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
