import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Board implements Grid{

    private final int rowSize;
    private final int colSize;
    private final int[][] board;

    public Board(int rowSize,int colSize){
        if(rowSize>0) this.rowSize = rowSize;
        else this.rowSize = 1;
        if(colSize>0) this.colSize = colSize;
        else this.colSize = 1;
        board = new int[rowSize][colSize];
    }
    public Board(File InputFile){
        //this method will fuck up with ints larger than 9
        int rowLen = 0;
        int colLen = 0;
        StringBuilder boardFill = new StringBuilder(" ");
        try {
            Scanner fileReader = new Scanner(InputFile);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().replace(" ","");
                //System.out.println(line);
                boardFill.append(line);
                String[] intData = line.split("");
                colLen=0;
                rowLen++;
                for(String ignored : intData){

                    colLen++;
                    //System.out.println(ignored+ ' '+ colLen);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //System.out.println(rowLen+ " " + colLen);
        if(rowLen>0) this.rowSize = rowLen;
        else this.rowSize = 1;
        if(colLen>0) this.colSize = colLen;
        else this.colSize = 1;
        board = new int[rowSize][colSize];

        String[] data = boardFill.toString().trim().split("");
        int dataIndex = 0;
        for(int row = 1; row<=rowSize; row++){
            for(int col = 1; col<=colSize; col++){
                //System.out.println(dataIndex+ " "+ data[dataIndex]);
                this.setData(row,col,Integer.parseInt(data[dataIndex]));
                dataIndex++;
            }
        }


    }
    @Override
    public void setData(int row, int col, int data) throws IndexOutOfBoundsException {
        if((row>0&& row<=rowSize) && (col>0 && col<=colSize)) {
            board[row-1][col-1] = data;
        }
        else{
            throw new IndexOutOfBoundsException("row or col is not within bounds unable to set");
        }
    }

    @Override
    public int getData(int row, int col) throws IndexOutOfBoundsException {
        if((row>0&& row<=rowSize) && (col>0 && col<=colSize)) {
            return board[row-1][col-1];
        }
        else{
            throw new IndexOutOfBoundsException("row or col is not within bounds unable to getData");
        }

    }

    @Override
    public Board getRow(int row) throws IndexOutOfBoundsException{
        if(row>0&& row<=rowSize) {
            Board retRow = new Board(1,colSize);
            for (int col = 1; col <= colSize; col++) {
                retRow.setData(1,col,this.getData(row,col));
            }
            return retRow;
        }
        else{
            throw new IndexOutOfBoundsException("row or col is not within bounds unable to getRow");
        }
    }

    @Override
    public Board getCol(int col) {
        if(col>0&& col<=colSize) {
            Board retCol = new Board(rowSize,1);
            for (int row = 1; row <= rowSize; row++) {
                retCol.setData(row,1,this.getData(row,col));
            }
            return retCol;
        }
        else{
            throw new IndexOutOfBoundsException("row or col is not within bounds unable to getRow");
        }
    }

    public int getRowSize(){
        return rowSize;
    }

    public int getColSize(){
        return colSize;
    }
    @Override
    public Board getSubSet(int rowTopLeft, int colTopLeft, int rowBotRight, int colBotRight){
        if(rowTopLeft<1 || rowBotRight>rowSize || colTopLeft<1|| colBotRight>colSize){
            throw new IndexOutOfBoundsException("one of the variables exceeds the bounds of the original array");
        }
        int rowLength = colBotRight - colTopLeft + 1, colLength = rowBotRight-rowTopLeft +1;
        if(rowLength<1|| colLength<1){
            System.exit(1);
        }
        Board subSet = new Board(rowLength, colLength);

        for(int row = rowTopLeft; row<= rowBotRight; row++){
            for(int col = colTopLeft; col<=colBotRight; col++){
                subSet.setData((row-rowTopLeft)+1,(col-colTopLeft)+1,this.getData(row, col));
            }
        }
        return subSet;
    }
    public boolean equals(Board boardToCheck){
        if(this.rowSize!= boardToCheck.rowSize && this.colSize != boardToCheck.colSize) {
            return false;
        }
        else{
            for(int row = 1; row<= rowSize; row++){
                for(int col = 1; col <= colSize; col++){
                    if(this.getData(row,col)!=boardToCheck.getData(row,col)){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public boolean contains(int data){
        for(int row = 1; row<= rowSize; row++){
            for(int col = 1; col<= colSize; col++){
                if(this.getData(row,col)==data){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        StringBuilder output = new StringBuilder("Board:\n");
        for(int i = 1; i<= rowSize; i++){
            for( int j = 1; j<= colSize; j++){
                output.append(this.getData(i, j)).append(" ");
            }
            output.append("\n");
        }
        return output.toString();
    }
}
