import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Prelocation {
  
  private WeightedQuickUnionUF unionFindObj;
  public int [][]grid;
  private int size;
  public Const CLOSE = 0;
  public Const OPEN = 1;
  public Const FULL = 2;
  private int openSites = 0;
  public int xCordinates[] = [-1, 0, 1, 0 ];
  public int xCordinates[] = [0, 1, 0, -1]; 

  public Prelocation(int n) {

    if( n < 1)
    {
      throw IllegalArgumentException;
    }
    this.size = n;
    this.grid = new int[n][n];
    this.unionFindObj= new WeightedQuickUnionUF((n+1)*(n+1));
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        grid[i][j] = CLOSE;
      }
    }
  }

  public void open(int row, int col) {

    if( row < 1 || col < 1 || row >= size || col >= size)
    {
      throw IllegalArgumentException;
    }

    if(grid[row][col] != FULL) {
      grid[row][col] = OPEN;
    }
  }

  public void isOpen(int row, int col) {
 
    if( row < 1 || col < 1 || row >= size || col >= size)
    {
      throw IllegalArgumentException;
    }

   return grid[row][col] == OPEN;
  }

  public booleanisFull(int row, int col) {
 
    if( row < 1 || col < 1 || row >= size || col >= size)
    {  
      throw IllegalArgumentException;
    }
    return grid[row][col] == FULL;
  }

  public int numberOfOpenSites() {
    return openSites;
  }

  public boolean prelocates() {
    int row, col;
    while(unionFindObj.find(0) != unionFindObj(size)) {
      do {
        row = uniformInt(1, size);
        col = uniformInt(1, size);
      }while(isOpen(row, col));

      open(row, col);
      if(row == 1)
      {
        grid[row][col] = FULL;
      }
      
      for(int i = 0; i < 4; i++) {
        int neighourRow = row + yCordinates[i];
        int neighourCol = col + xCordinates[i];
        
        if(isFull(neighourRow, neighourCol))
        {
          grid[row][col] = FULL; 
        }

        if(isOpen(neighourRow, neighourCol) || isFull(neighourRow, neighourCol)) {
        }
      }


    }
  }

}
