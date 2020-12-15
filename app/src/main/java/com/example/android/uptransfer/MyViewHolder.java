package com.example.android.uptransfer;


import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    static TextView mFilename;
    static TextView mLink;
    static ImageButton mDownload;
    static ImageButton mCopy;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mFilename= itemView.findViewById(R.id.filename);
        mLink= itemView.findViewById(R.id.link);
        mDownload= itemView.findViewById(R.id.down_button);
        mCopy= itemView.findViewById(R.id.copy_link);
    }
}



