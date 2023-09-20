import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Scanner;

import edu.princeton.cs.algs4.StdRandom;

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
    this.unionFindObj= new WeightedQuickUnionUF((size)*(size));
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
    
    boolean isFullFlag = false;
    grid[row][col] = OPEN;
    openSites++;
    if(row == 1) {
      isFullFlag = true;
      unionFindObj.union(0, size*(row-1) + col );
      System.out.println("root of 0 = " + unionFindObj.find(0));
      System.out.println("grid[" + row + "][" + col + "] is " +
                          unionFindObj.find(size*(row-1) + col ));
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
        if(isFullFlag && !isFull(neighourRow, neighourCol)) {
          unionFindObj.union(size*(row-1) + col, size*(neighourRow-1) + neighourCol);
          System.out.println("Assigning current as neighbour parent");
          System.out.println("current parent = " + unionFindObj.find(size*(row-1) + col));
          System.out.println("Assigning current as neighbour parent");
        } else {
          unionFindObj.union(size*(neighourRow-1) + neighourCol, size*(row-1) + col);
          System.out.println("Assigning neighbour as parent");
        }
        System.out.println("current parent = " + unionFindObj.find(size*(row-1) + col));
      }
    }
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
    return unionFindObj.find(0) == unionFindObj.find(size*size - 1);
  }

  public static void main(String[] args) {
    int row, col;
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter size");
    Percolation obj = new Percolation(sc.nextInt());
    while(!obj.percolates())
    {
      do {
        row = StdRandom.uniformInt(1, obj.size);
        col = StdRandom.uniformInt(1, obj.size);
      }while(obj.isOpen(row, col));
      obj.open(row, col);
    }
  }
}
