package com.innocruts.python_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Topic_Activity extends AppCompatActivity {

    private List<FeedItem> feedsList;
    private EarningHistoryAdapter adapter;
    private RecyclerView mRecyclerView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String UserMobileNumber;
    static final int READ_BLOCK_SIZE = 100;
    String TopicLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_);

        getSupportActionBar().hide();
        readFromUserActivityFile(this);
        pref=getApplicationContext().getSharedPreferences("python_app",MODE_PRIVATE);
        editor=pref.edit();
        UserMobileNumber=pref.getString("Email","");
        Intent intent=getIntent();
        TopicLevel=intent.getStringExtra("TopicLevel");
       // Log.i("TopicLevel",TopicLevel);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new EarningHistoryAdapter(Topic_Activity.this, feedsList, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

    private String readFromUserActivityFile(Context context) {

        String ret = "";
            try {
                FileInputStream fileIn = openFileInput("useractivity.txt");
                InputStreamReader InputRead = new InputStreamReader(fileIn);

                char[] inputBuffer = new char[READ_BLOCK_SIZE];
               // String ret = "";
                int charRead;

                while ((charRead = InputRead.read(inputBuffer)) > 0) {
                    // char to string conversion
                    String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    ret += readstring;
                    parseUserActivityResult(ret);
                }
                InputRead.close();
             //   Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }



        return ret;
    }
    private void parseUserActivityResult(String result) {
        try {
            //   Log.i("InParseIn", "Yes");

            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("result");

            feedsList = new ArrayList();

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                FeedItem item = new FeedItem();
                if(post.optString("topiclevel").compareTo("Basic")==0) {
                    item.setTitleName(post.optString("title"));
                    item.setHeadingTopicId(post.optString("id"));
                    item.setTopicName(post.optString("topic"));
                    item.setTopic_Status(post.optString("topic_status"));
                    item.setTopicExcercise(post.optString("exercise"));

//                item.setOutput(post.optString("output"));


                    feedsList.add(item);
                }
                Log.i("Title:", post.optString("title"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

























    private String readFromUserFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("useractivity.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                parseUserResult(ret);
                Log.i("Result is:",ret);
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
    private void parseUserResult(String result) {

        try {
            //   Log.i("InParseIn", "Yes");

            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("result");

            feedsList = new ArrayList();

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                FeedItem item = new FeedItem();
                item.setTopicName(post.optString("topic"));
                item.setTopicExcercise(post.optString("exercise"));

//                item.setOutput(post.optString("output"));


                feedsList.add(item);
                Log.i("Title:", post.optString("title"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
