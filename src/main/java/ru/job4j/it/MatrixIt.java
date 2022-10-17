package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;
    private int counter = 0;
    private int row_has = 0;
    private boolean no_first;
    private int column_has = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
     }


    @Override
    public boolean hasNext() {

        for (row_has = row; row_has < data.length; row_has++ )
            if ( data[row_has][column_has ] > 0 )
            {
                return true;

            }
        {
            for (column_has  = column; column_has  < data[column].length; column_has ++ ){
//                column = column_has ;

        }
        if ( data[row_has][column_has] > 0 )
        {
            return true;
        }
        else
        {
            return false;
        }


    }

    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        counter = 0;
        for (int i = row; i < data.length; i++ ){
            row = i;
            if ( data[row][column] > 0)
            {
                counter ++;

            }
            if ( ( data[row][column] > 0 ) && ( counter > 1  || no_first == false )  )
            {
                no_first =true;
                return data[row][column];

            }

            }
        {
            for (int j = column; j  < data[column].length; j++ ){
                if ( data[row][column] > 0 )
                {
                    counter ++;

                }
                column = j;
                if ( ( data[row][column] > 0 ) && ( counter > 1  || no_first == false )  )
                {
                no_first =true;
                return data[row][column];

        }
        }

    }
        return data[row][column];
    }

    }



