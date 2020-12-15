package com.example.android.uptransfer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */

public class ChatsFragment extends Fragment {


    private ImageButton send;
    private EditText editText;
    private RecyclerView msg_list;
    private final List<Messages> messagesList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private MessageAdapter mAdapter;
    private DatabaseReference reference;



    public ChatsFragment() {
        // Required empty public constructor

        loadM();

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_chats, container, false);

       // msg_list = rootView.findViewById(R.id.messages_list);
        editText = (EditText)rootView.findViewById(R.id.editText);
        send = (ImageButton) rootView.findViewById(R.id.send);
        mAdapter = new MessageAdapter(messagesList);
        msg_list=(RecyclerView) rootView.findViewById(R.id.messages_list);
        linearLayoutManager = new LinearLayoutManager(getContext());
        msg_list.setHasFixedSize(true);
        msg_list.setLayoutManager(linearLayoutManager);
        msg_list.setAdapter(mAdapter);







        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editText.getText().toString().isEmpty()) {
                    Date date = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    String time = dateFormat.format(date);
                    dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dt = dateFormat.format(date);
                    String msg = editText.getText().toString();
                    Messages m = new Messages(dt, msg, time,"client");
                    Chat_main cm = new Chat_main(m);
                    //messagesList.add(m);
                    mAdapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }


        });

        return rootView;
    }



    public void loadM()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        FirebaseDatabase database =FirebaseDatabase.getInstance();
        reference = database.getReference("messages").child(uid);
        reference.keepSynced(true);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Messages m = dataSnapshot.getValue(Messages.class);
                messagesList.add(m);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





}
