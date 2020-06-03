package com.vijay.volleyarray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vijay.volleyarray.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    String url="http://www.androindian.com/apps/blog_links/api.php";

    ArrayList<String> Tarray=new ArrayList<String>();
    ArrayList<String> iarray=new ArrayList<String>();
    ArrayList<String> uarray=new ArrayList<String>();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        requestQueue= Volley.newRequestQueue(MainActivity.this);

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_all_links");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String res=response.getString("response");

                            if(res.equalsIgnoreCase("success")){

                                //array
                                JSONArray jsonArray=response.getJSONArray("data");
                                Log.i("res","array"+jsonArray.toString());

                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject j2=jsonArray.getJSONObject(i);
                                    Log.i("res","withinarray"+j2.toString());

                                    String tittle=j2.getString("title");
                                    Log.i("res","Title"+tittle.toString());

                                    String xyzurl=j2.getString("url");
                                    Log.i("res","url"+xyzurl.toString());

                                    String id=j2.getString("id");
                                    Log.i("res","id"+id.toString());

                                    Tarray.add(tittle);
                                    Log.i("res","t"+Tarray.toString());

                                    iarray.add(id);
                                    Log.i("res","i"+iarray.toString());

                                    uarray.add(xyzurl);
                                    Log.i("res","u"+uarray.toString());
                                }

                                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,
                                        LinearLayoutManager.VERTICAL,false);
                                binding.rec.setLayoutManager(linearLayoutManager);
                                binding.rec.setAdapter(new Appadp(MainActivity.this, Tarray,iarray,uarray));


                            }else {
                                Toast.makeText(MainActivity.this, "no Data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}