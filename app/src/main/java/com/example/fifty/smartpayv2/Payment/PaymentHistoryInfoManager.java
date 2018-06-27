package com.example.fifty.smartpayv2.Payment;

import android.content.Context;

import com.example.fifty.smartpayv2.DBA.DBA;

import java.util.ArrayList;

/**
 * Created by Fifty on 5/8/2018.
 */

public class PaymentHistoryInfoManager {
    private static PaymentHistoryInfoManager paymentInfoManager;
    private ArrayList<PaymentInfo> paymentInfosList ;
    public PaymentHistoryInfoManager(Context context){
        DBA dba = new DBA();
        ArrayList paymentHistoriyList = dba.getPaymentHistoriyList();
        paymentInfoManager = this;
        this.paymentInfosList = paymentHistoriyList ;

    }
    public ArrayList<PaymentInfo> getPaymentInfosList(){
        return paymentInfosList;
    }

    public static PaymentHistoryInfoManager getPaymentInfoManager(Context context){
        if(paymentInfoManager==null){
            paymentInfoManager = new PaymentHistoryInfoManager(context);
        }

        return paymentInfoManager;
    }

}
