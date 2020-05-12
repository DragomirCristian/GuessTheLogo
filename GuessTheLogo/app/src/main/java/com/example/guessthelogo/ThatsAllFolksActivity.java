package com.example.guessthelogo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity used for showing the user that he finished the game
 */
public class ThatsAllFolksActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thats_all_folks);
        TextView lastScoreView = (TextView) findViewById(R.id.last_score_text_view);

        Bundle bundle = getIntent().getExtras();
        int score;
        if (bundle != null) {
            score = bundle.getInt("score");
            lastScoreView.setText("Your Score: " + Integer.toString(score));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
