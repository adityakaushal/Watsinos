package com.example.vmac.WatBot;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    private TextView texts;
    String lines;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texts = findViewById(R.id.text);
        textToSpeech = new TextToSpeech(Main2Activity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS)
                {
                    result = textToSpeech.setLanguage(Locale.UK);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not Supported!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void doSomething(View view) {
        switch(view.getId())
        {
            case R.id.btn_record:
            {
                if(result==TextToSpeech.LANG_NOT_SUPPORTED || result == textToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(getApplicationContext(),"Not Supported!", Toast.LENGTH_SHORT).show();

                }else {
                    lines = texts.getText().toString();
                    textToSpeech.speak(lines, TextToSpeech.QUEUE_FLUSH, null);

                }
            }
            break;

        }
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
