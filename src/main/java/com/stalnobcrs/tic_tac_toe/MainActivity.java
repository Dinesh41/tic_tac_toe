package com.stalnobcrs.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;//0->red 1->yellow
    int taptag;
    boolean gameisactive=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void drop(View view)
    {
        Log.i("Information","Drop funtion entered");
        ImageView img=(ImageView) view;
        taptag=Integer.parseInt(img.getTag().toString());
        String winner = null;
        if(gameisactive) {
            img.setTranslationY(-1000f);
            if (activePlayer == 0 && gamestate[taptag] == 2) {
                img.setImageResource(R.drawable.red);
                gamestate[taptag] = activePlayer;
                activePlayer = 1;
            } else if (activePlayer == 1 && gamestate[taptag] == 2) {
                img.setImageResource(R.drawable.yellow);
                gamestate[taptag] = activePlayer;
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).rotationBy(100).setDuration(300);
            for (int[] winpos : winningpositions) {
                if (gamestate[winpos[0]] == gamestate[winpos[1]] &&
                        gamestate[winpos[1]] == gamestate[winpos[2]] &&
                        gamestate[winpos[0]] != 2) {
                    if (gamestate[winpos[0]] == 0) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }
                }
                else
                {
                    boolean gameover=true;
                    for(int state:gamestate)
                    {
                        if(state==2)
                            gameover=false;
                    }
                    if(gameover)
                    {
                        winner="No one";
                    }
                }

            }
        }
            if (winner != null) {
                gameisactive=false;
                LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);
                TextView res = (TextView) findViewById(R.id.result);
                res.setText("Winner is "+winner);
                if (winner == "Red") {
                    layout.setBackgroundColor(RED);
                } else if(winner=="Yellow") {
                    layout.setBackgroundColor(YELLOW);
                }else
                {
                    layout.setBackgroundColor(WHITE);
                }
                layout.setVisibility(View.VISIBLE);
            }


    }
    public void playAgain(View view)
    {
        gameisactive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playagain);
        layout.setVisibility(View.INVISIBLE);
        GridLayout gr=(GridLayout)findViewById(R.id.gr);
        for(int i=0;i<gr.getChildCount();i++) {
            ((ImageView) gr.getChildAt(i)).setImageResource(0);
        }
        activePlayer=0;
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
