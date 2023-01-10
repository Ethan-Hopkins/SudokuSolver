public class Solve {
    private Board solve;
    private int row = 0, col = 0, checkNum = 0;
    public Solve(Board input){
        solve = input;
    }

    public Board solveBoard(){
        Board solved = new Board();
        while(row+col<=16 &&!solved.isSolved()){
            if(solve.getData(row, col) !=0){
                advance();
            }
            else{
                if(checkNum>9){
                    retreat();
                    checkNum = solved.getData(row, col)+1;
                    solved.setData(row, col, 0);
                }
                else if(solved.isValid(row,col,checkNum)){
                    solved.setData(row, col, checkNum);
                    checkNum = 1;
                    advance();
                }
                else checkNum++;
            }

        }
        return solved;
    }
    private void advance(){
        if((row+1)*(col+1)<81) {
            if (col < 8) {
                col++;
            } else {
                col = 0;
                row++;
            }
        }
    }

    private void retreat(){
        if(row+col>0){
            if (col > 0) {
                col--;
            } else {
                col = 8;
                row--;
            }
            while(solve.getData(row, col)!=0) {
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
    private void sleep(){
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }
    }

}
