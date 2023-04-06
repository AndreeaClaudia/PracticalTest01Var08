package com.example.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class ProcessingThread extends Thread{
    Context context;
    String answer;
    Random random = new Random();
    boolean isRunning = true;
    public ProcessingThread(Context context,String answer)
    {
        this.context = context;
        this.answer = answer;
    }

    @Override
    public void run() {
        Log.d("[THREAD]","Thread has started");
        while(isRunning)
        {
            String censoredAnswer = "";
            int poz = random.nextInt(answer.length());
            for(int i = 0; i < 5 ; i++)
            {
                if(i!=poz)
                {
                    censoredAnswer = censoredAnswer + "*";
                }
                else
                    censoredAnswer = censoredAnswer + String.valueOf(answer.charAt(poz));
            }
            Intent intent = new Intent();
            intent.setAction("MESSAGE");
            intent.putExtra("CENSOREDANSWER",censoredAnswer);
            context.sendBroadcast(intent);
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopThread()
    {
        isRunning = false;
    }

}
