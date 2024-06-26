package com.masrik.automation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.masrik.automation.R;

public class NewGame extends AppCompatActivity {
    ImageView question;
    Button next, b_1, b_2, b_3, b_4, button2;
    TextView option1, option2, option3, option4, que, scoree;
    int count = 0;
    Data data = new Data();
    int sco = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.imageView2);
        que = findViewById(R.id.que);
        next = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        option1 = findViewById(R.id.textView12);
        option2 = findViewById(R.id.textView11);
        option3 = findViewById(R.id.textView9);
        option4 = findViewById(R.id.textView10);
        scoree = findViewById(R.id.scoree);

        b_1 = findViewById(R.id.button5);
        b_2 = findViewById(R.id.button6);
        b_3 = findViewById(R.id.button7);
        b_4 = findViewById(R.id.button8);

        make_question(data.database,count);

    } // end onCreate


    @SuppressLint("SetTextI18n")
    public void questionCorrect (View v) {
        Intent i = new Intent(this,Correct.class);
        startActivity(i);
        sco++;
        sco = Math.min(sco, make_question(count));
        scoree.setText("Score: "+sco);
        next.callOnClick();
    }


    @SuppressLint("SetTextI18n")
    public void questionIncorrect (View v) {
        Intent i = new Intent(this,Incorrect.class);
        startActivity(i);
        sco--;
        sco = Math.min(sco, make_question(count));
        scoree.setText("Score: "+sco);
    }

    public void engine (View v) {
        count += 1;
        make_question(data.database, count);
    }
    public void back_engine (View v) {
        count -= 1;
        make_question(data.database, count);
    }

    public int make_question(int val) {
        return val+=1;
    }

    @SuppressLint("SetTextI18n")
    public void make_question(Object[][] database, int count){
        try {
            question.setImageResource((Integer) database[count][0]);
            option1.setText((database[count][1].toString()));
            option2.setText((database[count][2].toString()));
            option3.setText((database[count][3].toString()));
            option4.setText((database[count][4].toString()));
            que.setText("Question: " + make_question(count));
            b_1.setOnClickListener(b_1.getText().toString().equals(data.database[count][5].toString()) ?
                    this::questionCorrect : this::questionIncorrect);
            b_2.setOnClickListener(b_2.getText().toString().equals(data.database[count][5].toString()) ?
                    this::questionCorrect : this::questionIncorrect);
            b_3.setOnClickListener(b_3.getText().toString().equals(data.database[count][5].toString()) ?
                    this::questionCorrect : this::questionIncorrect);
            b_4.setOnClickListener(b_4.getText().toString().equals(data.database[count][5].toString()) ?
                    this::questionCorrect : this::questionIncorrect);
            next.setOnClickListener(this::engine);
            button2.setOnClickListener(this::back_engine);

            button2.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);

        }catch(Exception e){

        }
    }

} // end NewGame
