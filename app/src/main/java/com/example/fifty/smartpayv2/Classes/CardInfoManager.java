package com.example.fifty.smartpayv2.Classes;

import android.content.Context;

import com.example.fifty.smartpayv2.DBA.LocalDBA;

import java.util.ArrayList;

/**
 * Created by Fifty on 5/8/2018.
 */

public class CardInfoManager {
    private static CardInfoManager cardInfoManager;
    private ArrayList<Card> cardInfoList ;
    public CardInfoManager(Context context){
        LocalDBA localDBA = new LocalDBA(context);
        ArrayList cardsList = localDBA.getAllCards();
        cardInfoManager = this;
        this.cardInfoList = cardsList ;

    }
    public ArrayList<Card> getCardsInfoList(){
        return cardInfoList;
    }

    public static CardInfoManager getCardsInfoManager(Context context){
        if(cardInfoManager==null){
            cardInfoManager = new CardInfoManager(context);
        }

        return cardInfoManager;
    }

}
