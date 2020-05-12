package com.example.guessthelogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The game class used for the game logic
 */
public class Game {
    private Context context;
    private int highscore;
    private int score;
    private HashMap<String, Integer> logos;
    private Pair<String, Integer> currentLogo;

    Game(Context context) {
        this.highscore = 0;
        this.context = context;
        this.score = 0;
        this.logos = loadLogos();
    }

    /**
     * Method for getting the names of the logos in the logos folder
     *
     * @return list of logos names
     */
    private HashMap<String, Integer> loadLogos() {
        HashMap<String, Integer> logos = new HashMap<>();
        Field[] ID_Fields = R.mipmap.class.getFields();
        ArrayList<String> logoNames = new ArrayList<>();
        for (Field id_field : ID_Fields) {
            try {
                logos.put(id_field.getName(), id_field.getInt(null));
                logoNames.add(id_field.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (String logo : logoNames) {
            if (logo.contains("_")) {
                logos.remove(logo);
            }
        }

        return logos;
    }

    /**
     * Method for getting a logo for the current level
     *
     * @return pair of logo's name and id for the resource
     */
    public Pair<String, Integer> getLogo() throws Exception {
        Random generator = new Random();
        Object[] keys = this.logos.keySet().toArray();
        Object randomLogo = keys[generator.nextInt(keys.length)];
        Integer logoId = logos.get(randomLogo);

        this.currentLogo = new Pair<>((String) randomLogo, logoId);
        logos.remove(randomLogo);

        return this.currentLogo;
    }

    /**
     * Checks if the name typed matches the logo's name
     *
     * @param nameGuess the name gave by the user
     * @return true/false if param matches or not the current logo's name
     */
    public boolean match(String nameGuess) {
        if (currentLogo.first.equals(nameGuess.toLowerCase())) {
            score++;

            if (score > highscore) {
                setHighscore(score);
                setHighscorePreference(score);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Saves highscore in SharedPreferences
     *
     * @param score the score
     */
    public void setHighscorePreference(int score) {
        SharedPreferences pref = context.getSharedPreferences("GuessTheLogoData", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("highscore", score);
        editor.commit();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public HashMap<String, Integer> getLogos() {
        return logos;
    }

    public void setLogos(HashMap<String, Integer> logos) {
        this.logos = logos;
    }

    public Pair<String, Integer> getCurrentLogo() {
        return currentLogo;
    }

    public void setCurrentLogo(Pair<String, Integer> currentLogo) {
        this.currentLogo = currentLogo;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
