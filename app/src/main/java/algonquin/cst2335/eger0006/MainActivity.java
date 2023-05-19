package algonquin.cst2335.eger0006;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring based on id's
        TextView theText = findViewById(R.id.textview);
        Button myButton =findViewById(R.id.mybutton);
        EditText myedit = findViewById(R.id.myedittext);

        //button click listener
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is run when you click the button
                String editString = myedit.getText().toString();
                theText.setText("Your edit text has : "+ editString);
            }
        });
    }
}