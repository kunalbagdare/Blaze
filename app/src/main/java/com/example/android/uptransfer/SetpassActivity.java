package com.example.android.uptransfer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

@SuppressWarnings("SpellCheckingInspection")
public class SetpassActivity extends AppCompatActivity {

    private Button setpass;
    private EditText pass;
    private TextView text;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpass);
        setpass = findViewById(R.id.set_button);
        pass = findViewById(R.id.set_pass);
        text = findViewById(R.id.pass_confirm);



            setpass.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    String userpass = pass.getText().toString();
                    if(userpass.isEmpty())
                    {
                        text.setTextColor(R.color.colorPrimary);
                        text.setText("Password cannot be empty!!");
                        text.setVisibility(View.VISIBLE);
                    }
                    else {
                        setmethod(userpass);
                        text.setVisibility(View.VISIBLE);
                    }
                }
            });








    }


    public void setmethod(String ps)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
         reference = fb.getReference().child("Password").child(uid).child("password");
         reference.setValue(ps);
    }
}