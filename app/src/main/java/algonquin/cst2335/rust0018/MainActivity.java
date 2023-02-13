package algonquin.cst2335.rust0018;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Said
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** This holds the text at the center of the screen*/
    private TextView tv = null;

    /** This holds the password entered by the user*/
    private EditText pass = null;

    /** This holds the button to submit the password*/
    private Button btn = null;

    @Override // this starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // loads buttons, text on screen
        setContentView(R.layout.activity_main);
            /*
            R means res
            layout is the folder
            activity_main is the file
            */
         tv = findViewById(R.id.textView);
         pass = findViewById(R.id.editTextTextPassword);
         btn = findViewById(R.id.buttonLogin);

        btn.setOnClickListener( clk ->{
            String password = pass.getText().toString();

            if(checkPasswordComplexity(password)){
                tv.setText("you shall pass");
            }else {
                tv.setText("you shall not pass");
            }
        });
    }

    /**
     * this function checks how complex is the given string (for password)
     * @param pw The String object that we are checking
     * @return Returns true if pw is complex enough
     */
    boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase;
        boolean foundLowerCase;
        boolean foundNumber;
        boolean foundSpecial;
        foundSpecial = foundNumber = foundLowerCase = foundUpperCase = false;
        for (int i = 0; i < pw.length(); i++) {
            char[] arr = pw.toCharArray();
            Character c = arr[i];
            if (isDigit(c) == true) foundNumber = true;
            if (isUpperCase(c) == true) foundUpperCase = true;
            if (isLowerCase(c) == true) foundLowerCase = true;
            if ( c.toString().equals("#") || c.toString().equals("$") || c.toString().equals("^")
                    || c.toString().equals("&") || c.toString().equals("*") ||
                    c.toString().equals("!") || c.toString().equals("@")) foundSpecial = true;
        }
        if (foundSpecial && foundNumber && foundLowerCase && foundUpperCase == true) return true;
        else return false;
    }



}
