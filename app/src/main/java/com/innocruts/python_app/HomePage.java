package com.innocruts.python_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

       // GetTopicList();
        pref=getApplicationContext().getSharedPreferences("python_app",MODE_PRIVATE);
        editor=pref.edit();
        GetUserBalanceVolly();
        GetDataInformationVolly();
//        Intent intent=new Intent(this,Topic_Activity.class);
//        startActivity(intent);
    }

   public void BasicTopics(View view)
   {
       Intent intent=new Intent(this,Topic_Activity.class);
       intent.putExtra("TopicLevel","Basic");
       editor.putString("TopicLevel","Basic");
       editor.commit();
       startActivity(intent);
   }

    public void GetUserBalanceVolly() {

//        pd = new ProgressDialog(this);
//        pd.setMessage("Please Wait...");
//        pd.show();
        // Log.i("InGetUser",UserEmail);

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://innocruts.com/python_app/getTopicNameActivity.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Balance jjIS:",response.toString());
                // parseResult(response.toString());
                //GetData();
                writeToUserActivityFile(response.toString(),HomePage.this);
                //readFromFile(HomePage.this);

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


    public void GetDataInformationVolly() {

//        pd = new ProgressDialog(this);
//        pd.setMessage("Please Wait...");
//        pd.show();
        Log.i("InGetUser","IdDara");

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://innocruts.com/python_app/getInformation.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Balance jjIS:",response.toString());
                // parseResult(response.toString());
                //GetData();
                writeToDataFile(response.toString(),HomePage.this);
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

                //MyData.put("balance", String.valueOf(BalanceUpdate));
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

    private void writeToDataFile(String data,Context context) {
//        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("data.txt", Context.MODE_PRIVATE));
//            outputStreamWriter.write("");
//            outputStreamWriter.write(data);
//            outputStreamWriter.close();
//        }
//        catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }

        try {
            FileOutputStream fileout=openFileOutput("data.txt", MODE_PRIVATE);
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


    //NOt in Use
    public void GetTopicList() {

//        pd = new ProgressDialog(this);
//        pd.setMessage("Please Wait...");
//        pd.show();
        // Log.i("InGetUser",UserEmail);

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://innocruts.com/python_app/getTopicNameActivity.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Balance jjIS:",response.toString());
                // parseResult(response.toString());
                //GetData();
                writeToFileActivityFile(response.toString(),HomePage.this);
                //readFromFile(HomePage.this);

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

                //MyData.put("balance", String.valueOf(BalanceUpdate));
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }
    private void writeToFileActivityFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("topiclist.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("");
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Intent intent=new Intent(this,Topic_Activity.class);
            startActivity(intent);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
