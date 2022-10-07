package giezz;

import giezz.exception.MyArrayDataException;
import giezz.exception.MyArraySizeException;

public class Main {
    public static final int ROWS = 4;
    public static final int COLUMNS = 4;


    public static void main(String[] args) {
        MyArrays MyArrays = new MyArrays();

        String[][] array = new String[][] {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "b", "3", "4"}
        };

        try {
            System.out.println(MyArrays.getArrayElementsSum(array));
        } catch (MyArrayDataException | MyArraySizeException e) {
            e.printStackTrace();
        }
    }
}