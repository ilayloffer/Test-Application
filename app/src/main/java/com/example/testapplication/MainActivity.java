package com.example.testapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Switch musicSwitch;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set onApplyWindowInsetsListener to adjust padding for system bars (navigation bar, status bar, etc.)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the Switch and Button
        musicSwitch = findViewById(R.id.musicSwitch);
        button = findViewById(R.id.buttonNext);

        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);  // Corrected file name


        // Set listener for the Switch
        musicSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start(); // Start music
                    musicSwitch.setText("Pause Music");
                }
            } else {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause(); // Pause music
                    musicSwitch.setText("Play Music");
                }
            }
        });

        // Set up the button to navigate to another activity (spActivity)
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, spActivity.class);
            startActivity(intent);
        });
    }

    // Inflate the options menu with items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle the item selection from the options menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            handleSettings();
            return true;
        } else if (item.getItemId() == R.id.action_help) {
            handleHelp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Handle settings action
    private void handleSettings() {
        Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
    }

    // Handle help action
    private void handleHelp() {
        Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show();
    }

    // Ensure media player is released when the activity is destroyed to avoid memory leaks
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

