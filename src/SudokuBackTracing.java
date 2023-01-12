public class SudokuBackTracing implements SolveAlgorithm {
    static int row = 1;
    static int col = 1;

    static SudokuBoard toSolve;
    public static SudokuBoard solveBoard(SudokuBoard input)
    {
        toSolve = input;
        SudokuBoard solved = toSolve.getSubSet(1,1,9,9);
        //solved.toString();

        int numToCheck=1;
        while(row+col<=18 &&!solved.isSolved()){
            if(row+col==18) System.exit(0);
            //System.out.println(row+" "+ col+" "+ numToCheck+"\n"+ solved.toString());
            if(toSolve.getData(row, col) !=0){
                //System.out.println("test1");
                advance();
            }
            else{
                if(numToCheck>9){
                    //System.out.println("test2");
                    solved.setData(row,col,toSolve.getData(row,col));
                    retreat();
                    numToCheck = solved.getData(row, col)+1;
                    solved.setData(row, col, 0);
                }
                else if(solved.isValid(row,col,numToCheck)){
                    //System.out.println("test3");
                    solved.setData(row, col, numToCheck);
                    numToCheck = 1;
                    advance();
                }
                else {
                    numToCheck++;
                    //System.out.println("test4");
                }
            }
        }
        return solved;
    }

    private static void advance(){
        if((row)*(col)<81) {
            if (col < 9) {
                col++;
            } else {
                col = 1;
                row++;
            }
        }
    }

    private static void retreat(){
        if(row+col>1){
            if (col > 1) {
                col--;
            } else {
                col = 9;
                row--;
            }
            while(toSolve.getData(row, col)!=0) {
                if(row+col>1){
                    if (col > 1) {
                        col--;
                    } else {
                        col = 9;
                        row--;
                    }
                }
            }
        }
    }
}
