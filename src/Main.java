import java.io.File;

class Main {
    public static void main(String[] args) {
        File b = new File("src/TestFiles/Board.txt");
        SudokuBoard sud = new SudokuBoard(b);
        System.out.println(sud);

        long startTime = System.nanoTime();
        SudokuBoard solved = SudokuBackTracing.solveBoard(sud);
        long stopTime = System.nanoTime();


        System.out.println(solved+"\n\n");
        if(solved.isSolved()) System.out.println("Successfully solved in ");
        else System.out.println("failed ");
        System.out.println((double)(stopTime - startTime)/1000000000+ " seconds\n");

    }
}