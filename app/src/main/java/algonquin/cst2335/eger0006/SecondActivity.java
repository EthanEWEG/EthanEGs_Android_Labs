package algonquin.cst2335.eger0006;

import static android.provider.Telephony.Mms.Part.FILENAME;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private static final String FILENAMEE = "Picture.png";

    private static final String PREFS_NAME = "MyPrefs";
    private static final String PHONE_KEY = "PhoneKey";
    private SharedPreferences sharedPreferences;
    private EditText phoneN;

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        EditText editTextPhone = findViewById(R.id.editTextPhone);
        String phoneNumber = editTextPhone.getText().toString();

        // Save the email address to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHONE_KEY, phoneNumber);
        editor.apply();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        Button picButton = findViewById(R.id.button3);
        ImageView picImageView = findViewById(R.id.imageView);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = new File( getFilesDir(), FILENAMEE);

        if(file.exists())

        {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            picImageView.setImageBitmap( theImage );
        }

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        TextView welcomeMessageTextView = findViewById(R.id.textView);
        String welcomeMessage = "Welcome back " + emailAddress;

        welcomeMessageTextView.setText(welcomeMessage);

        //Calls based on inputted text
        Button phoneButton = findViewById(R.id.button);

        EditText editTextPhone = findViewById(R.id.editTextPhone);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        phoneButton.setOnClickListener(clk -> {

            String phoneNumber = editTextPhone.getText().toString();

            // Save the email address to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(PHONE_KEY, phoneNumber);
            editor.apply();

            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(call);
        });

        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            picImageView.setImageBitmap(thumbnail);

                            FileOutputStream fOut = null;

                            try {
                                fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                fOut.flush();
                                fOut.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                });


        //Change picture

        picButton.setOnClickListener(clk -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    cameraResult.launch(cameraIntent);
                else
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 20);
            }


        });

    }
}