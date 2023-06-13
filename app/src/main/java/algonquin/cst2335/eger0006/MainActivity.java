package algonquin.cst2335.eger0006;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onStart() {
        super.onStart();

        Log.w(TAG,"In onStart() - The application is now visible on screen");

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.w(TAG,"In onPause() - The application no longers responds to user input");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.w(TAG,"In onResume() - Application is now responding to user input");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.w(TAG,"In onDestroy() - Any memory used by the application is freed");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.w(TAG,"In onStop() - The application is no longer visible");

    }

    private static final String PREFS_NAME = "MyPrefs";
    private static final String EMAIL_KEY = "EmailKey";
    private SharedPreferences sharedPreferences;
    private EditText emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        emailAddress = findViewById(R.id.editTextTextEmailAddress);

        // Retrieve the saved email address if available
        String savedEmail = sharedPreferences.getString(EMAIL_KEY, "");
        emailAddress.setText(savedEmail);

        Button loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(clk -> {
            String email = emailAddress.getText().toString();

            // Save the email address to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(EMAIL_KEY, email);
            editor.apply();

            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra("EmailAddress", email);
            startActivity(nextPage);
        });



    }
}