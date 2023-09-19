// add algs4.jar to same directory as RandomWord.java
// to compile - javac -classpath ./algs4.jar RandomWord.java
// to run - java -classpath ./algs4.jar:. RandomWord < RandomWord_test.txt
// 
// 
// When giving a file as a input, It will automaticly show the output
// When providing custom values, ctrl-D must be pressed to get result
// 
// 
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
  public static void main(String[] args) {
    
    String champion = "";
    
    double probablity = 1;
    while (!StdIn.isEmpty()) {
      String inputString = StdIn.readString();
      // System.out.println("Input String is : " + input_string);
      // System.out.println("Prob is : " + 1/probablity);
      // System.out.println("bernoulli function gives : " + bool);
      if (StdRandom.bernoulli(1/probablity)) {
        champion = inputString;
        // System.out.println("champion changed");
        // System.out.println("new champion is : " + champion);
      }
      // System.out.println();
      probablity++;
    }
    StdOut.println(champion);
  }
}

