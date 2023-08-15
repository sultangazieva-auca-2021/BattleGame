package com.example.battlegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.battlegame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (binding.buttonPlayer1 != null) {
            binding.buttonPlayer1.setOnClickListener(this);
        }
        if (binding.buttonPlayer2 != null) {
            binding.buttonPlayer2.setOnClickListener(this);
        }
        if (binding.buttonDecrement1 != null) {
            binding.buttonDecrement1.setOnClickListener(this);
        }
        if (binding.buttonDecrement2 != null) {
            binding.buttonDecrement2.setOnClickListener(this);
        }

        Intent intent = getIntent();
        if (intent != null) {
            String name1 = intent.getStringExtra("namePlayer1");
            String name2 = intent.getStringExtra("namePlayer2");
            String goal = intent.getStringExtra("goal");

            binding.player1Name.setText(name1);
            binding.player2Name.setText(name2);
            binding.goalNum.setText("Goal = " + goal);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.restart) {
            Intent intent = new Intent(getBaseContext(), SetUpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (item.getItemId() == R.id.main_menu) {
            Intent intent = new Intent(getBaseContext(), FilloutActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.change_player1_name || item.getItemId() == R.id.goal_change || item.getItemId() == R.id.change_player2_name) {
            Intent intent = new Intent(getBaseContext(), SetUpActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.exit) {
            finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_player1) {
            int counter = 0;
            if (binding.points1 != null) {
                counter = Integer.parseInt(binding.points1.getText().toString());
            }
            YoYo.with(Techniques.SlideInLeft)
                    .duration(300)
                    .repeat(0)
                    .playOn(binding.battleImage);

            counter++;
            binding.points1.setText(String.valueOf(counter));
        } else if (view.getId() == R.id.button_player2) {
            int counter = 0;
            if (binding.points2 != null) {
                counter = Integer.parseInt(binding.points2.getText().toString());
            }
            YoYo.with(Techniques.SlideInRight)
                    .duration(300)
                    .repeat(0)
                    .playOn(binding.battleImage);

            counter++;
            binding.points2.setText(String.valueOf(counter));
        } else if (view.getId() == R.id.button_decrement1) {
            int counter = 0;
            if (binding.points1 != null) {
                counter = Integer.parseInt(binding.points1.getText().toString());
            }
            if (counter != 0) {
                counter--;
                binding.points1.setText(String.valueOf(counter));
            }
        } else if (view.getId() == R.id.button_decrement2) {
            int counter = 0;
            if (binding.points2 != null) {
                counter = Integer.parseInt(binding.points2.getText().toString());
            }
            if (counter != 0) {
                counter--;
                binding.points2.setText(String.valueOf(counter));
            }
        }

        checkThePointsWithGoal();
    }

    private void checkThePointsWithGoal() {
        if (binding.goalNum != null && binding.points1 != null && binding.points2 != null) {
            String goalInput = binding.goalNum.getText().toString().trim();
            String[] words = goalInput.split(" ");
            if (words.length >= 3) {
                int goal = Integer.parseInt(words[2]);
                int pointsPlayer1 = Integer.parseInt(binding.points1.getText().toString());
                int pointsPlayer2 = Integer.parseInt(binding.points2.getText().toString());

                if (goal == pointsPlayer1 || goal == pointsPlayer2) {
                    Intent intent1 = new Intent(MainActivity.this, WinWindowActivity.class);
                    intent1.putExtra("goal", String.valueOf(goal));
                    intent1.putExtra("pointsPlayer1", String.valueOf(pointsPlayer1));
                    intent1.putExtra("pointsPlayer2", String.valueOf(pointsPlayer2));

                    if (binding.player1Name != null) {
                        intent1.putExtra("player1_name", binding.player1Name.getText().toString());
                    }
                    if (binding.player2Name != null) {
                        intent1.putExtra("player2_name", binding.player2Name.getText().toString());
                    }

                    playAnimation(intent1, goal == pointsPlayer1 ? Techniques.BounceInLeft : Techniques.BounceInRight);
                }
            }
        }
    }

    private void playAnimation (Intent intent, Techniques techniques) {
        YoYo.with(techniques)
                .duration(700)
                .repeat(0)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                }).playOn(binding.battleImage);
    }
}
