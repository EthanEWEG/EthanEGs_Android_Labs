package algonquin.cst2335.eger0006;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Password page with a constraint checking function
 *
 * @author Ethan EG
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Checks password complexity. 1 Uppercase, 1 Lowercase, a number, and a special symbol
     * @param pw the password to check
     * @return returns true or false whether or not the password passes the constraints
     */
    boolean checkPasswordComplexity(String pw){
        /**
         * Variables used to check each password constraint is passed
         */
        boolean foundUC, foundLC, foundN, foundS;
        foundUC = foundLC = foundN = foundS = false;

        /**
         * loop checks all password constraints are passed
         */
        for (int i=0; i<pw.length(); i++){
            char c = pw.charAt(i);

            if(Character.isDigit( c )){
                foundN = true;
            }
            if(Character.isUpperCase( c )){
                foundUC = true;
            }
            if(Character.isLowerCase( c )){
                foundLC = true;
            }
            if(c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '!' || c == '@' || c == '?'){
                foundS = true;
            }
        }

        /**
         * returns true or false based on whether or not all password constraints are passed
         */
        if (foundUC && foundLC && foundN && foundS){
            return true;
        }
        else{
            if(!foundUC){
                Toast.makeText(getApplicationContext(),"Missing upper case letter",Toast.LENGTH_SHORT).show();
                return false;
            }

            else if(!foundLC){
                Toast.makeText(getApplicationContext(),"Missing lower case letter",Toast.LENGTH_SHORT).show();
                return false;
            }

            else if(!foundN){
                Toast.makeText(getApplicationContext(),"Missing atleast 1 number",Toast.LENGTH_SHORT).show();
                return false;
            }
            else{
                Toast.makeText(getApplicationContext(),"Missing atleast 1 special character",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    /** this holds text at the center of the screen */
    private TextView tv = null;
    /** this is where the password is typed */
    private EditText et = null;
    /** this is the button which the user clicks to login */
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.password);
        btn = findViewById(R.id.button);

        btn.setOnClickListener( clk ->{
            String password = et.getText().toString();

            if (checkPasswordComplexity(password)){
                tv.setText("Your password meets the requirements");
            }
            else{
                tv.setText("You shall not pass!");
            }
        });

    }
}