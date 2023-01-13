import java.util.Stack;

public class SudokuBackTracing implements SolveAlgorithm {
    public static SudokuBoard solveBoard(SudokuBoard input)
    {
        Stack<position> remaining = new Stack<>(), added = new Stack<>();
        for(int row = 9; row>=1; row--){
            for(int col = 9; col>=1; col--){
                if(input.getData(row,col)==0) remaining.push(new position(row, col, 1));
            }
        }
        if(remaining.empty() && input.isSolved()) return input;
        //System.out.println(remaining);
        position current = remaining.pop();

        while(!remaining.empty()||!input.isSolved()){
            //System.out.print(current+ "\n"+ input );
            // check (lastchecked) if it is valid
            // if it is add lastchecked to input at row col
            // also  add current to added and pop new current from remaining
            if(input.isValid(current.getRow(), current.getCol(), current.getCheck())){
                //System.out.print("test1");
                input.setData(current.getRow(),current.getCol(), current.getCheck());
                current.incCheck();
                added.push(current);
                if(remaining.empty()) return input;
                current = remaining.pop();
            }
            //if its not either
            // add 1 to current .lastchecked
            // or if lastchecked = 9
            //  set lastchecked = 0 add current to remaining pop from added
            else{
                if(current.getCheck()>=9){

                    input.setData(current.row, current.col, 0);
                    current.setCheck(1);
                    remaining.push(current);
                    if(!added.empty()) {
                        current = added.pop();
                        input.setData(current.row, current.col, current.check);
                    }
                    else System.exit(1);
                }
                else{
                    current.incCheck();
                }
            }
            //System.out.println();
        }

        return input;
    }


    private static class position{
        private final int row;
        private final int col;
        private int check;
        public position(int row, int col, int check){
            this.row = row;
            this.col = col;
            this.check = check;
        }

        public int getRow(){
            return row;
        }

        public int getCol(){
            return col;
        }

        public int getCheck(){
            return check;
        }

        public void incCheck(){
            check++;
        }

        public void setCheck(int value){
            check = value;
        }

        public String toString(){
            return row+ " " + col + " "+ check;
        }
    }
}
