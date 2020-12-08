package com.be2.test3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_draw;
    private List<CatbyMod> list;
    private Main_Adapter adapter;
    EditText edit;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit =findViewById(R.id.edit);
        search=findViewById(R.id.button);
        rv_draw = findViewById(R.id.rv);
        rv_draw.setLayoutManager(new LinearLayoutManager(this));

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edit.getText().toString();
                getmodulerequest(text);
            }
        });


    }

    private void getmodulerequest(String text) {
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, "http://omdbapi.com/?s="+text+"&&plot=full&apikey=936fcdb1" , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG", "datad" + response);
                JSONObject obj = null;
                try {
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("Response");
                    if (status == true) {
                        list = new ArrayList<>();
                        obj = new JSONObject(response);
                        JSONArray array = obj.getJSONArray("Search");
                        for (int i = 0; i < array.length(); i++) {
                            Log.i("TAG", "obj" + array);
                            JSONObject ob = array.getJSONObject(i);
                            CatbyMod ld = new CatbyMod(
                                    ob.getString("Title"),
                                    ob.getString("Year"),
                                    ob.getString("Poster"),
                                    ob.getString("Type"));
                            list.add(ld);
                            Log.i("TAG", "list" + ld);
                        }
                        adapter = new Main_Adapter(list);
                        rv_draw.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("User-Agent", "Mozilla/5.0");
            return params;
        }};

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}