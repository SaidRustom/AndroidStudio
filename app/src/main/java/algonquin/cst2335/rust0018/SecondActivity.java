package algonquin.cst2335.rust0018;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import algonquin.cst2335.rust0018.databinding.ActivityMainBinding;
import algonquin.cst2335.rust0018.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding binding = ActivitySecondBinding.inflate(getLayoutInflater());

        setContentView((binding.getRoot()));
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("Email");
        binding.textView.setText("Welcome back " + emailAddress);


        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override

                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            FileOutputStream fOut = null;
                            File sandbox = getFilesDir();
                            binding.imageView.setImageBitmap(thumbnail);
                            try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                                fOut.flush();

                                fOut.close();

                            }

                            catch (FileNotFoundException e)

                            { e.printStackTrace();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        binding.CallNumber.setOnClickListener(btn -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            startActivity(call.setData(Uri.parse("tel:"+binding.editTextPhone.getText().toString())));
        });
        binding.ChangePicture.setOnClickListener( click -> {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
           // startActivity(cameraIntent);

                 cameraResult.launch(cameraIntent);
             });



    }
}