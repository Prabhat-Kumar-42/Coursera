import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PrelocationStats {
  
  public Prelocation prelocationObj;
  public int trials;
  public int size;
  public double[] trialsArray;

  public PrelocationStats(int n, int trials) {
    this.prelocationObj = new Prelocation(n);
    this.trials = trials;
    this.size = n; 
    this.trialsArray = new double[trials];
  }
  
  public double mean() {

  }

  public double stddev() {

  }

  public double confidenceLo() {

  }
  
  public double confidenceHi() {

  }

  public static void main(String[] args) {
    PrelocationStats obj = new PrelocationStats( args[0], args[1]);
    for(int i = 0; i < obj.trials; i++) {
      while(!obj.prelocationObj.prelocates());
      int openSites = obj.prelocationObj.numberOfOpenSites();
       obj.trialsArray[i] = openSites/(obj.size*obj.size); 
    }
  }
}
