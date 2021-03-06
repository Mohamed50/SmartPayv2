package com.example.fifty.smartpayv2.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fifty.smartpayv2.ClassesManagers.PaymentHistoryInfoManager;
import com.example.fifty.smartpayv2.Classes.PaymentInfo;
import com.example.fifty.smartpayv2.DBA.Configuration;
import com.example.fifty.smartpayv2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fifty on 5/6/2018.
 */

public class HistoryFragment extends Fragment {
    View myView;
    PaymentHistoryAdapter adapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.history_fragment,container,false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.history_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        UpdateUI();
        return myView;

    }

    public class PaymentHistoryAdapter extends Adapter<PaymentHistoryAdapter.PayHolder>{
        private List<PaymentInfo> paymentList;
        public class PayHolder extends RecyclerView.ViewHolder {
            TextView companeyNameTextView;
            TextView billAmountTextView;
            TextView paymentDateTextView;
            TextView paymentTimeTextView;
            ImageView companeyIcon;
            PaymentInfo paymentObj;

            public PayHolder(View itemView) {
                super(itemView);
                companeyNameTextView = (TextView) itemView.findViewById(R.id.company_name);
                billAmountTextView = (TextView) itemView.findViewById(R.id.bill_amount);
                paymentTimeTextView = (TextView) itemView.findViewById(R.id.payment_time);
                paymentDateTextView = (TextView) itemView.findViewById(R.id.payment_date);
                companeyIcon = (ImageView) itemView.findViewById(R.id.company_icon);
            }

        }

        public PaymentHistoryAdapter(List<PaymentInfo> list) {
            this.paymentList = list;
        }

        @Override
        public PayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getActivity().getBaseContext()).inflate(R.layout.payment_info_card, parent, false);
            return new PayHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PayHolder holder, int position) {

            holder.paymentObj = paymentList.get(position);
            holder.companeyNameTextView.setText(holder.paymentObj.getCompaneyName());
            holder.paymentDateTextView.setText(holder.paymentObj.getStringDate());
            holder.paymentTimeTextView.setText(holder.paymentObj.getStringTime());
            holder.billAmountTextView.setText(String.valueOf(holder.paymentObj.getBillAmount())+" SDG");
            if(holder.paymentObj.getCompaneyType()==1)
                holder.companeyIcon.setImageResource(R.mipmap.coffee_icon);

        }

        @Override
        public int getItemCount() {
            return this.paymentList.size();
        }

    }
    public void UpdateUI(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Configuration.MY_PREFERENCE, Context.MODE_PRIVATE);
        ArrayList<PaymentInfo> paymentInfoArrayList = PaymentHistoryInfoManager.getPaymentInfoManager(getActivity().getBaseContext(),sharedPreferences).getPaymentInfosList();
        if (this.adapter == null){
            PaymentHistoryAdapter cAdapter = new PaymentHistoryAdapter(paymentInfoArrayList);
            adapter = cAdapter;
            this.recyclerView.setAdapter(adapter);
            return;
        }
        this.adapter.notifyDataSetChanged();
    }

}
