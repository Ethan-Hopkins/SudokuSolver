import java.io.File;

public class SudokuBoard extends Board{

    public SudokuBoard(){
        super(9,9);
    }

    public SudokuBoard(File input){
        super(input);
    }

    public void setData(int row, int col, int data) throws IndexOutOfBoundsException {
        if(data<0||data>9);
        else super.setData(row,col,data);
    }

    public Board getBox(int row, int col){
        int boxNum = findBox(row, col);
        //System.out.println("bnum "+boxNum);
        Board box;
        switch(boxNum){
            case 1: box = super.getSubSet(1,1,3,3);
                    break;
            case 2: box = super.getSubSet(1,4,3,6);
                    break;
            case 3: box = super.getSubSet(1,7,3,9);
                    break;
            case 4: box = super.getSubSet(4,1,6,3);
                    break;
            case 5: box = super.getSubSet(4,4,6,6);
                    break;
            case 6: box = super.getSubSet(4,7,6,9);
                    break;
            case 7: box = super.getSubSet(7,1,9,3);
                    break;
            case 8: box = super.getSubSet(7,4,9,6);
                    break;
            case 9: box = super.getSubSet(7,7,9,9);
                    break;

            default: box = new Board(1,1);
        }
        return box;
    }

    public int findBox(int row, int col){
        if(row<1||col<1||row>9||col>9){
            throw new IndexOutOfBoundsException();
        }
        int r = (row-1)/3;
        return   ((col-1)/3+ 3*r)+1;
    }

    public boolean isValid(int row, int col, int data){
        if(data>9 || data < 1) return false;
        int holdDataVal = this.getData(row,col);
        this.setData(row,col,0);
        Board Row = this.getRow(row);
        Board Col = this.getCol(col);
        Board Box = this.getBox(row,col);
        this.setData(row,col,holdDataVal);
        return !Row.contains(data) && !Col.contains(data) && !Box.contains(data);
    }

    public boolean isSolved(){
        for(int row = 1; row<= getRowSize(); row++){
            for(int col = 1; col<= getColSize(); col++){
                if(!this.isValid(row,col,this.getData(row,col))){
                    return false;
                }
            }
        }
        return true;
    }

}
