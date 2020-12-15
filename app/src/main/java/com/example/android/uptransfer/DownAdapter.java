package com.example.android.uptransfer;

import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.getExternalStorageState;
import static android.os.Environment.getRootDirectory;

public class DownAdapter extends RecyclerView.Adapter<MyViewHolder>{
    DownloadActivity downloadActivity;
    ArrayList<Downloads> downloads;

    public DownAdapter(DownloadActivity downloadActivity, ArrayList<Downloads> downloads) {
        this.downloadActivity = downloadActivity;
        this.downloads = downloads;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(downloadActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.download_list,null,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Downloads c = downloads.get(position);
        MyViewHolder.mFilename.setText(c.getFilename());
        MyViewHolder.mLink.setText(c.getLink());
        MyViewHolder.mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory()+"/Blaze/";
                Log.v("path",path);
                downloadFile(MyViewHolder.mFilename.getContext(),downloads.get(position).getFilename(),"",path,downloads.get(position).getLink());
            }
        });

        MyViewHolder.mCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context r= downloadActivity.getBaseContext();
                ClipboardManager clipboard = (ClipboardManager) r.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(null,downloads.get(position).getLink());
                clipboard.setPrimaryClip(clip);
            }
        });

    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadmanager.enqueue(request);
    }


    @Override
    public int getItemCount() {
        return downloads.size();
    }
}
