package com.example.practicaltest01var08;

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

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    Button check;
    Button back;
    EditText riddle2;
    EditText editable2;
    IntentFilter filter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        check =  findViewById(R.id.check_button);
        back = findViewById(R.id.back_button);
        riddle2 = findViewById(R.id.riddle_2);
        editable2 = findViewById(R.id.editable_text2);
        Intent intent = getIntent();
        String Riddle = intent.getStringExtra("RIDDLE");
        String Answer = intent.getStringExtra("ANSWER");
        riddle2.setText(Riddle);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editable2.getText().toString().equals(Answer))
                {
                    Toast.makeText(PracticalTest01Var02PlayActivity.this, "Answer is CORRECT!!", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(PracticalTest01Var02PlayActivity.this, "Answer is INCORRECT!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

        Intent serviceIntent = new Intent(getApplicationContext(),PracticalTest01Var08Service.class);
        serviceIntent.putExtra("RIDDLESANSWER",Answer);
        getApplicationContext().startService(serviceIntent);

        filter.addAction("MESSAGE");
    }
    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[ANSWER]", intent.getStringExtra("CENSOREDANSWER"));
            Toast.makeText(context, "Hint:"+intent.getStringExtra("CENSOREDANSWER"), Toast.LENGTH_LONG).show();
        }
    };
    protected void onResume(){
        super.onResume();
        registerReceiver(messageReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageReceiver);
    }


    @Override
    protected void onDestroy() {
        stopService(new Intent(getApplicationContext(),PracticalTest01Var08Service.class));
        super.onDestroy();
    }
}