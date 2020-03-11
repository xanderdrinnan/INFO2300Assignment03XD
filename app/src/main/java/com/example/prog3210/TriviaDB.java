package com.example.prog3210;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class TriviaDB {

    public static final String DB_NAME = "trivia.sqlite";
    public static final int    DB_VERSION = 1;
    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL("CREATE TABLE triviaQuestions (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , triviaQuestion VARCHAR)");
            db.execSQL("CREATE TABLE triviaAnswers (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, triviaAnswer VARCHAR)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE \"triviaQuestions\"");
            db.execSQL("DROP TABLE \"triviaAnswers\"");
            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public TriviaDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        openWriteableDB();
        closeDB();
    }
    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public void deleteDB()
    {
        openWriteableDB();
        db.execSQL("DROP TABLE IF EXISTS triviaQuestions");
        closeDB();
    }


    String[] returnQuestion() {
        //Get the count of all questions in the triviaQuestions table for later//
        openReadableDB();
        Cursor countCursor = db.rawQuery("SELECT COUNT(*) FROM triviaQuestions", null);
        int getCount = countCursor.getCount();

        //Return a random question
        String[] getQuestion = new String[2];

        try
        {
        Random random = new Random();
        Cursor questionCursor;


            questionCursor = db.rawQuery("SELECT id, triviaQuestion FROM triviaQuestions WHERE id = ?", new String[]{Integer.toString(random.nextInt(16 - 1 + 1) + 1)});
            while(questionCursor.moveToNext()) {
                getQuestion[0] = questionCursor.getString((0));
                getQuestion[1] = questionCursor.getString((1));
            }

            questionCursor.close();
        }
        catch (Exception e )
        {
            Log.d("Trivia Question Error", "Unable to Get New Question");
        }

        countCursor.close();
        closeDB();
        return getQuestion;
    }

    String returnAnswer(String questionId) {
        String getAnswer = "";
        openReadableDB();
        try {
            Cursor answerCursor = db.rawQuery("SELECT triviaAnswer FROM triviaAnswers WHERE id = ?", new String[]{questionId});
            answerCursor.moveToFirst();
            getAnswer = answerCursor.getString((0));
            answerCursor.close();
        }
        catch (Exception f)
        {
            Log.d("Answer Error", "Unable to Get Answer");
        }

        closeDB();
        return getAnswer;
    }

    public void populateQuestionsAndAnswersTables()
    {
        String answerQuery = "";
        String questionQuery = "";
        String[] questionsArray = new String[16];
        String[] answersArray = new String[16];

        questionsArray[0] = "'How Much Weight Did Strongman Eddie Hall Deadlift For His World Record? (In KG)'";
        questionsArray[1] = "'Did Jeffrey Epstein Kill Himself?'";
        questionsArray[2] = "'What year was Canada founded?'";
        questionsArray[3] = "'Which historical epoch was inspired following Alexander the Great?'";
        questionsArray[4] = "'What was the name of the final episode of Breaking Bad?'";
        questionsArray[5] = "'Modern Crocodylians (Crocodiles, Alligators, Gharials) represent which ancient genus?'";
        questionsArray[6] = "'What nation borders both Burma and Malaysia?'";
        questionsArray[7] = "'What Beatles song repeats the title in the lyrics forty-one times?'";
        questionsArray[8] = "'Which river runs through the Grand Canyon?'";
        questionsArray[9] = "'What year was Winnie the Pooh created?'";
        questionsArray[10] = "'Who was the author of War and Peace?'";
        questionsArray[11] = "'What vitamin group is mostly required for blood coagulation?'";
        questionsArray[12] = "'What year was Beethoven born?'";
        questionsArray[13] = "'Warsaw is the capital of what country?'";
        questionsArray[14] = "'Who skinned the Nemean Lion?'";
        questionsArray[15] = "'According to Futurama, how much 1lb of Dark Matter weigh?'";



        answersArray[0] = "'500kg'";
        answersArray[1] = "'No'";
        answersArray[2] = "'1867'";
        answersArray[3] = "'Hellenistic'";
        answersArray[4] = "'Felina'";
        answersArray[5] = "'Crocodylomorpha'";
        answersArray[6] = "'Thailand'";
        answersArray[7] = "'Let It Be'";
        answersArray[8] = "'Colorado River'";
        answersArray[9] = "'1925'";
        answersArray[10] = "'Leo Tolstoy'";
        answersArray[11] = "'Vitamin K'";
        answersArray[12] = "'1770'";
        answersArray[13] = "'Poland'";
        answersArray[14] = "'Hercules/Heracles'";
        answersArray[15] = "'Over 10000lbs'";

        try {
            openWriteableDB();
            for(int i = 0; i < questionsArray.length; i++)
            {
                questionQuery = "INSERT INTO triviaQuestions (triviaQuestion) VALUES (" +questionsArray[i] + ")";
               db.execSQL(questionQuery);
            }



            for(int i = 0; i < answersArray.length; i++)
            {
                answerQuery = "INSERT INTO triviaAnswers (triviaAnswer) VALUES (" +answersArray[i] + ")";
                db.execSQL(answerQuery);
            }

            closeDB();

        }
        catch (Exception e)
        {
            Log.d("Value Creation Error", "Unable to Insert Questions/Answers");
        }


    }



}
