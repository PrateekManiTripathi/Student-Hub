package com.example.studenthub;

import static com.example.studenthub.Constants.topic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studenthub.api.ApiUtilities;
import com.example.studenthub.model.NotificationData;
import com.example.studenthub.model.PushNotifications;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadpdfFile extends AppCompatActivity {

    private ImageView pdf_img;
    private Button select_btn,upload_btn,send_notify;
    private EditText select_title;
    private TextView file_name;
    private String pdfName,title;

    private final int REQ = 1;
    private Uri pdfData;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ProgressDialog pd;
    String downloadUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpdf_file);

        pdf_img = findViewById(R.id.pdf_img);
        select_btn = findViewById(R.id.select_btn);
        upload_btn = findViewById(R.id.upload_btn);
        send_notify = findViewById(R.id.send_notify);
        select_title = findViewById(R.id.select_title);
        file_name = findViewById(R.id.file_name);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        FirebaseMessaging.getInstance().subscribeToTopic(topic);

        pd = new ProgressDialog(this);

        select_btn.setOnClickListener((view) ->{openGallery();});

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = select_title.getText().toString();
                if (title.isEmpty()){
                    select_title.setError("Enter Title");
                    select_title.requestFocus();
                }else if (pdfData == null){
                    Toast.makeText(UploadpdfFile.this, "Please Upload Pdf", Toast.LENGTH_SHORT).show();
                }else{
                    uploadPdf();
                }
            }
        });

        send_notify.setOnClickListener(v -> {
            sendNotification();
        });
    }


    private void sendNotification(){
        String title_txt = select_title.getText().toString();
        String msg_txt = select_title.getText().toString() + "uploded by Admin";

        if (!title_txt.isEmpty()){
            PushNotifications pushNotifications = new PushNotifications(new NotificationData(title_txt,msg_txt),topic);
            sendOnNotification(pushNotifications);
        }
    }

    private void sendOnNotification(PushNotifications pushNotifications) {
        ApiUtilities.getClient().sendNotify(pushNotifications).enqueue(new Callback<PushNotifications>() {
            @Override
            public void onResponse(Call<PushNotifications> call, Response<PushNotifications> response) {
                if (response.isSuccessful()){
                    Toast.makeText(UploadpdfFile.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PushNotifications> call, Throwable t) {
                Toast.makeText(UploadpdfFile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadPdf() {
        pd.setTitle("Please wait....");
        pd.setMessage("Uploading pdf");
        pd.show();
        StorageReference reference = storageReference.child("pdf/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri uri = uriTask.getResult();
                        uploadData(String.valueOf(uri));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadpdfFile.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void uploadData(String downloadUrl) {
        String uniquekey  = databaseReference.child("pdf").push().getKey();

        HashMap data = new HashMap();   //to store the data
        data.put("pdfTitle",title);
        data.put("pdfUrl",downloadUrl);

        databaseReference.child("pdf").child(uniquekey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(UploadpdfFile.this, "Pdf uploaded successfully", Toast.LENGTH_SHORT).show();
                select_title.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadpdfFile.this, "Failed to upload pdf", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK){
           pdfData= data.getData();

           if (pdfData.toString().startsWith("content://")){
               Cursor cursor = null;
               try {
                   cursor = UploadpdfFile.this.getContentResolver().query(pdfData,null,null,null,null);
                   if (cursor !=null && cursor.moveToFirst()){
                       pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else if (pdfData.toString().startsWith("file://")){
               pdfName = new File(pdfData.toString()).getName();
           }

           file_name.setText(pdfName);
        }
    }
}