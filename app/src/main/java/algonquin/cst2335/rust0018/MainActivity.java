package algonquin.cst2335.rust0018;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import algonquin.cst2335.rust0018.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override // this starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate( getLayoutInflater() ); //produces layout


        // loads buttons, text on screen
        setContentView(binding.getRoot()); // load stuff on screen

            /*
            R means res
            layout is the folder
            activity_main is the file
            */

        binding.button.setOnClickListener( v -> {
            String editString = binding.editText.getText().toString();
            binding.textView.setText(editString);
            binding.editText.setText(editString);
            binding.button.setText(editString);
        });
    }
}