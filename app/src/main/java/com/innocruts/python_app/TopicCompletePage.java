package com.innocruts.python_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class TopicCompletePage extends AppCompatActivity {

    String SelectedTopicId;
    ProgressDialog pd;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_complete_page);

        getSupportActionBar().hide();
        Intent intent=getIntent();
        SelectedTopicId=intent.getStringExtra("TopicId");
        String TopicStatus=intent.getStringExtra("TopicStatus");
        pref=getApplicationContext().getSharedPreferences("python_app",MODE_PRIVATE);
        editor=pref.edit();
        if(TopicStatus.compareTo("1")!=0) {
            UpdateUserRecord();
            UpdateUserActivity();
        }


    }

    public void Continue(View view)
    {

        Intent intent=new Intent(this,Topic_Activity.class);
        intent.putExtra("TopicLevel",pref.getString("TopicLevel","Basic"));
        startActivity(intent);
        finish();
    }

    public void UpdateUserRecord() {

//        pd = new ProgressDialog(this);
//        pd.setMessage("Please Wait...");
//        pd.show();
        Log.i("InGetUser","IdDara");

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://innocruts.com/python_app/UpdateUserActivity.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Balance jjIS:",response.toString());
                // parseResult(response.toString());
                //GetData()
                //     readFromFile(HomePage.this);

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //  pd.dismiss();
                //This code is executed if there is an error.
                //     Log.i("ErroInConnection:",error.getMessage());
                // Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                //  Log.i("Error jjIS:",error.getMessage());

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();

                MyData.put("mobile", "13098267486");
                MyData.put("topicid",SelectedTopicId);
                MyData.put("topic_status","1");
                MyData.put("exercise","0");
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }
    public void UpdateUserActivity() {

        pd = new ProgressDialog(this);
        pd.setMessage("Congratlation...");
        pd.show();
        // Log.i("InGetUser",UserEmail);

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://innocruts.com/python_app/getTopicNameActivity.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Balance jjIS:",response.toString());
                // parseResult(response.toString());
                //GetData();
                writeToUserActivityFile(response.toString(),TopicCompletePage.this);
                //readFromFile(HomePage.this);
                pd.dismiss();

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                  pd.dismiss();
                //This code is executed if there is an error.
                //     Log.i("ErroInConnection:",error.getMessage());
                // Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                //  Log.i("Error jjIS:",error.getMessage());

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();

                //MyData.put("balance", String.valueOf(BalanceUpdate));
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

    private void writeToUserActivityFile(String data,Context context) {
//        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("useractivity.txt", Context.MODE_PRIVATE));
//            outputStreamWriter.write("");
//            outputStreamWriter.write(data);
//            outputStreamWriter.close();
//        }
//        catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
        try {
            FileOutputStream fileout=openFileOutput("useractivity.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("");
            outputWriter.write(data);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
