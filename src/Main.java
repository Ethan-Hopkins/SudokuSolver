class Main {
    public static void main(String[] args) {
        Board solve = new Board();
        System.out.println(solve.toString()+"\n\n");
        Solve x = new Solve(solve);
        long startTime = System.nanoTime();
        Board solved = x.solveBoard();
        long stopTime = System.nanoTime();
        System.out.println(solved.toString()+"\n\n");
        System.out.println((double)(stopTime - startTime)/1000000000+ " seconds");
        solved.numUsed();
    }
}