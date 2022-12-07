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
    private ImageView winningLine;
    private ImageView currentPlayerImage, currentTurnBanner;
    private ImageView box0, box1, box2, box3, box4, box5, box6, box7, box8;
    private final ImageView [] boxesArray = {box0, box1, box2, box3, box4, box5, box6, box7, box8};
    private boolean gameOver;
    private Button playAgainBtn;
    private Button undoButton;
    private final Stack<Integer> undoStack = new Stack<>();
    private boolean undoEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        undoButton = findViewById(R.id.undoButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            undoEnabled = extras.getBoolean("undoEnabled");
        if (!undoEnabled){
            undoButton.setClickable(false);
            undoButton.setVisibility(View.INVISIBLE);
        }

        playAgainBtn = findViewById(R.id.playAgainBtn);
        playAgainBtn.setClickable(false);
        playAgainBtn.setVisibility(View.INVISIBLE);

        winningLine = findViewById(R.id.winningLineImage);

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
            int comboNumber = 0;
            for (int [] combo: comboList) {
                if (boxPositions[combo[0]]!='E' && boxPositions[combo[0]] == boxPositions[combo[1]] && boxPositions[combo[0]] == boxPositions[combo[2]]){
                    gameOver = true;
                    if (boxPositions[combo[0]] == 'X')
                        currentTurnBanner.setImageResource(R.drawable.xwin);
                    else
                        currentTurnBanner.setImageResource(R.drawable.owin);
                    drawWinningLine(comboNumber);
                    break;
                }
                comboNumber++;
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

    public void drawWinningLine(int comboImage){
        switch(comboImage){
            case 0:
                winningLine.setImageResource(R.drawable.mark3);
                winningLine.setRotation(90);
                break;
            case 1:
                winningLine.setImageResource(R.drawable.mark4);
                winningLine.setRotation(90);
                break;
            case 2:
                winningLine.setImageResource(R.drawable.mark3);
                winningLine.setRotation(-90);
                break;
            case 3:
                winningLine.setImageResource(R.drawable.mark3);
                break;
            case 4:
                winningLine.setImageResource(R.drawable.mark4);
                break;
            case 5:
                winningLine.setImageResource(R.drawable.mark5);
                break;
            case 6:
                winningLine.setImageResource(R.drawable.mark1);
                break;
            case 7:
                winningLine.setImageResource(R.drawable.mark2);
                break;
        }
        winningLine.setVisibility(View.VISIBLE);
    }

    public void onPlayAgain(View v ){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("undoEnabled", undoEnabled);
        startActivity(intent);
    }

    public void onUndo(View v){
        if (undoEnabled && !gameOver && totalSelectedBoxes > 0) {
            boxPositions[undoStack.peek()] = 'E';
            boxesArray[undoStack.pop()].setImageResource(R.drawable.empty);
            nextPlayerTurn();
            totalSelectedBoxes-=2;
        }
    }

    public void onMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("undoEnabled", undoEnabled);
        startActivity(intent);
//        finish();
    }
}
