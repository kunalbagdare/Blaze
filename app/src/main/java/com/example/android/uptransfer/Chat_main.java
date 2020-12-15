package com.example.android.uptransfer;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Chat_main {



    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    public Chat_main(Messages m){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db = databaseReference.child("messages").child(uid).push();
        Map<String,String> msg = new HashMap<>();
        msg.put("date",m.getDate());
        msg.put("message",m.getMessage());
        msg.put("time",m.getTime());
        msg.put("type",m.getType());
        db.setValue(msg);



    }





}
