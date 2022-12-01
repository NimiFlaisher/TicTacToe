package com.example.bishvilhaketa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private final List<int[]> comboList = new ArrayList<>();
    private int [] boxPositions = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 0;
    private ImageView box0, box1, box2, box3, box4, box5, box6, box7, box8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        box0 = findViewById(R.id.box0);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);
        box7 = findViewById(R.id.box7);
        box8 = findViewById(R.id.box8);

        comboList.add(new int[]{0,1,2});
        comboList.add(new int[]{3,4,5});
        comboList.add(new int[]{6,7,8});
        comboList.add(new int[]{0,3,6});
        comboList.add(new int[]{1,4,7});
        comboList.add(new int[]{2,5,8});
        comboList.add(new int[]{0,4,8});
        comboList.add(new int[]{2,4,6});

    }
}