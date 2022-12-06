package com.example.bishvilhaketa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    boolean undoState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playButton(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("undoEnabled", undoState);
        startActivity(intent);
    }

    public void switchState(View view){
        if (!undoState)
            undoState = true;
        else
            undoState = false;
    }
}
