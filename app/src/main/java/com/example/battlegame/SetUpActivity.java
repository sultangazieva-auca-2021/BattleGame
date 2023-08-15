package com.example.battlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.battlegame.databinding.ActivitySetUpBinding;

public class SetUpActivity extends AppCompatActivity {
    private ActivitySetUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (binding.continueButton != null) {
            binding.continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SetUpActivity.this, MainActivity.class);

                    String name1 = "";
                    if (binding.player1NameEditText != null) {
                        name1 = binding.player1NameEditText.getEditableText().toString();
                    }
                    if (!name1.isEmpty()) {
                        intent.putExtra("namePlayer1", binding.player1NameEditText.getEditableText().toString());
                    } else {
                        intent.putExtra("namePlayer1", "Player 1");
                    }

                    String name2 = "";
                    if (binding.player2NameEditText != null) {
                        name2 = binding.player2NameEditText.getEditableText().toString();
                    }
                    if (!name2.isEmpty()) {
                        intent.putExtra("namePlayer2", binding.player2NameEditText.getEditableText().toString());
                    } else {
                        intent.putExtra("namePlayer2", "Player 2");
                    }

                    String goal = "";
                    if (binding.goalEditText != null) {
                        goal = binding.goalEditText.getEditableText().toString();
                    }
                    if (!goal.isEmpty()) {
                        intent.putExtra("goal", binding.goalEditText.getEditableText().toString());
                    } else {
                        intent.putExtra("goal", "1");
                    }

                    startActivity(intent);
                }
            });
        }
    }
}
