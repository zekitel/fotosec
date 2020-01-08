package com.project.fotosec;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public  class ExampleController {
    @GetMapping(value = "/index")
    public String getHomePage() throws IOException, ExecutionException, InterruptedException {
        FileInputStream serviceAccount = new FileInputStream("./ServiceAccountKey.json");
        FirebaseOptions options =new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://fotosecuploadproject.firebaseio.com")
                .build();
        FirebaseApp fireApp=FirebaseApp.initializeApp(options);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= (DatabaseReference) database.getReference().child("users");
        Map<String, String> users = new HashMap<>();
        users.put("furkan","12312");


        databaseReference.setValueAsync(users);


/*
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document("yusuf");
// Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Zeki");
        data.put("last", "TEL");
        data.put("born", 1815);
//asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
// ...
// result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());

/*

        DatabaseReference rootRef= FirebaseDatabase.getInstance().getReference();

        DatabaseReference childRef=rootRef.child("clients").push();
        childRef.setValueAsync("yusuf");

        Bucket bucket=StorageClient.getInstance().bucket();



        StorageClient storageClient = StorageClient.getInstance(fireApp);
        InputStream testFile = new FileInputStream("./a.txt");
        String blobString = "NEW_FOLDER/" + "FILE_NAME.EXT";

        storageClient.bucket().create(blobString, testFile , Bucket.BlobWriteOption.userProject("fotosec-8da32"));
        */
        return "merhaba";
    }
}