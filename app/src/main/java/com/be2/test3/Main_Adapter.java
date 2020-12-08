package com.be2.test3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.MyViewHolder>{
    private List<CatbyMod> modelList;
    private Context context;


    public Main_Adapter(List<CatbyMod> list) {
        this.modelList = list;
    }



    @NonNull
    @Override
    public Main_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawlist1, parent, false);
        context = parent.getContext();
        return new Main_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Main_Adapter.MyViewHolder holder, final int position) {
        final CatbyMod mList = modelList.get(position);
        Picasso.with(context).load(mList.getPoster()).into(holder.image);
        holder.source.setText(mList.getYear());
        holder.title.setText(mList.getTitle());
        holder.date.setText(mList.getType());


    }




    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description,date,source;
        public ImageView image;
        Button delete,edit;
        LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            source = (TextView)view.findViewById(R.id.source);


        }
    }

}

