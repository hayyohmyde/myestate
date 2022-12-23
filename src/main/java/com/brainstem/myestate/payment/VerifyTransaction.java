package com.brainstem.myestate.payment;

import com.brainstem.myestate.payment.response.VerifyTransactionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VerifyTransaction {

    @Value("${PAYSTACK_SECRET_KEY}")
    String PAYSTACK_SECRET_KEY;

    @Value("${PAYSTACK_STATUS_CODE_OK}")
    int STATUS_CODE_OK;


    public VerifyTransactionResponse verifyTransaction(String reference) throws Exception {
        VerifyTransactionResponse paystackresponse = null;
        try {
//            HttpClient client = HttpClientBuilder.create().build();
            HttpClient client = HttpClientBuilder.create().build();

            HttpGet request = new HttpGet("https://api.paystack.co/transaction/verify/" + reference);
            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + PAYSTACK_SECRET_KEY);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new Exception("Error Occurred while connecting to Paystack URL");
            }
            ObjectMapper mapper = new ObjectMapper();

            paystackresponse = mapper.readValue(result.toString(), VerifyTransactionResponse.class);

            if (paystackresponse == null || paystackresponse.getStatus().equals("false")) {
                throw new Exception("An error occurred while  verifying payment");
            } else if (paystackresponse.getData().getStatus().equals("success")) {
                //PAYMENT IS SUCCESSFUL, APPLY VALUE TO THE TRANSACTION
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Internal server error");
        }
        return paystackresponse;
    }
}


