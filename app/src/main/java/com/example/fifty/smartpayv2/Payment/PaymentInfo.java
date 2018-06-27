package com.example.fifty.smartpayv2.Payment;

import java.util.Date;

/**
 * Created by Fifty on 5/8/2018.
 */

public class PaymentInfo {
    String companeyName ;
    int CompaneyType ;
    Date paymentDate ;
    double billAmount;

    public String getCompaneyName() {
        return companeyName;
    }

    public void setCompaneyName(String companeyName) {
        this.companeyName = companeyName;
    }

    public int getCompaneyType() {
        return CompaneyType;
    }

    public void setCompaneyType(int companeyType) {
        CompaneyType = companeyType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }
}
