import java.io.File;

class Main {
    public static void main(String[] args) {
        //Board solve = new Board();

        File b = new File("src/TestFiles/Board.txt");
        SudokuBoard sud = new SudokuBoard(b);
        //System.out.println(solve.toString()+"\n\n");
        long startTime = System.nanoTime();
        SudokuBoard solved = SudokuBackTracing.solveBoard(sud);
        long stopTime = System.nanoTime();
        System.out.println(solved.toString()+"\n\n");
        System.out.println((double)(stopTime - startTime)/1000000000+ " seconds");
        //solved.numUsed();
    }
}