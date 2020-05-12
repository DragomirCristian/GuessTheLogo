package com.example.guessthelogo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Base Activity used for creating menu and override that is extended by the other activities
 */
public class BaseActivity extends AppCompatActivity {
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    /**
     * Creates the menu
     *
     * @param menu the menu
     * @return boolean variable
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Executes code depending on the item selected from the menu
     *
     * @param item the menuItem
     * @return boolean variable
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game_menu:
                startActivity(new Intent(this, NewGameActivity.class));
                return true;
            case R.id.highscore_menu:
                builder = new AlertDialog.Builder(this);
                builder.setMessage("This is your HighScore: " + getApplicationContext().getSharedPreferences("GuessTheLogoData", 0).getInt("highscore", 0)).setTitle("HighScore");
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.share_menu:
                share(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Shares the HighScore using apps that can do this thing
     *
     * @param view the view
     */
    public void share(View view) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "My highscore at GuessTheLogo is -> " + getApplicationContext().getSharedPreferences("GuessTheLogoData", 0).getInt("highscore", 0));
        startActivity(Intent.createChooser(share, "share"));
    }
}
