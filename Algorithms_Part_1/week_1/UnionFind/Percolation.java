import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  
  private WeightedQuickUnionUF unionFindObj;
  private int [][]grid;
  private int size;
  private int CLOSE = 0;
  private int OPEN = 1;
  private int openSites = 0;
  private int xCordinates[] = {-1, 0, 1, 0 };
  private int yCordinates[] = {0, 1, 0, -1 }; 
  
  private void reset()
  {
    // System.out.println("Resetting grids");
    this.openSites = 0;
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        grid[i][j] = CLOSE;
      }
    }
  }

  public Percolation(int n) {

    if( n < 1) {
      // return;
      throw new IllegalArgumentException();
    }
    this.size = n+1;
    this.grid = new int[size][size];
    reset();
    // System.out.println("Constructed Grid with size " + size);
    // System.out.println("Constructed Union ojectwith size " + size*size);
  }

  public void open(int row, int col) {
    // System.out.println("Opening grid[" + row +"][" + col + "]");
    if( row < 1 || col < 1 || row >= size || col >= size) {
      // return;
      throw new IllegalArgumentException();
    }
    grid[row][col] = OPEN;
    openSites++;
  }

  public boolean isOpen(int row, int col) {
    // System.out.println("Check if Open grid[" + row +"][" + col + "] = "); 
    if( row < 1 || col < 1 || row >= size || col >= size) {
      // return false;
      throw new IllegalArgumentException();
    }
    // System.out.println((grid[row][col] == OPEN));
    return grid[row][col] == OPEN;
  }

  public boolean isFull(int row, int col) {
    // System.out.println("Checking if full grid[" + row +"][" + col + "]");
    if( row < 1 || col < 1 || row >= size || col >= size) {  
      // return false;
      throw new IllegalArgumentException();
    }
    return unionFindObj.find((row-1)*size + col) == 0;
  }

  public int numberOfOpenSites() {
    return openSites;
  }

  public boolean percolates() {
    reset();
    this.unionFindObj= new WeightedQuickUnionUF((size)*(size));
    int row, col;
    boolean isFullFlag;
    while(unionFindObj.find(0) != unionFindObj.find(size*size - 1)) {
      isFullFlag = false;
      // System.out.println("Inside Row Col Assignment loop");
      do {
        row = StdRandom.uniformInt(1, size);
        col = StdRandom.uniformInt(1, size);
      }while(isOpen(row, col));
      // System.out.println("Outside Row Col Assignment loop");

      // System.out.println("Row is: " + row);
      // System.out.println("Col is: " + col);

      open(row, col);
      if(row == 1) {
        isFullFlag = true;
        unionFindObj.union(0, size*(row-1) + col );
        // System.out.println("Assigned Parent, Parent is : " + 
        //                 unionFindObj.find(size*(row-1) + col));
      }
      else if(row == size-1)
      {
        unionFindObj.union(size*size - 1, size*(row-1) + col);
        // System.out.println("Assigned Parent, Parent is : " + 
        //                    unionFindObj.find(size*(row-1) + col));
      }
      
      // System.out.println("Checking Neighbours");
      for(int i = 0; i < 4; i++) {
        int neighourRow = row + yCordinates[i];
        int neighourCol = col + xCordinates[i];
         
        if(neighourRow < 1 || neighourRow >= size || neighourCol < 1 || neighourCol >= size) {
          continue;
        }
        if(isOpen(neighourRow, neighourCol)) {
          // System.out.println("Merged neighbour and cell");
          if(isFullFlag) {
            unionFindObj.union(size*(row-1) + col, size*(neighourRow-1) + neighourCol);
          } else {
            unionFindObj.union(size*(neighourRow-1) + neighourCol, size*(row-1) + col);
          }
        }
      }
    }
    return true;
  }
}
