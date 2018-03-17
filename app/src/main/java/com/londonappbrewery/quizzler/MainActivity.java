package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    // TODO: Declare member variables here:

    Button mTrue;
    Button mFalse;
    TextView questionText ;
    TextView mScore;
    ProgressBar scoreBar;
    int index=0;
    int score;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int UPDATE_PROGRESS_BAR= (int)Math.ceil(100.0/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            score= savedInstanceState.getInt("Score");
            index= savedInstanceState.getInt("Index");
        }else{
            score=0;
            index=0;
        }

        mTrue=(Button) findViewById(R.id.true_button);
        mFalse =(Button) findViewById(R.id.false_button);
        questionText =(TextView) findViewById(R.id.question_text_view);
        questionText.setText(mQuestionBank[index].getQuestionID());
        mScore=(TextView) findViewById(R.id.score);
        scoreBar=(ProgressBar) findViewById(R.id.progress_bar);
        mScore.setText("Score"+ score+"/"+ mQuestionBank.length);

        mTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getAnswer(true);
                updateQuestion();
            }
        });

        mFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getAnswer(false);
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
        index=(index+1)% mQuestionBank.length;
        if(index==0){
            AlertDialog.Builder alert =new AlertDialog.Builder(this);
            alert.setCancelable(false);
            alert.setTitle("Game Over");
            alert.setMessage("Your Score is " + score);
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();;
                }
            });
            alert.show();
        }

        questionText.setText(mQuestionBank[index].getQuestionID());
        scoreBar.incrementProgressBy(UPDATE_PROGRESS_BAR);
        mScore.setText("Score" + score + "/"+ mQuestionBank.length);
    }

    private void getAnswer(boolean userAnswer){

        boolean correctAnswer= mQuestionBank[index].isAnswer();
        if(userAnswer== correctAnswer){
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            score=score+1;
        }else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }

    @Override

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("Score",score);
        outState.putInt("Index",index);
    }
}
