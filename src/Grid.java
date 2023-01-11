public interface Grid {

    //set a specific spot on the grid as data
    public void setData(int row, int col, int data) throws IndexOutOfBoundsException;

    public int getData(int row, int col) throws IndexOutOfBoundsException;

    public Grid getRow(int row) throws IndexOutOfBoundsException;

    public Grid getCol(int col) throws IndexOutOfBoundsException;

    public Grid getSubSet(int rowTopLeft,int colTopLeft, int rowBotRight, int colBotRight)throws Exception;


    public String toString();
}
