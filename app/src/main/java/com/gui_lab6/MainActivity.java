package com.gui_lab6;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.*;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    static final String Player_1_SYMBOL = "X";
    static final String Player_2_SYMBOL = "O";
    boolean player1Turn = true;
    byte[][] board = new byte[3][3];
    static final byte EMTY_VALUE = 0;

    static final byte Player_1_VALUE = 1;
    static final byte Player_2_VALUE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table = findViewById(R.id.table);


        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(new CellListener(i, j));
            }
        }
    }


    class CellListener implements View.OnClickListener {

        int row, column;

        public CellListener(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void onClick(View V) {//View V is our button
            byte WhichPlayer = EMTY_VALUE;

            if (board[row][column] != EMTY_VALUE) {
                Toast.makeText(MainActivity.this, "Cell is already occupied", Toast.LENGTH_SHORT).show();
                return;
            }

            if (player1Turn) {
                ((Button) V).setText(Player_1_SYMBOL);
                board[row][column] = Player_1_VALUE;
                WhichPlayer = Player_1_VALUE;
            } else {
                ((Button) V).setText(Player_2_SYMBOL);
                board[row][column] = Player_2_VALUE;
                WhichPlayer = Player_2_VALUE;

            }
            player1Turn = !player1Turn;

            int GameState = GameEnded(row, column, WhichPlayer);

            if (GameState > 0) {
                Toast.makeText(MainActivity.this, "Player " + GameState + "WON", Toast.LENGTH_SHORT).show();
                setBoardEnable(false);
            }
        }


        public int GameEnded(int row, int column, byte WhichPlayer) {
            // we will chech column
            boolean win = true;
            for (int r = 0; r < 3; r++) {
                if (board[r][column] != WhichPlayer) {

                    win = false;
                    break;
                }
            }

            if (win) {
                return WhichPlayer;
            }


            //rows

            return -1;
            //diagonal
        }

        void setBoardEnable(boolean Enable) {
            TableLayout tbl = findViewById(R.id.table);

            for (int i = 0; i < 3; i++) {
                TableRow row = (TableRow) tbl.getChildAt(i);
                for (int j = 0; j < 3; j++) {
                    Button btn = (Button) row.getChildAt(j);
                    btn.setEnabled(Enable);
                }
            }
        }

        public boolean newGame(MenuItem item) {
            setBoardEnable(true);
            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {
                    board[i][j] = EMTY_VALUE;
                }
            }

            TableLayout tbl = findViewById(R.id.table);

            for (int i = 0; i < 3; i++) {
                TableRow row = (TableRow) tbl.getChildAt(i);
                for (int j = 0; j < 3; j++) {
                    Button btn = (Button) row.getChildAt(j);
                    btn.setText("");
                }
            }

            return true;
        }


    }}
