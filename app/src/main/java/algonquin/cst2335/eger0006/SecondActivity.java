package algonquin.cst2335.eger0006;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        TextView welcomeMessageTextView = findViewById(R.id.textView);
        String welcomeMessage = "Welcome back " + emailAddress;

        welcomeMessageTextView.setText(welcomeMessage);

        //Calls based on inputted text
        Button phoneButton = findViewById(R.id.button);
        phoneButton.setOnClickListener(clk ->{
            EditText editTextPhone = findViewById(R.id.editTextPhone);
            String phoneNumber = editTextPhone.getText().toString();

            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(call);
        });

        //Change picture
        Button picButton = findViewById(R.id.button3);
        ImageView picImageView = findViewById(R.id.imageView);
        picButton.setOnClickListener(clk ->{
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if(checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                startActivity(cameraIntent);
            else
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 20);


            ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){

                        Intent data = result.getData();
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        picImageView.setImageBitmap(thumbnail);
                    }
                }
            });
            cameraResult.launch(cameraIntent);
        });

    }
}