package com.jarus.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoegame.R;

public class MainActivity extends AppCompatActivity {

    // 0 = x; 1 = o; 2 = Empty;
    private int player = 0;
    private int gameState[] = {2, 2, 2,
                              2, 2, 2,
                              2, 2, 2};

    private int winnigPosition[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                     {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                     {0, 4, 8}, {2, 4, 6}};
    private boolean activeGame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tap (View view)
    {
        ImageView image = (ImageView) view;
        int tappedTag = Integer.parseInt(image.getTag().toString());

        if (gameState[tappedTag] == 2 && activeGame)
        {
            gameState[tappedTag] = player;
            if (player == 0)
            {
                image.setImageResource(R.drawable.xx);
                player = 1;
            }
            else
            {
                image.setImageResource(R.drawable.oo);
                player = 0;
            }

            // Winning Possiblity
            for (int winnigPosition[] : winnigPosition)
            {
                if (gameState[winnigPosition[0]] == gameState[winnigPosition[1]] && gameState[winnigPosition[1]] == gameState[winnigPosition[2]]
                && gameState[winnigPosition[0]] != 2)
                {
                    // Someone has Won
                    activeGame = false;
                    String winner;
                    if (player == 1)
                        winner = "X";
                    else
                        winner = "O";

                    // Making Visible TextView and Button
                    TextView info = (TextView) findViewById(R.id.info);
                    info.setText(winner + " has Won!");
                    info.setVisibility(View.VISIBLE);

                    Button reset = (Button) findViewById(R.id.playAgain);
                    reset.setVisibility(View.VISIBLE);
                }
            }

            // If No one Wins
            int count = 0;
            for (int i = 0; i < gameState.length; i++)
            {
                if (gameState[i] == 2)
                    count++;
            }

            if (count == 0)
            {
                TextView info = (TextView) findViewById(R.id.info);
                info.setText("None has Won!");
                info.setVisibility(View.VISIBLE);

                Button reset = (Button) findViewById(R.id.playAgain);
                reset.setVisibility(View.VISIBLE);
            }
        }

    }

    public void playAgain(View view)
    {
        // Removing TextView and Button
        TextView info = (TextView) findViewById(R.id.info);
        info.setVisibility(View.INVISIBLE);

        Button reset = (Button) findViewById(R.id.playAgain);
        reset.setVisibility(View.INVISIBLE);

        // Removing all images
        GridLayout board = (GridLayout) findViewById(R.id.tictactorboard);
        for (int i = 0; i < board.getChildCount(); i++)
        {
            ImageView img = (ImageView) board.getChildAt(i);
            img.setImageDrawable(null);
        }

        // Resetting all values
        for (int i = 0; i < gameState.length; i++)
            gameState[i] = 2;
        player = 0;
        activeGame = true;

    }
}
