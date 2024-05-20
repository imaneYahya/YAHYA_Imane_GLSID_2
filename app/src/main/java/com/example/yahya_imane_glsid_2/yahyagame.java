package com.example.yahya_imane_glsid_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class yahyagame extends AppCompatActivity {

    private TextView textViewPlayerFortune;
    private TextView textViewCasinoFortune;
    private Button buttonRollDice;
    private TextView textViewResult;
    private ImageView diceImageView;
    private Button buttonTerminate;

    private int playerFortune;
    private int casinoFortune;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yahya_game);

        textViewPlayerFortune = findViewById(R.id.textViewPlayerFortune);
        textViewCasinoFortune = findViewById(R.id.textViewCasinoFortune);
        buttonRollDice = findViewById(R.id.buttonRollDice);
        textViewResult = findViewById(R.id.textViewResult);
        diceImageView = findViewById(R.id.diceImage);
        buttonTerminate = findViewById(R.id.buttonTerminate);



        playerFortune = 100;
        casinoFortune = new Random().nextInt(91) + 10;

        updateFortuneTextViews();

        buttonRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        buttonTerminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishGame();
            }
        });
    }

    private void updateFortuneTextViews() {
        textViewPlayerFortune.setText("Player's Fortune: " + playerFortune + "€");
        textViewCasinoFortune.setText("Casino's Fortune: " + casinoFortune + "€");
    }

    private void rollDice() {
        int diceResult = new Random().nextInt(6) + 1;
        updateDiceImage(diceResult);
        if (diceResult == 2 || diceResult == 3) {
            playerFortune++;
            casinoFortune--;
            textViewResult.setText("Result: Player wins 1€");
        } else {
            playerFortune--;
            casinoFortune++;
            textViewResult.setText("Result: Casino wins 1€");
        }
        updateFortuneTextViews();
        if (playerFortune <= 0 || casinoFortune <= 0) {
            finishGame();
        }
    }
    private void updateDiceImage(int diceRoll) {
        String imageName = "dice_" + diceRoll;
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        diceImageView.setImageResource(resId);
    }

    private void finishGame() {
        String winner;
        if (playerFortune <= 0 && casinoFortune <= 0) {
            winner = "No one (Both player and casino are ruined)";
        } else if (playerFortune <= 0) {
            winner = "Casino";
        } else {
            winner = "Player";
        }
        String message = "Game Over!\n";
        message += "Winner: " + winner + "\n";
        message += "Player's Fortune: " + playerFortune + "€\n";
        message += "Casino's Fortune: " + casinoFortune + "€";
        textViewResult.setText(message);
        buttonRollDice.setEnabled(false);
        buttonTerminate.setEnabled(false);
    }
}
