package com.example.battlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.battlegame.databinding.ActivityWinWindowBinding;

public class WinWindowActivity extends AppCompatActivity {
    private ActivityWinWindowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWinWindowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            int goal = Integer.parseInt(intent.getStringExtra("goal"));
            String player1Name = intent.getStringExtra("player1_name");
            String player2Name = intent.getStringExtra("player2_name");
            int player1Points = Integer.parseInt(intent.getStringExtra("pointsPlayer1"));
            int player2Points = Integer.parseInt(intent.getStringExtra("pointsPlayer2"));

            if (goal == player1Points) {
                if (binding.winner != null) {
                    binding.winner.setText(player1Name);
                }
            } else if (goal == player2Points) {
                if (binding.winner != null) {
                    binding.winner.setText(player2Name);
                }
            }
        }

        if (binding.restartButton != null) {
            binding.restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), SetUpActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }

        if (binding.exitButton != null) {
            binding.exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finishAffinity();
                }
            });
        }
    }
}
