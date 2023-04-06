package com.example.practicaltest01var08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    Button play;
    EditText riddle;
    EditText answer;
    ButtonListener buttonListener = new ButtonListener();
    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.play_button:
                    if(!riddle.getText().toString().equals("") && !answer.getText().toString().equals(""))
                    {
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                        intent.putExtra("RIDDLE",riddle.getText().toString());
                        intent.putExtra("ANSWER",answer.getText().toString());
                        startActivityForResult(intent,2023);
                        break;
                    }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        play = findViewById(R.id.play_button);
        riddle = findViewById(R.id.Riddle);
        answer = findViewById(R.id.Answer);
        play.setOnClickListener(buttonListener);
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("RIDDLE",riddle.getText().toString());
        outState.putString("ANSWER",answer.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.containsKey("RIDDLE"))
            riddle.setText(savedInstanceState.getString("RIDDLE"));
        if(savedInstanceState.containsKey("ANSWER"))
            answer.setText(savedInstanceState.getString("ANSWER"));
    }
}