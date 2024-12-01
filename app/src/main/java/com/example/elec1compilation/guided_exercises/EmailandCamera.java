package com.example.elec1compilation.guided_exercises;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.elec1compilation.MainActivity;
import com.example.elec1compilation.R;

public class EmailandCamera extends AppCompatActivity {

    Button send, capturePic;
    EditText receiver, subject, message;
    ImageView pic;
    Intent intent,chooser;
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge13);
        init();
        sendEmail();
        enableRuntimePermission();
        capturePic();
    }

    public void init(){
        receiver = findViewById(R.id.etReceiver);
        subject = findViewById(R.id.etSubject);
        message = findViewById(R.id.etMessage);
        pic = findViewById(R.id.ivPic);
        send = findViewById(R.id.btnSend);
        capturePic = findViewById(R.id.btnCapturePic);
    }

    public void sendEmail(){
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Deliver some data to someone else. Who the data is delivered to
                // is not specified; it is up to the receiver of this action to ask the
                // user where the data should be sent.
                intent = new Intent(Intent.ACTION_SEND);
                // Set the data this intent is operation on.
                // This method automatically clears any type that was previously set by setType(String)
                // The URI (a Uri object) that references the data to be acted on and/or the MIME
                // type of that data. The type of data supplied is generally dictated byy the intent's action.
                intent.setData(Uri.parse("mailto:"));
                // This will hold of multiple email of receiver
                String[] to = {receiver.getText().toString()};
                // Android has built-in support to add TO, SUBJECT, CC, TEXT etc., fields which can be
                // attached to the intent before sending the intent to a target email client.
                intent.putExtra(Intent.EXTRA_EMAIL, to); // for recepient
                intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString()); // subject of email
                intent.putExtra(Intent.EXTRA_TEXT,message.getText().toString()); // body of email
                // This is used to create intents that only specify a type and not data, for example
                // to indicate the type of data to return.
                intent.setType("message/rfc822");
                // When launching a SEND intent, you should usually wrap it in a chooser through
                // createChooser(Intent,CharSequence) which will give the proper interface for the
                // user to pick how to send your data and allow you to specify a prompt indicating
                // what they are doing.
                chooser = intent.createChooser(intent,"Send Email");

                if(receiver.getText().toString().isEmpty()){
// it will show an exclamation point icon to the editText receiver
                    // once it's detected empty.
                    receiver.setError("Email required!");
                }else{
                    // start activity chooser
                    startActivity(chooser);
                }
            }
        });
    }
    // This method will help to retrieve the image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 7 && resultCode == RESULT_OK){
            // BitMap is data structure of image file which store the image in memory
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            pic.setImageBitmap(bitmap);
        }
    }

    public void  enableRuntimePermission(){
        // This will check the permission and if granted by the user in Manifest file for CAMERA
        if(ActivityCompat.shouldShowRequestPermissionRationale(EmailandCamera.this, Manifest.permission.CAMERA)){
            Toast.makeText(getApplicationContext(),"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();
        }else{
            ActivityCompat.requestPermissions(EmailandCamera.this, new String[]{Manifest.permission.CAMERA},RequestPermissionCode);
        }
    }

    public void capturePic(){
        // for more detail documentation check this link below
        // https://howtodoandroid.com/capture-image-android/
        capturePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,7);
            }
        });
    }
}
