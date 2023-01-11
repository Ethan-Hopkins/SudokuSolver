import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.io.File;
import java.util.*;

public class SudokuTests {

    Board bd;

    @Before
    public void setup() {
    }

    @Test
    public void setAndGetCorrectly(){
        bd = new Board(5, 6);
        bd.setData(1,1, 11);
        bd.setData(5, 6, 56);
        bd.setData(2,2, 22);
        assertEquals("get (1,1) should equal 11",11,bd.getData(1,1));
        assertEquals("get (5,6) should equal 56",56,bd.getData(5,6));
        assertEquals("get (2,2) should equal 22",22,bd.getData(2,2));

    }

    @Test
    public void setAndGetCorrectlyLarge(){
        for(int rows = 1; rows <= 10; rows++) {
            for(int cols = 1; cols <= 10; cols++) {
                bd = new Board(rows,cols);
                for(int checkRows = 1; checkRows<rows; checkRows++){
                    for(int checkCols = 1; checkCols<cols;checkCols++){
                        bd.setData(checkRows,checkCols,checkRows*10+checkCols);
                    }
                }
                for(int checkRows = 1; checkRows<rows; checkRows++){
                    for(int checkCols = 1; checkCols<cols;checkCols++){
                        assertEquals("should display (checkRows*10)+checkCols",checkRows*10+checkCols,bd.getData(checkRows,checkCols));
                    }
                }
            }
        }
    }

    @Test
    public void throwsIndexOutOfBoundsException(){
        bd = new Board(5, 6);
        boolean thrown = false;
        try{
            bd.getData(0,1);
        }
        catch(IndexOutOfBoundsException e){
            thrown = true;
        }
        assertTrue("This should have been turned to true by the throw", thrown);
        thrown = false;
        try{
            bd.getData(5,7);
        }
        catch(IndexOutOfBoundsException e){
            thrown = true;
        }
        assertTrue("This should have been turned to true by the throw", thrown);
    }
    @Test
    public void getRowCorrectly(){
        bd = new Board(5, 6);
        bd.setData(1,1, 11);
        bd.setData(1, 6, 56);
        bd.setData(1,2, 22);
        bd.setData(2,2, 33);
        Board expected = new Board(1,6);
        expected.setData(1,6,56);
        expected.setData(1,1,11);
        assertFalse("This should equal the first row in the board",bd.getRow(1).equals(expected));
        //System.out.println(expected.toString());
        //System.out.println(bd.getRow(1).toString());
        expected.setData(1,2,22);
        //System.out.println(expected.toString());
        //System.out.println(bd.getRow(1).toString());
        assertTrue("This should equal the first row in the board",bd.getRow(1).equals(expected));
        boolean thrown = false;
        try{
            bd.getRow(6);
        }
        catch(IndexOutOfBoundsException e){
            thrown = true;
        }
        assertTrue("This should have been turned to true by the throw", thrown);
    }

    @Test
    public void getCol(){
        bd = new Board(5, 6);
        bd.setData(1,1, 11);
        bd.setData(1, 6, 56);
        bd.setData(1,2, 22);
        bd.setData(5,6, 33);
        Board expected = new Board(5,1);
        expected.setData(1,1,56);
        //System.out.println(expected.toString());
        //System.out.println(bd.getCol(6).toString());
        assertFalse("This should equal the sixth col in the board",bd.getCol(6).equals(expected));
        expected.setData(5,1,33);
        //System.out.println(expected.toString());
        //System.out.println(bd.getCol(6).toString());
        assertTrue("This should equal the sixth col in the board",bd.getCol(6).equals(expected));
        boolean thrown = false;
        try{
            bd.getCol(0);
        }
        catch(IndexOutOfBoundsException e){
            thrown = true;
        }
        assertTrue("This should have been turned to true by the throw", thrown);
    }

