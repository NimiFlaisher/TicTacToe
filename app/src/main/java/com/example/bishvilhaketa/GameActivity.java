package com.example.bishvilhaketa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private final List<int[]> comboList = new ArrayList<>();
    private char [] boxPositions = {'E','E','E','E','E','E','E','E','E'};
    private char playerTurn = 'X';
    private int totalSelectedBoxes = 0;
    private ImageView currentPlayerImage, currentTurnBanner;
    private ImageView box0, box1, box2, box3, box4, box5, box6, box7, box8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        currentTurnBanner = findViewById(R.id.bannerImage);
        currentPlayerImage = findViewById(R.id.testest);
        currentPlayerImage.setImageResource(R.drawable.x);
        currentTurnBanner.setImageResource(R.drawable.xplay);

        comboList.add(new int[]{0,1,2});
        comboList.add(new int[]{3,4,5});
        comboList.add(new int[]{6,7,8});
        comboList.add(new int[]{0,3,6});
        comboList.add(new int[]{1,4,7});
        comboList.add(new int[]{2,5,8});
        comboList.add(new int[]{0,4,8});
        comboList.add(new int[]{2,4,6});

        box0 = findViewById(R.id.box0);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);
        box7 = findViewById(R.id.box7);
        box8 = findViewById(R.id.box8);

        box0.setImageDrawable(null);
        box1.setImageDrawable(null);
        box2.setImageDrawable(null);
        box3.setImageDrawable(null);
        box4.setImageDrawable(null);
        box5.setImageDrawable(null);
        box6.setImageDrawable(null);
        box7.setImageDrawable(null);
        box8.setImageDrawable(null);

        box0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Yo", "Yo");
                if (boxPositions[0] == 'E'){
                    boxPositions[0] = playerTurn;
                    box0.setImageDrawable(currentPlayerImage.getDrawable());
                    nextPlayerTurn();
                    checkWinner();
                }
            }
        });
    }

    private void nextPlayerTurn(){
        totalSelectedBoxes++;
        if (playerTurn == 'X'){
            playerTurn = 'O';
            currentPlayerImage.setImageResource(R.drawable.o);
            currentTurnBanner.setImageResource(R.drawable.oplay);
        }
        else{
            playerTurn = 'X';
            currentPlayerImage.setImageResource(R.drawable.x);
            currentTurnBanner.setImageResource(R.drawable.xplay);
        }
    }

    private void checkWinner(){
        if (totalSelectedBoxes > 1){
            for (int [] combo: comboList) {
                if (boxPositions[combo[0]] == boxPositions[combo[1]] && boxPositions[combo[0]] == boxPositions[combo[2]]){
                    if (boxPositions[0] == 'X')
                        currentTurnBanner.setImageResource(R.drawable.xwin);
                    else
                        currentTurnBanner.setImageResource(R.drawable.owin);
                }
            }
        }
        if (totalSelectedBoxes == 9)
            currentTurnBanner.setImageResource(R.drawable.nowin);
    }
}