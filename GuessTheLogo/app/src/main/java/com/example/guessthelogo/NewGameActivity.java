package com.example.guessthelogo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity used for a new game
 */
public class NewGameActivity extends BaseActivity {
    Game game;
    ImageView logoView;
    TextView scoreView;
    TextView answerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("GuessTheLogoData", 0);

        game = new Game(getApplicationContext());

        try {
            game.setHighscore(pref.getInt("highscore", 0));
        } catch (Exception e) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("highscore", 0);
            game.setHighscore(0);
            e.printStackTrace();
        }

        logoView = (ImageView) findViewById(R.id.logo_view);
        scoreView = (TextView) findViewById(R.id.score_view);
        answerView = (TextView) findViewById(R.id.logos_name);

        answerView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    answerView.setText("");
                else
                    answerView.setText("Logo's name");
            }
        });

        // if enter pressed, call guessLogo()
        answerView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        guessLogo(view);
                    return true;
                }
                return false;
            }
        });

        scoreView.setText("Score: " + Integer.toString(game.getScore()));
        nextLevel();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    /**
     * Checks if the answer is correct and send user to the next level
     *
     * @param view the view
     */
    public void guessLogo(View view) {
        TextView logosNameView = findViewById(R.id.logos_name);
        String logosName = logosNameView.getText().toString();

        giveFeedback(logosName);

        scoreView.setText("Score: " + Integer.toString(game.getScore()));

        nextLevel();
        answerView.setText("");
        answerView.setFocusable(true);
    }

    /**
     * Checks if the name given by the user is correct and gives feedback
     *
     * @param logosName the name given by the user
     */
    public void giveFeedback(String logosName) {
        boolean response = game.match(logosName);
        if (response) {
            Toast toast = Toast.makeText(getApplicationContext(), "Correct answer!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Oof, that was wrong!\nThe correct answer was -> " + game.getCurrentLogo().first, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Gets a new logo and sets the image and clears the name (next level)
     */
    public void nextLevel() {
        try {
            Pair<String, Integer> logo = game.getLogo();
            logoView.setImageResource(logo.second);
        } catch (Exception e) {
            Intent intent = new Intent(this, ThatsAllFolksActivity.class);
            intent.putExtra("score", game.getScore());
            startActivity(intent);
        }
    }
}


