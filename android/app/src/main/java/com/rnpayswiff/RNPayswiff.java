package com.payswiffintegration; // replace com.your-app-name with your app’s name
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.Map;
import java.util.HashMap;
import com.ps.epay.interfaces.IListener;
import com.ps.epay.model.MerchantIntegrationOrderRequest;
import com.ps.epay.model.MerchantIntegrationPaymentResponse;
import com.ps.epay.model.MerchantIntegrationPaymentStatusResponse;
import com.ps.epay.transaction.TransactionInitialization;
import com.ps.epay.model.MerchantIntegrationPaymentStatusRequest;
import com.ps.epay.model.MerchantIntegrationPaymentStatusResponse;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import android.content.Intent;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;


public class RNPayswiff extends ReactContextBaseJavaModule {

    ReactContext reactContext;
   RNPayswiff(ReactApplicationContext context) {
       super(context);
   }

    

    @Override
    public String getName() {
        return "RNPayswiff";
    }

    
    private void sendEvent(ReactContext reactContext,String eventName,String response){
        WritableMap params = Arguments.createMap();
        params.putString("eventName",eventName);
        params.putString("response",response);
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("RNPayswiffResponseEvent",params);
    }

    @ReactMethod
    public void startTransaction( 
       String description,
       String amount,
       String customerMobileNumber, 
       String paymentOrderNo, 
       int merchandId,
       String billingContactName,
       String responseUrl,
       String billingCity,
       String billingState,
       String billingPostalCode,
       String billingCountry,
       String billingEmail,
        String secretKey) {
        final Activity activity = getCurrentActivity();
        MerchantIntegrationOrderRequest vo = new MerchantIntegrationOrderRequest();
                vo.setDescription(description);
                vo.setAmount(amount);
                vo.setCustomerMobileNumber(customerMobileNumber);
                vo.setPaymentMode(1);
                vo.setMerchantRefNo(paymentOrderNo);
                vo.setMerchantId(merchandId);
                vo.setBillingContactName(billingContactName);
                vo.setResponseUrl(responseUrl);
                vo.setBillingCity(billingCity);
                vo.setBillingState(billingState);
                vo.setBillingPostalCode(billingPostalCode);
                vo.setBillingCountry(billingCountry);
                vo.setBillingEmail(billingEmail);
                TransactionListener transactionListener = new TransactionListener();
                TransactionInitialization pay = new TransactionInitialization(activity);
                pay.startTransaction(vo,secretKey, transactionListener);           
    }

    @ReactMethod
    public void getTransactionDetails( 
       String paymentOrderNo, 
       int merchandId,
        String secretKey) {
        final Activity activity = getCurrentActivity();
        TransactionInitialization pay = new TransactionInitialization(activity); 
        MerchantIntegrationPaymentStatusRequest vo=new MerchantIntegrationPaymentStatusRequest(); 
        vo.setMerchantId(merchandId);
        vo.setMerchantRefNo(paymentOrderNo); 
        DetailsListener details = new DetailsListener(); 
        pay.fetchTransactionDetails(vo,secretKey, details);        
    }

    @ReactMethod
    public void getTransactionStatus( 
       String paymentOrderNo, 
       int merchandId,
        String secretKey) {
        final Activity activity = getCurrentActivity();
        MerchantIntegrationPaymentStatusRequest statusRequest=new MerchantIntegrationPaymentStatusRequest(); 
        statusRequest.setMerchantId(merchandId); 
        statusRequest.setMerchantRefNo(paymentOrderNo);
        StatusListener statusListener=new StatusListener();
        TransactionInitialization initialization=new TransactionInitialization(activity); 
        initialization.initiateTransactionStatus(statusRequest,secretKey,statusListener);       
    }

    class TransactionListener implements IListener { 
        final Activity activity = getCurrentActivity();
        @Override
        public void onSuccess(@NotNull Object x) {
        MerchantIntegrationPaymentStatusResponse vo=(MerchantIntegrationPaymentStatusResponse) x;
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnSuccess",vo.toString()); 
        }

        @Override
        public void onFail(@NotNull Object x) { 
        MerchantIntegrationPaymentStatusResponse vo=(MerchantIntegrationPaymentStatusResponse) x;
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnFail",vo.toString());
        }

        @Override
        public void onError(@NotNull String x) {
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnError",x.toString());
        }
    }

    class DetailsListener implements IListener { 
        final Activity activity = getCurrentActivity();
        @Override
        public void onSuccess(@NotNull Object x) {
        MerchantIntegrationPaymentResponse vo=(MerchantIntegrationPaymentResponse) x;
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnSuccess",vo.toString()); 
        }

        @Override
        public void onFail(@NotNull Object x) { 
        MerchantIntegrationPaymentResponse vo=(MerchantIntegrationPaymentResponse) x;
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnFail",vo.toString());
        }

        @Override
        public void onError(@NotNull String x) {
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnError",x.toString());
        }
    }

    class StatusListener implements IListener { 
        final Activity activity = getCurrentActivity();
        @Override
        public void onSuccess(@NotNull Object x) {
        MerchantIntegrationPaymentStatusResponse vo=(MerchantIntegrationPaymentStatusResponse) x;
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnSuccess",vo.toString()); 
        }

        @Override
        public void onFail(@NotNull Object x) { 
        MerchantIntegrationPaymentStatusResponse vo=(MerchantIntegrationPaymentStatusResponse) x;
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnFail",vo.toString());
        }

        @Override
        public void onError(@NotNull String x) {
        sendEvent(getReactApplicationContext(),"RNPayswiff::OnError",x.toString());
        }
    }
}


