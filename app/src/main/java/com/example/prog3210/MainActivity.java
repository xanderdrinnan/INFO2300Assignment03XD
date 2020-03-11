package com.example.prog3210;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener {

    TriviaDB triviaDB;
    Button randomTriviaBtn;
    String getScore = "";
    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView)findViewById(R.id.displayMessageView);

        triviaDB = new TriviaDB(this);
        triviaDB.populateQuestionsAndAnswersTables();

        randomTriviaBtn = (Button)findViewById(R.id.randomTriviaBtn);
        randomTriviaBtn.setOnClickListener(this);

        displayText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayText()
    {
        if(getScore != "")
        {
            display.setText("Play again! You got " + getScore + " points last time!");
        }
        if(getIntent().getStringExtra("score") != null && !getIntent().getStringExtra("score").isEmpty())
        {
            getScore = getIntent().getStringExtra("score");
           display.setText("Play again! You got " + getScore + " points last time!");
        }
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.randomTriviaBtn){
            Intent rt = new Intent(this, Trivia.class);
            finish();
            startActivity(rt);
        }
        else
        {

        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        displayText();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        getScore = savedInstanceState.getString("getScore");
        displayText();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        if(getScore != null && !getScore.isEmpty())
        {
            savedInstanceState.putString("getScore", getScore);
        }
        else if (getIntent().getStringExtra("score") != null && !getIntent().getStringExtra("score").isEmpty())
        {
            savedInstanceState.putString("getScore", getIntent().getStringExtra("score"));
        }
    }

}
