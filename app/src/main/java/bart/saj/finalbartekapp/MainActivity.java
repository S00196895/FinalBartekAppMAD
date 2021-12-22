package bart.saj.finalbartekapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    // Adding buttons
    Button btnOne, btnWest, btnFour, btnThree, FB;

    int score, round, increase;


    private SensorManager mSensorManager;
    private Sensor mSensor;

    int sequenceCount = 4, n = 0;
    int[] gameSequence = new int[120];
    int arrayIndex = 0;

    private final int ONE = 1;
    private final int TWO = 2;
    private final int THREE = 3;
    private final int FOUR = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnOne = findViewById(R.id.btnOne);
        btnWest = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);



        // we are going to use the sensor service
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        score = getIntent().getIntExtra("score", 0);
        round = getIntent().getIntExtra("round", 1);
        increase = getIntent().getIntExtra("increase", 2);

    }

    CountDownTimer cdtRound1 = new CountDownTimer(6000,  1500) {

        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        @Override
        public void onFinish() {

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            // start next activity

            // put the sequence into the next activity
            Intent i = new Intent(MainActivity.this, GameStartActivity.class);
            i.putExtra("sequence", gameSequence);
            i.putExtra("round", round);
            i.putExtra("score", score);
            i.putExtra("increase", increase);
            startActivity(i);

            // start the next activity
        }
    };
    CountDownTimer cdtRound2 = new CountDownTimer(9000,  1500) {

        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        @Override
        public void onFinish() {

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            // start next activity

            // put the sequence into the next activity
            Intent i = new Intent(MainActivity.this, GameStartActivity.class);
            i.putExtra("sequence", gameSequence);
            i.putExtra("round", round);
            i.putExtra("score", score);
            i.putExtra("increase", increase);
            startActivity(i);

            // start the next activity
        }
    };
    CountDownTimer cdtRound3 = new CountDownTimer(12000,  1500) {

        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        @Override
        public void onFinish() {

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            // start next activity

            // put the sequence into the next activity
            Intent i = new Intent(MainActivity.this, GameStartActivity.class);
            i.putExtra("sequence", gameSequence);
            i.putExtra("round", round);
            i.putExtra("score", score);
            i.putExtra("increase", increase);
            startActivity(i);

            // start the next activity
        }
    };
    CountDownTimer cdtRound4 = new CountDownTimer(15000,  1500) {

        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        @Override
        public void onFinish() {

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            // start next activity

            // put the sequence into the next activity
            Intent i = new Intent(MainActivity.this, GameStartActivity.class);
            i.putExtra("sequence", gameSequence);
            i.putExtra("round", round);
            i.putExtra("score", score);
            i.putExtra("increase", increase);
            startActivity(i);

            // start the next activity
        }
    };
    CountDownTimer cdtRound5 = new CountDownTimer(18000,  1500) {

        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        @Override
        public void onFinish() {

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            // start next activity

            // put the sequence into the next activity
            Intent i = new Intent(MainActivity.this, GameStartActivity.class);
            i.putExtra("sequence", gameSequence);
            i.putExtra("round", round);
            i.putExtra("score", score);
            i.putExtra("increase", increase);
            startActivity(i);

            // start the next activity
        }
    };

    public void doPlay(View view) {

        switch  (round)
        {
            case(1):
                cdtRound1.start();
                break;
            case(2):
                cdtRound2.start();
                break;
            case(3):
                cdtRound3.start();
                break;
            case(4):
                cdtRound4.start();
                break;
            case(5):
                cdtRound5.start();
                break;
        }
    }

    private void oneButton() {
        n = getRandom(sequenceCount);


        switch (n) {
            case 1:
                flashButton(btnOne);
                gameSequence[arrayIndex++] = ONE;
                break;
            case 2:
                flashButton(btnWest);
                gameSequence[arrayIndex++] = TWO;
                break;
            case 3:
                flashButton(btnThree);
                gameSequence[arrayIndex++] = THREE;
                break;
            case 4:
                flashButton(btnFour);
                gameSequence[arrayIndex++] = FOUR;
                break;
            default:
                break;
        }   // end switch
    }

    // return a number between 1 and maxValue
    private int getRandom(int maxValue) {
        return ((int) ((Math.random() * maxValue) + 1));
    }

    private void flashButton(Button button) {
        FB = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                FB.setPressed(true);
                FB.invalidate();
                FB.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        FB.setPressed(false);
                        FB.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);

            } // end runnable
        };
        handler.postDelayed(r, 600);
    }
};