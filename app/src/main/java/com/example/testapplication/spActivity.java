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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

       // editor.putBoolean("music", true); // Storing boolean - true/false
        editor.putString("userName", "string value"); // Storing string
        editor.putInt("level", Integer.parseInt("int value")); // Storing integer


        editor.commit(); // commit changes


        pref.getString("userName", null); // getting String
        pref.getInt("level", -1); // getting Integer
       // boolean music = pref.getBoolean("music", null);// getting boolean
    }


    public void Save(View view) {
        String n = userName.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.commit();
    }

    public void clear(View view) {
        userName = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        userName.setText("");
        email.setText("");

    }

    public void Get(View view) {
        userName = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            userName.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }
    }
}