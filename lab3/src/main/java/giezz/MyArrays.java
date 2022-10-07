package giezz;

import giezz.exception.MyArrayDataException;
import giezz.exception.MyArraySizeException;

public class MyArrays {
    public void arraySizeCheck(String[][] arr) throws MyArraySizeException {
        if (arr.length != Main.ROWS)
            throw new MyArraySizeException("Array size is incorrect");
        else {
            for (int i = 0; i < Main.ROWS; i++) {
                if (arr[i].length != Main.COLUMNS) {
                    throw new MyArraySizeException("Array size is incorrect");
                }
            }
        }
        System.out.println("Array size is correct");
    }

    public int getArrayElementsSum(String[][] arr) throws MyArrayDataException, MyArraySizeException {
        int sum = 0;
        try {
            arraySizeCheck(arr);
        } catch (MyArraySizeException e) {
            throw new MyArraySizeException("Array size is incorrect");
        }
        for (int i = 0; i < Main.ROWS; i++) {
            for (int j = 0; j < Main.COLUMNS; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Array data is incorrect in at symbol '" + arr[i][j] + "' in row " + (i + 1) + " column " + (j + 1));
                }
            }
        }
        return sum;
    }
}
