package com.innocruts.python_app;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by Rahul on 1/14/2017.
 */
public class EarningHistoryAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;
    SQLiteDatabase eventsDB;
    RecyclerView recyclerView;
    String Ad;
    int layout=1;


    public EarningHistoryAdapter(Context context, List<FeedItem> feedItemList, RecyclerView rv) {
        this.feedItemList = feedItemList;
        this.mContext = context;
        this.recyclerView=rv;
    }
 //   int conditionn=HomePage.condition;
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       // MobileAds.initialize((mContext), String.valueOf(R.string.Admob_App_Id));




            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_topic_layout, null);
            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;


    }


    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, int i) {
        final FeedItem feedItem = feedItemList.get(i);


        try {
            //Download image using picasso library
//    Picasso.with(mContext).load(feedItem.getCat_Image())
//            .error(R.drawable.placeholder)
//            .placeholder(R.drawable.placeholder)
//            .into(customViewHolder.Cat_Image);


//            if(i==3)
//            {
//                Log.i("In InstallApp;","YES");
//                customViewHolder.adView.setVisibility(View.VISIBLE);
//                AdRequest request = new AdRequest.Builder().build();
//                customViewHolder.adView.loadAd(request);
//
//            }

//            Picasso.with(mContext).load(feedItem.getAppInstallImage())
//            .error(R.drawable.placeholder)
//            .placeholder(R.drawable.placeholder)
//            .into(customViewHolder.AppImage);
            Log.i("TaskName",feedItem.getTopicName());
            customViewHolder.TopicName.setText(Html.fromHtml(feedItem.getTitleName()));
            customViewHolder.TopicExcercise.setText("Excerise "+i);
            if(feedItem.getTopic_Status().compareTo("1")==0)
            {
                customViewHolder.Icon_1.setImageResource(R.drawable.verified_green);
            }
            if(feedItem.getTopicExcercise().compareTo("1")==0)
            {
                customViewHolder.Icon_1.setImageResource(R.drawable.verified);
            }
            customViewHolder.Topic_Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,feedItem.getTitleName(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mContext,MainActivity.class);
                    intent.putExtra("TopicId",feedItem.getHeadingTopicId());
                    intent.putExtra("TopicStatus",feedItem.getTopic_Status());
                    mContext.startActivity(intent);
                }
            });
            customViewHolder.Excerise_Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,feedItem.getTopicExcercise(),Toast.LENGTH_SHORT).show();
                }
            });
          //  customViewHolder.AppInstallBtn.setText(Html.fromHtml(feedItem.getAppInstallPayout()));
            //Log.i("Adv:",Ad);

            //Setting text view title
//            if(feedItem.getAdsSellRent().compareTo("Sell")==0)
//            {
//                customViewHolder.AdsRentSell.setImageResource(R.drawable.sell_tag);
//            }
//            else
//            {
//                customViewHolder.AdsRentSell.setImageResource(R.drawable.rent_tag);
//            }


        }
        catch (Exception e) {
            Log.i("Yes", "Error");
        }



//        customViewHolder.AdsImage.setOnClickListener(clickListener);
////
//        customViewHolder.AdsTitle.setTag(customViewHolder);
//        customViewHolder.AdsImage.setTag(customViewHolder);
//        customViewHolder.AppInstallBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(feedItem.getAppInstallUrl()));
//               mContext.startActivity(browserIntent);
//            }
//        });
    }




//    View.OnClickListener clickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            try {
//                CustomViewHolder holder = (CustomViewHolder) view.getTag();
//                int position = holder.getPosition();
//                Log.i("Click", "Click");
//                FeedItem feedItem = feedItemList.get(position);
//              //  Toast.makeText(mContext, feedItem.getTitle(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext, Sell_Rent_Detail_Activity.class);
//                intent.putExtra("Title", feedItem.getAdsTitle());
//                intent.putExtra("Price", feedItem.getAdsPrice());
//                intent.putExtra("Phone", feedItem.getAdsContact());
//                intent.putExtra("UserName", feedItem.getAdsPersonName());
//                intent.putExtra("Address", feedItem.getAdsAddress());
//                intent.putExtra("Description", feedItem.getAdsDecription());
//                intent.putExtra("Category", feedItem.getAdsCatgeory());
//                intent.putExtra("Image",feedItem.getAdsImage());
//                mContext.startActivity(intent);
//
//            }
//            catch (Exception e)
//            {
//
//            }
//        }
//    };



    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

//    public void filter(String text) {
//        feedItemList = GetPostData.feedsList;
//        if (text.length() > 0) {
//            List<FeedItem> filterd = new ArrayList<FeedItem>();
//            for (int i = 0; i < feedItemList.size(); i++) {
////                if (feedItemList.get(i).getAdsTitle().toLowerCase().startsWith(text.toLowerCase())) {
////                    filterd.add(feedItemList.get(i));
////                }
//                if (feedItemList.get(i).getAdsCatgeory().toLowerCase().startsWith(text.toLowerCase())) {
//                    filterd.add(feedItemList.get(i));
//                }
//            }
//            feedItemList = filterd;
//            filterd = null;
//
//
//        }
//        recyclerView.getAdapter().notifyDataSetChanged();
//    }
}


