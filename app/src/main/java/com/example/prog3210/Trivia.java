package com.example.prog3210;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Trivia extends AppCompatActivity
implements View.OnClickListener {

    PopupWindow popUp;
    private TriviaDB triviaDB;
    Button submitQuestion;
    Button mainMenuButton;
    String[] getQuestionCall = new String[2];
    String getQuestion;
    String getQuestionId;
    String getPlayerAnswer;
    String getQuestionAnswer;
    TextView questionTextView;
    EditText submitAnswerEdit;
    int score = 0;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

            triviaDB = new TriviaDB(this);
            submitAnswerEdit = (EditText) findViewById(R.id.submitAnswerTxt);
            submitQuestion = (Button) findViewById(R.id.submitButton);
            submitQuestion.setOnClickListener(this);
            questionTextView = (TextView) findViewById(R.id.randomQuestionTextView);
            getQuestionCall = triviaDB.returnQuestion();
            getQuestion = getQuestionCall[1];
            getQuestionId = getQuestionCall[0];

            //main menu button
            mainMenuButton = (Button)findViewById(R.id.mainMenuButton);
            mainMenuButton.setOnClickListener(this);

            //Answer to the question that was generated
            getQuestionAnswer = triviaDB.returnAnswer(getQuestionId);

            //Setting the question to be visible
            questionTextView.setText(getQuestion);

            if(savedInstanceState != null)
            {
            getQuestion = savedInstanceState.getString("getQuestion");
            getQuestionAnswer = savedInstanceState.getString("getQuestionAnswer");
            getQuestionId = savedInstanceState.getString("getQuestionId");
            questionTextView.setText(getQuestion);
            submitAnswerEdit.setText(savedInstanceState.getString("getPlayerAnswer"));
            }
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.submitButton){
            getPlayerAnswer = submitAnswerEdit.getText().toString();
            if(getPlayerAnswer.contains(getQuestionAnswer))
            {
                //Do the success stuff here
                score = score + 1;
                answerToast("Correct", getQuestionAnswer);
            }
            else
            {
                answerToast("Incorrect", getQuestionAnswer);
            }

//            Refresh - Get New Question, Answer, then display
            getQuestionCall = triviaDB.returnQuestion();
            getQuestion = getQuestionCall[1];
            getQuestionId = getQuestionCall[0];
            getQuestionAnswer = triviaDB.returnAnswer(getQuestionId);
            questionTextView.setText(getQuestion);
            createNotification(score);
        }
        if(v.getId() == R.id.mainMenuButton) {
            Intent mm = new Intent(Trivia.this, MainActivity.class);
            mm.putExtra("score", Integer.toString(score));
            finish();
            startActivity(mm);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("getQuestion", getQuestion);
        savedInstanceState.putString("getQuestionId", getQuestionId);
        savedInstanceState.putString("getQuestionAnswer", getQuestionAnswer);
        savedInstanceState.putInt("score", score);
        savedInstanceState.putString("getPlayerAnswer", submitAnswerEdit.getText().toString());

    }

    public void answerToast(String result, String getQuestionAnswer) {
        Context context = getApplicationContext();
        CharSequence text = "";

        if(result == "Correct") {

            text = "You got it right!";
        }
        else if (result == "Incorrect")
        {
            text = "Incorrect! The correct answer was: " + getQuestionAnswer;
        }

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public void createNotification(int score) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.triviaicon)
                .setContentTitle("Play Again!")
                .setContentText("You just scored " + score + " points!");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
