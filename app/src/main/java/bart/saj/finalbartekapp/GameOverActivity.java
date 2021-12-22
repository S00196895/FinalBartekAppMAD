package bart.saj.finalbartekapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class GameOverActivity extends AppCompatActivity {

    int score, round;
    TextView tvScore, tvRound;

    public DatabaseHandler db;
    public EditText etScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        etScore = findViewById(R.id.enterName);


        tvScore = findViewById(R.id.tvUserScore);
        tvRound = findViewById(R.id.tvRounds);

        score = getIntent().getIntExtra("score", 0);
        round = getIntent().getIntExtra("round",0);

        tvScore.setText(String.valueOf("Score of: " + score));
        tvRound.setText(String.valueOf("Rounds Played " + round));


        db = new DatabaseHandler(this);
        db.emptyHiScores();
        Data();
        Log.i("Reading: ", "Reading all scores..");
        List<HiScore> hiScores = db.getAllHiScores();


        for (HiScore hs : hiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "========================================");

        HiScore singleScore = db.getHiScore(5);
        Log.i("High Score 5 is by ", singleScore.getPlayer_name() + " with a score of " + singleScore.getScore());

        Log.i("divider", "========================================");

        // Calling SQL statement
        List<HiScore> top5HiScores = db.getTopFiveScores();
        for (HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();
            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        HiScore lastScore = top5HiScores.get(top5HiScores.size() - 1);
        if (score > lastScore.score) {
            Toast.makeText(this,"You Won!!! Enter your Name!!", Toast.LENGTH_LONG).show();
        }

    }

    public void Data(){
        // Inserting hi scores
        Log.i("Insert: ", "Inserting Scores...");
        db.addHiScore(new HiScore("23/1/2016", "Bartek", 1));
        db.addHiScore(new HiScore("20/12/2010", "Christian", 4));
        db.addHiScore(new HiScore("20/01/2001", "Mr Krabs", 6));
        db.addHiScore(new HiScore("06/10/2018", "Patrick", 7));
        db.addHiScore(new HiScore("14/11/2020", "Spongebob", 111));
        db.addHiScore(new HiScore("04/02/2020", "Sandy", 132));
    }

    public void doAdd(View view) {
        List<HiScore> top5HiScores = db.getTopFiveScores();
        HiScore lastScore = top5HiScores.get(top5HiScores.size() - 1);

        if(score > lastScore.score && etScore.getText().toString() != ""){
            String userName = etScore.getText().toString();
            String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            db.addHiScore(new HiScore(date, userName, score));
            top5HiScores = db.getTopFiveScores();
            for (HiScore hs : top5HiScores) {
                String log =
                        "Id: " + hs.getScore_id() +
                                " , Player: " + hs.getPlayer_name() +
                                " , Score: " + hs.getScore();

                // Writing HiScore to log
                Log.i("Score: ", log);
            }
        }
        else{
            Toast.makeText(this,"Your Score isn't High Enough",Toast.LENGTH_SHORT).show();
        }

       doHighScore(view);
    }

    public void doHighScore(View view) {
        Intent intent = new Intent(view.getContext(), DisplayHighScore.class);

        startActivity(intent);
        finish();
    }

    public void doPlayAgain(View view) {
        Intent in = new Intent(view.getContext(), MainActivity.class);

        startActivity(in);
    }
}