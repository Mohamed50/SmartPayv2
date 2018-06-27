package com.example.fifty.smartpayv2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fifty.smartpayv2.DBA.DBA;
import com.example.fifty.smartpayv2.DBA.LocalDBA;
import com.example.fifty.smartpayv2.Payment.Card;
import com.example.fifty.smartpayv2.Payment.CardInfoManager;
import com.example.fifty.smartpayv2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Fifty on 4/15/2018.
 */

public class SlidePagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    LocalDBA localDBA;
    ArrayList<Card> cardArrayList;
    public SlidePagerAdapter(Context context){
        this.context = context;
        localDBA = new LocalDBA(context);
        cardArrayList = localDBA.getAllCards();
    }



    public int [] slideImage= {
            R.drawable.fispngsmall,
            R.drawable.omb,
            R.drawable.bok
    };

    @Override
    public int getCount() {
        return cardArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideIcon = (ImageView) view.findViewById(R.id.slider_latout_card_image);
        TextView cardBalance = (TextView) view .findViewById(R.id.card_balance);
        slideIcon.setImageResource(slideImage[cardArrayList.get(position).getCardIcon()]);
        cardBalance.setText(String.valueOf(cardArrayList.get(position).getCardBalance()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
