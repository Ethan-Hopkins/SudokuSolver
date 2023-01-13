public interface Grid {

    //set a specific spot on the grid as data
    void setData(int row, int col, int data) throws IndexOutOfBoundsException;

    int getData(int row, int col) throws IndexOutOfBoundsException;

    Grid getRow(int row) throws IndexOutOfBoundsException;

    Grid getCol(int col) throws IndexOutOfBoundsException;

    Grid getSubSet(int rowTopLeft,int colTopLeft, int rowBotRight, int colBotRight)throws Exception;


    String toString();
}
