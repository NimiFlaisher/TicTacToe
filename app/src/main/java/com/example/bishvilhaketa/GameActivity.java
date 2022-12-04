package com.example.bishvilhaketa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameActivity extends AppCompatActivity {

    private final List<int[]> comboList = new ArrayList<>();
    private final char [] boxPositions = {'E','E','E','E','E','E','E','E','E'};
    private char playerTurn = 'X';
    private int totalSelectedBoxes = 0;
    private ImageView currentPlayerImage, currentTurnBanner;
    private ImageView box0, box1, box2, box3, box4, box5, box6, box7, box8;
    private final ImageView [] boxesArray = {box0, box1, box2, box3, box4, box5, box6, box7, box8};
    private boolean gameOver;
    private Button playAgainBtn;
    private final Stack<Integer> undoStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playAgainBtn = findViewById(R.id.playAgainBtn);
        playAgainBtn.setClickable(false);
        playAgainBtn.setVisibility(View.INVISIBLE);

        currentTurnBanner = findViewById(R.id.bannerImage);
        currentTurnBanner.setImageResource(R.drawable.xplay);
        currentPlayerImage = findViewById(R.id.testest);
        currentPlayerImage.setImageResource(R.drawable.x);

        gameOver = false;

        comboList.add(new int[]{0, 1, 2});
        comboList.add(new int[]{3, 4, 5});
        comboList.add(new int[]{6, 7, 8});
        comboList.add(new int[]{0, 3, 6});
        comboList.add(new int[]{1, 4, 7});
        comboList.add(new int[]{2, 5, 8});
        comboList.add(new int[]{0, 4, 8});
        comboList.add(new int[]{2, 4, 6});

        boxesArray[0] = findViewById(R.id.box0);
        boxesArray[1] = findViewById(R.id.box1);
        boxesArray[2] = findViewById(R.id.box2);
        boxesArray[3] = findViewById(R.id.box3);
        boxesArray[4] = findViewById(R.id.box4);
        boxesArray[5] = findViewById(R.id.box5);
        boxesArray[6] = findViewById(R.id.box6);
        boxesArray[7] = findViewById(R.id.box7);
        boxesArray[8] = findViewById(R.id.box8);

        for (int i = 0; i <= 8; i++) {
            boxesArray[i].setImageResource(R.drawable.empty);
            int finalI = i;
            boxesArray[i].setOnClickListener(view -> {
                if (boxPositions[finalI] == 'E' && !gameOver) {
                    boxPositions[finalI] = playerTurn;
                    boxesArray[finalI].setImageDrawable(currentPlayerImage.getDrawable());
                    nextPlayerTurn();
                    checkWinner();
                    undoStack.push(finalI);
                }
            });
        }
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
                    if (boxPositions[combo[0]] == 'X')
                        currentTurnBanner.setImageResource(R.drawable.xwin);
                    else
                        currentTurnBanner.setImageResource(R.drawable.owin);
                }
            }
        }
        if (totalSelectedBoxes == 9 && !gameOver) {
            currentTurnBanner.setImageResource(R.drawable.nowin);
            gameOver = true;
        }
        if (gameOver){
            playAgainBtn.setClickable(true);
            playAgainBtn.setVisibility(View.VISIBLE);
        }
    }

    public void onPlayAgain(View v ){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onUndo(View v){
        if (!gameOver && totalSelectedBoxes > 0) {
            boxPositions[undoStack.peek()] = 'E';
            boxesArray[undoStack.pop()].setImageResource(R.drawable.empty);
            nextPlayerTurn();
            totalSelectedBoxes-=2;
        }
    }
}