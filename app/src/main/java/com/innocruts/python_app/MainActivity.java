package com.innocruts.python_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import github.nisrulz.stackedhorizontalprogressbar.StackedHorizontalProgressBar;

public class MainActivity extends AppCompatActivity {

    private List<FeedItem> feedsList;
    private List<FeedItem> feedItemList;
    ArrayList<String> Title;
    ArrayList<String> Description;
    ArrayList<String> Code;
    ArrayList<String> Output;
    ArrayList<String> ButtonArrey;
    static int DataFlag=0;
    TextView TitleText,DescriptionText,CodeText,OutputText;
    Button ButtonText;
    boolean OutputFlag=false;

    String SelectedTopicId;
    String TopicIdSel;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String TopicStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        feedsList = new ArrayList();
        Title=new ArrayList<>();
        Description=new ArrayList<>();
        Code=new ArrayList<>();
        Output=new ArrayList<>();
        ButtonArrey=new ArrayList<>();
        getSupportActionBar().hide();

        Intent intent=getIntent();
        SelectedTopicId=intent.getStringExtra("TopicId");
        TopicIdSel=SelectedTopicId;
        pref=getApplicationContext().getSharedPreferences("python_app",MODE_PRIVATE);
        editor=pref.edit();

        Log.i("Heading Id=",SelectedTopicId);
        TopicStatus=intent.getStringExtra("TopicStatus");
        TitleText=(TextView) findViewById(R.id.title);
        DescriptionText=(TextView) findViewById(R.id.description);
        CodeText=(TextView) findViewById(R.id.code);
        OutputText=(TextView) findViewById(R.id.output);
        ButtonText=(Button) findViewById(R.id.buttonNext);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/codefont.ttf");
        Typeface Infoface = Typeface.createFromAsset(getAssets(),
                "fonts/infotext.ttf");
        CodeText.setTypeface(face);
        OutputText.setTypeface(face);
        TitleText.setTypeface(Infoface);
        DescriptionText.setTypeface(Infoface);
        DataFlag=0;


      //  stackedHorizontalProgressBar.setSecondaryProgress(secondary_pts);

        readFromFile(this);
        GetData(0,OutputFlag);



    }

    public void SetProgressBar(int DataFlag)
    {
        int primary_pts = DataFlag+1;
        int secondary_pts = 6;
        int max = Title.size();

        StackedHorizontalProgressBar stackedHorizontalProgressBar;
        stackedHorizontalProgressBar = (StackedHorizontalProgressBar) findViewById(R.id.stackedhorizontalprogressbar);
        stackedHorizontalProgressBar.setMax(max);
        stackedHorizontalProgressBar.setSecondaryProgress(primary_pts);

    }

//    public void GetUserBalanceVolly() {
//
////        pd = new ProgressDialog(this);
////        pd.setMessage("Please Wait...");
////        pd.show();
//        // Log.i("InGetUser",UserEmail);
//
//        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
//        String url = "http://innocruts.com/python_app/getInformation.php";
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.i("Balance jjIS:",response.toString());
//               // parseResult(response.toString());
//                //GetData();
//                writeToFile(response.toString(),MainActivity.this);
//                readFromFile(MainActivity.this);
//
//            }
//        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //  pd.dismiss();
//                //This code is executed if there is an error.
//                //     Log.i("ErroInConnection:",error.getMessage());
//                // Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
//                //  Log.i("Error jjIS:",error.getMessage());
//
//            }
//        }) {
//            protected Map<String, String> getParams() {
//                Map<String, String> MyData = new HashMap<String, String>();
//
//                //MyData.put("balance", String.valueOf(BalanceUpdate));
//                return MyData;
//            }
//        };
//        MyRequestQueue.add(MyStringRequest);
//    }
    private void parseResult(String result) {

        try {
            //   Log.i("InParseIn", "Yes");

            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("result");



            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                FeedItem item = new FeedItem();
//                item.setTitle(post.optString("title"));
//                item.setDescription(post.optString("description"));
//                item.setCode(post.optString("code"));
//                item.setButton(post.optString("button"));
//                item.setOutput(post.optString("output"));
                Log.i("NewHeadingId",post.optString("heading_id"));
                if(SelectedTopicId.compareTo(post.optString("heading_id"))==0)
                {
                    Title.add(post.optString("title"));
                    Description.add(post.optString("description"));
                    Code.add(post.optString("code"));
                    Output.add(post.optString("output"));
                    ButtonArrey.add(post.optString("button"));

                    feedsList.add(item);
                }
                Log.i("Title:", post.optString("title"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void GetData(int i,boolean OutputFlag)
    {
//        Log.i("SIZE Is:", String.valueOf(feedsList.size()));
//        int SizeIs=feedsList.size();

        if(OutputFlag==true)
        {
            OutputText.setVisibility(View.VISIBLE);
            OutputText.setText(Output.get(i));
        }
        else {
            OutputText.setVisibility(View.GONE);
            Log.i("Title Is:", Title.get(i));
            Log.i("Description Is:", Description.get(i));
            TitleText.setText(Title.get(i));
            DescriptionText.setText(Description.get(i));
            CodeText.setText(Code.get(i));
            ButtonText.setText(ButtonArrey.get(i));
        }


    }
    public void NextInfo(View view)
    {
        Log.i("DataFlag "+DataFlag+"--", String.valueOf(Title.size()));
        if(ButtonText.getText().toString().compareTo("EXIT")==0)
        {
            Intent intent=new Intent(getApplicationContext(),TopicCompletePage.class);
            Log.i("TopicIDDD:",TopicIdSel);
            intent.putExtra("TopicId",TopicIdSel);
            intent.putExtra("TopicStatus",TopicStatus);
            startActivity(intent);
        }
        if(Title.size()==DataFlag+1&&ButtonText.getText().toString().compareTo("RUN")!=0)
        {
            ButtonText.setText("EXIT");
            DataFlag=0;
        }
        else {
            SetProgressBar(DataFlag);
            if (OutputFlag == false) {
                OutputFlag = true;
                GetData(DataFlag, OutputFlag);
                ButtonText.setText("Continue");
            } else {
                DataFlag++;
                OutputFlag = false;
                GetData(DataFlag, OutputFlag);
            }
        }


    }



    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("data.txt");

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
                parseResult(ret);
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


    private void writeToUserActivityFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("useractivity.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("");
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("data.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("");
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
