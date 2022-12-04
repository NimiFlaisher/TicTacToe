package com.example.bishvilhaketa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private boolean gameOver;
    private Button playAgainBtn ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        playAgainBtn.setClickable(false);
        playAgainBtn.setVisibility(View.INVISIBLE);

        currentTurnBanner = findViewById(R.id.bannerImage);
        currentPlayerImage = findViewById(R.id.testest);
        currentPlayerImage.setImageResource(R.drawable.x);
        currentTurnBanner.setImageResource(R.drawable.xplay);

        gameOver = false;

        comboList.add(new int[]{0, 1, 2});
        comboList.add(new int[]{3, 4, 5});
        comboList.add(new int[]{6, 7, 8});
        comboList.add(new int[]{0, 3, 6});
        comboList.add(new int[]{1, 4, 7});
        comboList.add(new int[]{2, 5, 8});
        comboList.add(new int[]{0, 4, 8});
        comboList.add(new int[]{2, 4, 6});

        box0 = findViewById(R.id.box0);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);
        box7 = findViewById(R.id.box7);
        box8 = findViewById(R.id.box8);

        box0.setImageResource(R.drawable.empty);
        box1.setImageResource(R.drawable.empty);
        box2.setImageResource(R.drawable.empty);
        box3.setImageResource(R.drawable.empty);
        box4.setImageResource(R.drawable.empty);
        box5.setImageResource(R.drawable.empty);
        box6.setImageResource(R.drawable.empty);
        box7.setImageResource(R.drawable.empty);
        box8.setImageResource(R.drawable.empty);


            box0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[0] == 'E' && !gameOver) {
                        boxPositions[0] = playerTurn;
                        box0.setImageDrawable(currentPlayerImage.getDrawable());
                        nextPlayerTurn();
                        checkWinner();
                    }
                }
            });

            box1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[1] == 'E' && !gameOver) {
                        boxPositions[1] = playerTurn;
                        box1.setImageDrawable(currentPlayerImage.getDrawable());
                        nextPlayerTurn();
                        checkWinner();
                    }
                }
            });
            box2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[2] == 'E' && !gameOver) {
                        boxPositions[2] = playerTurn;
                        box2.setImageDrawable(currentPlayerImage.getDrawable());
                        nextPlayerTurn();
                        checkWinner();
                    }
                }
            });
            box3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[3] == 'E' && !gameOver) {
                        boxPositions[3] = playerTurn;
                        box3.setImageDrawable(currentPlayerImage.getDrawable());
                        nextPlayerTurn();
                        checkWinner();
                    }
                }
            });
            box4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[4] == 'E' && !gameOver) {
                        boxPositions[4] = playerTurn;
                        box4.setImageDrawable(currentPlayerImage.getDrawable());
                        nextPlayerTurn();
                        checkWinner();
                    }
                }
            });
            box5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[5] == 'E' && !gameOver) {
                        boxPositions[5] = playerTurn;
                        box5.setImageDrawable(currentPlayerImage.getDrawable());
                        nextPlayerTurn();
                        checkWinner();
                    }
                }
            });
            box6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[6] == 'E' && !gameOver) {
                        boxPositions[6] = playerTurn;
                        box6.setImageDrawable(currentPlayerImage.getDrawable());
                        nextPlayerTurn();
                        checkWinner();
                    }
                }
            });
            box7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[7] == 'E' && !gameOver) {
                        boxPositions[7] = playerTurn;
                        box7.setImageDrawable(currentPlayerImage.getDrawable());
                        nextPlayerTurn();
                        checkWinner();
                    }
                }
            });
            box8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (boxPositions[8] == 'E' && !gameOver) {
                        boxPositions[8] = playerTurn;
                        box8.setImageDrawable(currentPlayerImage.getDrawable());
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
        if (totalSelectedBoxes > 2){
            for (int [] combo: comboList) {
                if (boxPositions[combo[0]]!='E' && boxPositions[combo[0]] == boxPositions[combo[1]] && boxPositions[combo[0]] == boxPositions[combo[2]]){
                    gameOver = true;
                    playAgainBtn.setClickable(true);
                    playAgainBtn.setVisibility(View.VISIBLE);
                    if (boxPositions[combo[0]] == 'X')
                        currentTurnBanner.setImageResource(R.drawable.xwin);
                    else
                        currentTurnBanner.setImageResource(R.drawable.owin);
                }
            }
        }
        else  if (totalSelectedBoxes ==9) {
            currentTurnBanner.setImageResource(R.drawable.nowin);
            gameOver = true;
            playAgainBtn.setClickable(true);
            playAgainBtn.setVisibility(View.VISIBLE);

        }
    }
    public void onPlayAgain(View v ){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}