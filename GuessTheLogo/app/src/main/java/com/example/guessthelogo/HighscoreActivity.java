package com.example.guessthelogo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity used for showing the user the HighScore
 */
public class HighscoreActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        TextView highscoreView = (TextView) findViewById(R.id.highscore_view);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("GuessTheLogoData", 0);

        try {
            highscoreView.setText(Integer.toString(pref.getInt("highscore", 0)));
        } catch (Exception e) {
            highscoreView.setText("0");
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