    @Test
    public void getSubSet(){
        bd = new Board(5, 6);
        bd.setData(1,1, 11);
        bd.setData(1, 2, 12);
        bd.setData(1,3, 13);
        bd.setData(3,3, 33);
        bd.setData(1, 4, 14);
        bd.setData(3,5, 35);
        Board sub = bd.getSubSet(1,1,3,3);
        Board expected = new Board(3,3);
        expected.setData(1,1, 11);
        expected.setData(1, 2, 12);
        expected.setData(1,3, 13);
        expected.setData(3,3, 33);
        assertFalse(bd.equals(expected));
        assertTrue(expected.equals(sub));
        for(int col =1; col<=3; col++){
            assertTrue("cols do not equal",expected.getCol(col).equals(sub.getCol(col)));
        }
        for(int row =1; row<=3; row++){
            assertTrue("rows do not equal",expected.getCol(row).equals(sub.getCol(row)));
        }

    }

    @Test
    public void testEquals(){
        bd = new Board(5, 6);
        bd.setData(1,1, 11);
        bd.setData(1, 2, 12);
        bd.setData(1,3, 13);
        bd.setData(3,3, 33);
        bd.setData(1, 4, 14);
        bd.setData(3,5, 35);
        Board expected = new Board(3,3);
        expected.setData(1,1, 11);
        expected.setData(1, 2, 12);
        expected.setData(1,3, 13);
        expected.setData(3,3, 33);
        assertFalse("these are not the same size should return false",bd.equals(expected));
        bd = new Board(3, 3);
        bd.setData(1,1, 11);
        bd.setData(1, 2, 12);
        bd.setData(1,3, 13);
        bd.setData(3,3, 33);
        assertTrue("these should now be the same", bd.equals(expected));

    }

    @Test
    public void testReturnCorrectBoxNum(){
        SudokuBoard Sud = new SudokuBoard();
        assertEquals(1,Sud.findBox(1,1));
        assertEquals(9,Sud.findBox(9,9));
        assertEquals(5,Sud.findBox(5,5));
        assertEquals(5,Sud.findBox(4,5));
        assertEquals(4,Sud.findBox(4,1));
    }

    @Test
    public void testReturnCorrectBox(){
        SudokuBoard Sud = new SudokuBoard();
        Sud.setData(5,5, 9);
        Sud.setData(9,9, 3);
        Board expected = new Board(3,3);
        expected.setData(2,2 ,9);
        assertTrue(Sud.getBox(5,5).equals(expected));
        expected.setData(2,1 ,9);
        assertFalse(Sud.getBox(5,5).equals(expected));
        expected = new Board(3,3);
        assertFalse(Sud.getBox(8,8).equals(expected));
        expected.setData(3,3 ,3);
        assertTrue(Sud.getBox(8,8).equals(expected));
    }

    @Test
    public void testRejectNot1thru9(){
        SudokuBoard Sud = new SudokuBoard();
        Sud.setData(5,5, 9);
        assertEquals(9, Sud.getData(5,5));
        Sud.setData(5,6,10);
        //System.out.println(Sud.toString());
        assertEquals(0,Sud.getData(5,6));
         //WHY NOT PRINTING!!!
    }

    @Test
    public void loadFromFile(){
        File b = new File("src/TestFiles/Board.txt");
        Board bd = new Board(b);
        assertEquals(5, bd.getData(1,1));
        assertEquals(7, bd.getData(9,8));
        assertEquals(0, bd.getData(9,9));
    }

    @Test
    public void testContains(){
        bd = new Board(5, 6);
        bd.setData(1,1, 5);
        bd.setData(1, 2, 6);
        bd.setData(1,3, 7);
        assertTrue(bd.contains(5));
        assertFalse(bd.contains(3));
    }

    @Test
    public void testIsValid(){
        File b = new File("src/TestFiles/Board.txt");
        SudokuBoard sud = new SudokuBoard(b);
        assertTrue(sud.isValid(1,1,3));
        assertFalse(sud.isValid(5,8,9));
    }
    @Test
    public void testIsSolved(){
        File b = new File("src/TestFiles/SolvedBoard.txt");
        SudokuBoard sud = new SudokuBoard(b);
        System.out.println(sud.toString());
        assertTrue(sud.isSolved());
        sud.setData(1,1,1);
        assertFalse(sud.isSolved());
        sud.setData(1,1,0);
        assertFalse(sud.isSolved());
    }
}
