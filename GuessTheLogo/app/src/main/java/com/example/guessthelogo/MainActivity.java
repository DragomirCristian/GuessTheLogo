package com.example.guessthelogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The main activity
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Creates a new game (sends to NewGameActivity)
     *
     * @param view the view
     */
    public void newGame(View view) {
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivity(intent);
    }

    /**
     * Shows the highscore in a new activity
     *
     * @param view the view
     */
    public void highscore(View view) {
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }

    /**
     * Exits the app instead of going back to previous activity
     */
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void share(View view) {
        super.share(view);
    }
}
