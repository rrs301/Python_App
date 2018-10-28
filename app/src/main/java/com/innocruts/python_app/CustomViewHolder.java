package com.innocruts.python_app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;
//import com.google.android.gms.ads.NativeExpressAdView;
//import com.ramotion.foldingcell.FoldingCell;


/**
 * Created by Rahul on 2/4/2016.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    protected ImageView Icon_1,Icon_2,Team2Image,InspiImage,GameImage1,AppImage,PlayerImage,AdsRentSell,NewsImage,PostPremium,EventImage,Share;
    protected TextView TopicName,TopicExcercise,HeadingTopicId,TaskAmount,Bid,MatchDate,PlayerName,SR,AVG,PlanSelect,NewsTitle,AppInstallPay,OfferTitle,AppInstallText,AppInstallUrl,AppInstallPayout,AppInstallDescription,PostDecription;
    protected Button AppInstallBtn,PlayBidButton,NewsBtn;
    protected RelativeLayout Excerise_Layout,Topic_Layout;

    public CustomViewHolder(View view) {
        super(view);

        ;

        //--------------------------------Player List--------------------------------


        this.TopicName=(TextView) view.findViewById(R.id.TopicName) ;
        this.TopicExcercise=(TextView) view.findViewById(R.id.TopicExcercise);

        this.Icon_1=(ImageView) view.findViewById(R.id.i_1);
        this.Icon_2=(ImageView) view.findViewById(R.id.i_2);
        this.Excerise_Layout=(RelativeLayout) view.findViewById(R.id.excerise_layout);
        this.Topic_Layout=(RelativeLayout) view.findViewById(R.id.topic_layout);

        //  adView = (NativeExpressAdView)view.findViewById(R.id.adView);
////        // this.AdsRentSell=(Ima)view.findViewById(R.id.rent_sell_tag);
//        this.PostDecription=(TextView)view.findViewById(R.id.postDescription);
//        this.StartDate=(TextView)view.findViewById(R.id.StartDate);
////       // this.BTN=(Button)view.findViewById(R.id.BTN);
//        this.EndDate=(TextView)view.findViewById(R.id.EndDate);

    }
}