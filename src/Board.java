import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class Board {
    public Row[] board;
    public int gRC =0, gCC= 0, gBC=0, gisV=0;
    public Board() {
        board = new Row[9];
        for (int i = 0; i < 9; i++) {
            board[i] = new Row();
        }
        solveFrom();
    }

    public void setData(int row, int col, int data) {
        board[row].set(col, data);
    }

    public void numUsed(){
        System.out.println(gRC+" "+gCC+" "+ gBC+" "+gisV);
    }
    private int findBox(int row, int col){
        int r = row/3;
        return   col/3+ 3*r;
    }

    private void testbox(){
        System.out.println(findBox(1, 1));
        System.out.println(findBox(1, 3));
        System.out.println(findBox(1, 6));
        System.out.println(findBox(3, 1));
        System.out.println(findBox(3, 3));
        System.out.println(findBox(3, 6));
        System.out.println(findBox(6, 1));
        System.out.println(findBox(6, 3));
        System.out.println(findBox(8, 8));
    }

    public Row getRow(int index) {
        gRC++;
        return board[index];
    }

    private Row getCol(int index) {
        gCC++;
        Row col = new Row();
        for (int i = 0; i < 9; i++) {
            col.set(i, board[i].get(index));
        }
        return col;
    }

    public int getData(int row, int col){
        return board[row].get(col);
    }
    private Row getBox(int index) {
        gBC++;
        int colInitial = index % 3;
        int rowInitial = index / 3;
        int row = rowInitial * 3;
        int col = colInitial * 3;
        Row box = new Row();
        for (int i = 0; i < 9; i++) {
            //System.out.println(row + " " + col);
            box.set(i, board[row].get(col));
            col++;
            if (col % 3 == 0) {
                col = colInitial*3;
                row++;
            }
        }
        return box;
    }

    private void testSolve(){
        int[] test = {4,8,3,9,2,1,6,5,7,9,6,7,3,4,5,8,2,1,2,5,1,8,7,6,4,9,3,5,4,8,1,3,2,9,7,6,7,2,9,5,6,4,1,3,8,1,3,6,7,9,8,2,4,5,3,7,2,6,8,9,5,1,4,8,1,4,2,5,3,7,6,9,6,9,5,4,1,7,3,8,2};
        int row = 0;
        for(int i = 0; i< 81; ){
            int col = i%9;
            setData(row,col,test[i]);
            i++;
            if(i%9 ==0) row++;
        }
    }

    private void solveFrom(){
        String B = "";
        try {
            File myObj = new File("src/Board.txt");

            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                B += data;
            }
            myReader.close();
            int row = 0;
            for(int i = 0; i<81; ){
                int col = i%9;
                setData(row,col,B.charAt(i)-48);
                i++;
                if(i%9 ==0) row++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //System.out.println(B);

    }

    public boolean isSolved() {
        for(int i = 0; i<9; i++){
            Row row = getRow(i);
            Row col = getCol(i);
            Row box = getBox(i);
            HashSet<Integer> r = new HashSet<Integer>();
            HashSet<Integer> c = new HashSet<Integer>();
            HashSet<Integer> b = new HashSet<Integer>();
            for(int j = 0; j< 9; j++){
                if(row.get(j) ==0||col.get(j)==0 || box.get(i) ==0){
                    return false;
                }
                else{
                    r.add(row.get(j));
                    c.add(col.get(j));
                    b.add(box.get(j));
                }
            }
            if(r.size()+c.size()+b.size() != 27){
                return false;
            }
            r.clear();
            c.clear();
            b.clear();
        }
        return true;
    }

    public boolean isValid(int row, int col, int data){
        gisV++;
        Row Row = getRow(row);
        Row Col = getCol(col);
        Row Box = getBox(findBox(row,col));
        for(int index = 0; index<9; index++){

            if((Row.get(index) == data)||(Col.get(index) == data )|| Box.get(index) == data){
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String output = "";
        for (Row row : board) {
            for (int i = 0; i < 9; i++) {
                output += row.get(i) + "\t";
            }
            output += "\n\n";
        }
        return output;
    }

    class Row {
        public int[] row;

        public Row(int initial) {
            row = new int[9];
            for (int i = 0; i < 9; i++) {
                row[i] = initial;
            }
        }

        public Row() {
            int initial = 0;
            row = new int[9];
            for (int i = 0; i < 9; i++) {
                row[i] = initial;
            }
        }

        public int get(int index) {
            return row[index];
        }

        public void set(int index, int value) {
            row[index] = value;
        }

        public String toString() {
            String output = "";
            for (int data : row) {
                output += data + " ";
            }
            return output;
        }
    }
}
