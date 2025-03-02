package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

public class MainActivity extends AppCompatActivity {

    Button button;

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

        button = findViewById(R.id.buttonNext);

        // הגדרת פעולה לכפתור
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // יצירת Intent כדי לעבור ל-SecondActivity
                Intent intent = new Intent(MainActivity.this, spActivity.class);
                startActivity(intent);
            }
        });
    }

    // Inflate the options menu with items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
}
