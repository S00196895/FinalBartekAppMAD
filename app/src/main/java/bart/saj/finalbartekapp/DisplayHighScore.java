package bart.saj.finalbartekapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayHighScore extends ListActivity {

    //TextView tvListHeader;
    private DatabaseHandler datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_high_score);

        //tvListHeader = findViewById(R.id.tvListHeader);

        String result = String.format("%-50s%-50s%-50s\n","Player Name","Score","Date Played");

       // tvListHeader.setText(result);
        datasource = new DatabaseHandler(this);

        List<HiScore> values = datasource.getTopFiveScores();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<HiScore> adapter = new ArrayAdapter<HiScore>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Puts you back on the main activity to start again
    public void onRestart(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);

        startActivity(intent);
    }
}