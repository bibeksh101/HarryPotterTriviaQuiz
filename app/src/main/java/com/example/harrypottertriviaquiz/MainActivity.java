package com.example.harrypottertriviaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Member Variables
    Button mButton01;
    Button mButton02;
    Button mButton03;
    Button mButton04;

    TextView mScoreTextView;
    TextView mQuestionTextView;
    ProgressBar mProgressBar;

    int mIndex;
    int mScore;
    int mQuestion;


    /* Question Bank
        Source: Pottermore
        Imported From "QuestionAndAnswers"
     */
    private Answers[] Set01_The_First_Year_Hagrid_Quiz = QuestionsAndAnswers.Set01_The_First_Year_Hagrid_Quiz;
    private Answers[] Set02_The_Hogwarts_School_List_Quiz = QuestionsAndAnswers.Set02_The_Hogwarts_School_List_Quiz;
    private Answers[] Tester = QuestionsAndAnswers.mQuestionBankSet02in;


    //FIRST PAGE
    public void starter(){
        setContentView(R.layout.first_page);
        Button startGame = findViewById(R.id.start_game);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quiz_Choices();
            }
        });
    }

    // TODO: DONE: MAIN FUNCTION (STARTER PACK)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        starter();
    }

    //SECOND PAGE (With Quiz Options)
    public void Quiz_Choices() {
        setContentView(R.layout.welcome_page);
        Button set01 = findViewById(R.id.set01);
        Button set02 = findViewById(R.id.set02);
        Button set03 = findViewById(R.id.set03);

        set01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                RUN_QUESTION_SET(Set01_The_First_Year_Hagrid_Quiz);
            }
        });
        set02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                RUN_QUESTION_SET(Set02_The_Hogwarts_School_List_Quiz);
            }
        });
        set03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                RUN_QUESTION_SET(Tester);
            }
        });
    }

    int PROGRESS_BAR_INCREMENT = 0;

    private void RUN_QUESTION_SET(final Answers[] mQuestionBank) {

        //Increment is unit fraction for each question.
        //10 question means, after each question, the bar increases by 1/10th.
        PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);


        mButton01 = findViewById(R.id.button1);
        mButton02 = findViewById(R.id.button2);
        mButton03 = findViewById(R.id.button3);
        mButton04 = findViewById(R.id.button4);
        mQuestionTextView = findViewById(R.id.question);

        mScoreTextView = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progressBar);

        /*
        Set up For FIRST Question. Display All Options.
         */
        Answers firstQuestion = mQuestionBank[mIndex];
        mQuestion = firstQuestion.getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mButton01.setText(firstQuestion.getManswerID01());
        mButton02.setText(firstQuestion.getManswerID02());
        mButton03.setText(firstQuestion.getManswerID03());
        mButton04.setText(firstQuestion.getManswerID04());

        //DISPLAY INITIAL SCORE
        mScoreTextView.setText(scoreUpdate(mScore, mQuestionBank));

        /*
        LISTENERS for CLICKS on OPTIONS!
         */
        mButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CHECK ANSWER
                checkAnswer(String.valueOf(mButton01.getText()), mQuestionBank);
                //BRING NEW QUESTION
                updateQuestion(mQuestionBank);
            }
        });

        mButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(String.valueOf(mButton02.getText()), mQuestionBank);
                updateQuestion(mQuestionBank);
            }
        });

        mButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(String.valueOf(mButton03.getText()), mQuestionBank);
                updateQuestion(mQuestionBank);
            }
        });

        mButton04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(String.valueOf(mButton04.getText()), mQuestionBank);
                updateQuestion(mQuestionBank);
            }
        });
    }

    /* Updates the Score for Printing on Screen.
      @param: score is the score to be displayed.
      @return: the string with updated score.
   */
    private String scoreUpdate(int score, Answers[] mQuestionBank) {
        return "Score " + score + "/" + mQuestionBank.length;
    }

    private void updateQuestion(Answers[] mQuestionBank) {
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if (mIndex == 0) {
            setContentView(R.layout.end_page);
            END_OF_GAME_OR_RESTART();
        }

        // GET NEW QUESTION
        mQuestion = mQuestionBank[mIndex].getQuestionID();
        // SET NEW QUESTION
        mQuestionTextView.setText(mQuestion);
        // SET NEW OPTIONS
        mButton01.setText(mQuestionBank[mIndex].getManswerID01());
        mButton02.setText(mQuestionBank[mIndex].getManswerID02());
        mButton03.setText(mQuestionBank[mIndex].getManswerID03());
        mButton04.setText(mQuestionBank[mIndex].getManswerID04());

        // UPDATE PROGRESS BAR
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        // UPDATE SCORE
        mScoreTextView.setText(scoreUpdate(mScore, mQuestionBank));
    }

    private void checkAnswer(String userSelection, Answers[] mQuestionBank) {
        String correctAnswer = mQuestionBank[mIndex].getManswerID();


        // DISPLAY TOAST (Notification on Bottom)
        if (userSelection.equals(correctAnswer)) {
            String question = getText(mQuestionBank[mIndex].getQuestionID()).toString();
            String correctMessage = "Correct Answer Master Wizard! \n" + question + " ANSWER: " + correctAnswer;
            Toast myToast = Toast.makeText(getApplicationContext(), correctMessage, Toast.LENGTH_SHORT);
            myToast.show();
            mScore = mScore + 1;
        }

        else {
            String message = "Wrong Answer Young Wizard!";
            Toast myToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            myToast.show();
            showQuestionAnswer(correctAnswer, mQuestionBank);
        }
    }

    private void showAlertDialogue(String qAlert, String aAlert) {
        new AlertDialog.Builder(this)
                .setTitle(qAlert)
                .setMessage("CORRECT ANSWER: " + aAlert)
                .setPositiveButton("Thanks Pottermore!", null)
                .setIcon(android.R.drawable.ic_menu_zoom)
                .show();
    }

    private void showQuestionAnswer(String CORRECT_ANSWER, Answers[] mQuestionBank) {
        showAlertDialogue(getText(mQuestionBank[mIndex].getQuestionID()).toString(), CORRECT_ANSWER);
    }

    public void END_OF_GAME_OR_RESTART() {
        boolean answer;
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        //dialog.setTitle("CONTINUE GAME?");
        //dialog.setMessage("Would you like to retake another game?");


        dialog.setTitle("GOOD SPELLWORK!");
        dialog.setMessage("You Scored " + mScore + " points!. \nA few more swish and flicks, and you’ll be top of the class. WOULD YOU LIKE TO RETAKE ANOTHER QUIZ?");

        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonId) {
                mScore = 0;
                starter();
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Do Nothing!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonId) {
                setContentView(R.layout.end_page);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonId) {
                finish();
            }
        });

        dialog.setIcon(android.R.drawable.ic_menu_zoom);
        dialog.show();
    }
}
