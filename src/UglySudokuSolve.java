public class UglySudokuSolve implements SolveAlgorithm {

    private static Board solve;
    private static int row = 0, col = 0, checkNum = 0;

    static SudokuBoard solveBoard(SudokuBoard input) {
        solve = input;
        SudokuBoard  solved = new SudokuBoard();
        while(row+col<=16 &&!solved.isSolved()){
            if(solve.getData(row+1, col+1) !=0){
                advance();
            }
            else{
                if(checkNum>9){
                    retreat();
                    checkNum = solved.getData(row+1, col+1)+1;
                    solved.setData(row+1, col+1, 0);
                }
                else if(solved.isValid(row+1,col+1,checkNum)){
                    solved.setData(row+1, col+1, checkNum);
                    checkNum = 1;
                    advance();
                }
                else checkNum++;
            }

        }
        return solved;
    }

    private static void advance(){
        if((row+1)*(col+1)<81) {
            if (col < 8) {
                col++;
            } else {
                col = 0;
                row++;
            }
        }
    }

    private static void retreat(){
        if(row+col>0){
            if (col > 0) {
                col--;
            } else {
                col = 8;
                row--;
            }
            while(solve.getData(row+1, col+1)!=0) {
                if(row+col>0){
                    if (col > 0) {
                        col--;
                    } else {
                        col = 8;
                        row--;
                    }
                }
            }
        }

    }
}
