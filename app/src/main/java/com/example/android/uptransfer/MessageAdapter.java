package com.example.android.uptransfer;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.MeasureFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<Messages> myList;
    static String cdate = "temp";

    public MessageAdapter(List<Messages> list) { this.myList = list; }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView messageDate;
        private TextView messageText;
        private TextView messageTime;
        private RelativeLayout message;
        private RelativeLayout dt;


        public MyViewHolder(View view) {
            super(view);
                messageDate = view.findViewById(R.id.msg_date);
                messageText= view.findViewById(R.id.message_text_layout);
                messageTime = view.findViewById(R.id.msg_time);
                //message = view.findViewById(R.id.message_single_layout);
                dt=view.findViewById(R.id.dt);


        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_single_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dt = dateFormat.format(date);
        Messages c = myList.get(position);
        String type = c.getType();



        if(type.equals("server"))
        {

            holder.messageText.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.messageText.setTextColor(Color.WHITE);
            holder.messageDate.setTextColor(Color.WHITE);
            holder.messageText.setBackgroundResource(R.drawable.message_server);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.messageText.getLayoutParams();
            params.setMarginStart(400);
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            holder.messageText.setLayoutParams(params);





        }
        else if (type.equals("client")){
            holder.messageText.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.messageText.setBackgroundResource(R.drawable.message_client);
            holder.messageText.setTextColor(Color.BLACK);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.messageText.getLayoutParams();
            params.setMarginEnd(400);
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            holder.messageText.setLayoutParams(params);


        }


        if(cdate.equals("temp")||!cdate.equals(dt)) {
            holder.messageDate.setText(c.getDate());
            cdate= dt;
        }
        else if (cdate.equals(dt)){
            holder.messageDate.setVisibility(View.INVISIBLE);
        }
        holder.messageText.setText(c.getMessage());
        holder.messageTime.setText(c.getTime());



    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}