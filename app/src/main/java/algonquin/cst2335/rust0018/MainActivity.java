package algonquin.cst2335.rust0018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import algonquin.cst2335.rust0018.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    private static String TAG = "MainActivity";

    @Override // this starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView((binding.getRoot()));

        Log.w( TAG, "In onCreate() - Loading Widgets" );
        Log.d( TAG, "Message");
        binding.loginButton.setOnClickListener(btn -> {
            Intent intent = new Intent(MainActivity.this , SecondActivity.class);
            intent.putExtra("Email", binding.emailEditText.getText().toString());

            startActivity( intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w( TAG, "In onStart - Activity is now visible" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w( TAG, "In onResume() - Touch screen now responsive" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( TAG, "In onPause() - No longer listening to touches" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( TAG, "In onStop() - Activity running in the background" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( TAG, "In onDestroy - Activity destroyed" );
    }

}