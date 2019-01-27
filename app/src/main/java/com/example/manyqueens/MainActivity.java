package com.example.manyqueens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // shadow of board
    // 0 means save
    // 1 means not save
    // 2 means Queen

    int boardRow = 8;
    int boardCol = 8;
    int[][] board = new int[boardRow][boardCol];
    int queen = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    void foo (View v){

        // get click button by id


        Button b = findViewById(v.getId());



        // get location

        String s = v.getTag().toString();
        char m = s.charAt(0);
        char n = s.charAt(1);

        int r =Character.getNumericValue(m);
        int c =Character.getNumericValue(n);

        check(r, c, b);


        // change button's background,
        // toggles the queen’s presence
        // https://stackoverflow.com/questions/23357706/how-to-check-which-current-image-resource-is-attached-to-imageview-in-android-xm
        // https://stackoverflow.com/questions/15976601/comparing-the-background-resources-of-buttons


        /*

        if (b.getBackground().getConstantState() == getResources().getDrawable( R.drawable.dark).getConstantState())
        {
            b.setBackgroundResource(R.drawable.qd);
        }
        else if (b.getBackground().getConstantState() == getResources().getDrawable( R.drawable.light).getConstantState())
        {
            b.setBackgroundResource(R.drawable.ql);
        }
        else if (b.getBackground().getConstantState() == getResources().getDrawable( R.drawable.qd).getConstantState())
        {
            b.setBackgroundResource(R.drawable.dark);
        }
        else {
            b.setBackgroundResource(R.drawable.light);
        }


        */



    }



    void check (int r, int c, Button b){
        // A[r][c] == 0
        // put queen

        if(board[r][c] == 0){

            setAll(r, c, 1);

            board[r][c] = 2;

            putQueen(b);

            queen--;

            checkQueen(queen);


        }

        else if(board[r][c] == 2){

            setAll(r, c, 0);

            board[r][c] = 0;

            takeQueen(b);

            queen++;


        }

        else{
            Toast.makeText(this, "Under Attack!", Toast.LENGTH_LONG).show();
            Log.v("MY_TAG","Under Attack");

        }







    }


    void setAll (int r, int c, int s){
        // set row

        for(int i = 0; i < boardCol; i++ ){
            board[r][i] = s;
        }

        // set col

        for(int i = 0; i < boardRow; i++ ){
            board[i][c] = s;
        }

        // set top right


        for(int i=r, j=c; i >=0 && j < boardCol; i--,j++ ){
            board[i][j] = s;
        }


        // set bottom right

        for(int i=r, j=c; i < boardRow && j < boardCol; i++,j++ ){
            board[i][j] = s;
        }

        // set top left

        for(int i=r, j=c; i >= 0 && j >= 0  ; i--,j-- ){
            board[i][j] = s;
        }

        // set bottom left

        for(int i=r, j=c; i < boardRow && j >= 0 ; i++,j-- ){
            board[i][j] = s;
        }




    }


    //put Queen on board

    void putQueen(Button b){
        if (b.getBackground().getConstantState() == getResources().getDrawable( R.drawable.dark).getConstantState())
        {
            b.setBackgroundResource(R.drawable.qd);
        }
        else
        {
            b.setBackgroundResource(R.drawable.ql);
        }
    }

    // take Queen on board

    void takeQueen(Button b){

        if (b.getBackground().getConstantState() == getResources().getDrawable( R.drawable.qd).getConstantState())
        {
            b.setBackgroundResource(R.drawable.dark);
        }
        else {
            b.setBackgroundResource(R.drawable.light);
        }

    }

    void checkQueen(int q){
        if (q == 0){

            Toast.makeText(this, "WOW! You Won! Start new game by click New Game", Toast.LENGTH_LONG).show();
            Log.v("MY_TAG","WOW");

        }
    }


    // restart game

    void restart (View v){
        this.recreate();
    }
}
