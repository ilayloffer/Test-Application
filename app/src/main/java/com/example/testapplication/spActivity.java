package com.example.testapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class spActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView userName;
    TextView email;
    public static final String mypreference = "mypref";
    public static final String Name = "userNameKey";
    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sp);

        // Initialize SharedPreferences
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        // Initialize views
        userName = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);

        // Adjust the padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Save(View view) {
        String n = userName.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.apply(); // Use apply() instead of commit()
    }

    public void clear(View view) {
        userName.setText("");
        email.setText("");
    }

    public void Get(View view) {
        if (sharedpreferences.contains(Name)) {
            userName.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));
        }
    }
}
