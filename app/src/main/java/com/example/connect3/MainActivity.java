package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: blue,  1:grey,  2:empty

    int[] gameState= {2, 2, 2, 2, 2, 2 ,2 ,2 ,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter= (ImageView) view;

        int counterTapped = Integer.parseInt(counter.getTag().toString());

        if(gameState[counterTapped]==2 && gameActive) {

            gameState[counterTapped] = activePlayer;

            Log.i("tag", counter.getTag().toString());

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.grey);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(500);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {

                    String winner = " ";

                    if (activePlayer == 0)
                        winner = "Grey";

                    else
                        winner = "Blue";


                    Button playAgain = findViewById(R.id.button);
                    TextView textViewWinner = findViewById(R.id.textViewWinner);

                    textViewWinner.setText(winner + " just WON!!");

                    textViewWinner.setVisibility(View.VISIBLE);

                    playAgain.setVisibility(View.VISIBLE);

                    gameActive = false;

                }

            }
        }

    }

    public void playAgain(View view){

        Button playAgain = findViewById(R.id.button);

        TextView textViewWinner = findViewById(R.id.textViewWinner);

        textViewWinner.setVisibility(View.INVISIBLE);

        playAgain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++){

            gameState[i] = 2;
        }

        activePlayer = 0;
        gameActive = true;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
