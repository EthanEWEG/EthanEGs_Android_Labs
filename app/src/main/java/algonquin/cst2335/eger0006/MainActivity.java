package algonquin.cst2335.eger0006;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import algonquin.cst2335.eger0006.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        //Declaring based on id's
        TextView theText = variableBinding.textview;
        Button myButton =variableBinding.mybutton;
        EditText myedit = variableBinding.myedittext;

        //button click listener
        myButton.setOnClickListener( vw -> {
            //this is run when you click the button
            String editString = myedit.getText().toString();
            theText.setText("Your edit text has : "+ editString);
        });
    }
}