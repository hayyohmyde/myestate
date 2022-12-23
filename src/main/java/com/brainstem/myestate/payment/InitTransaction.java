package com.brainstem.myestate.payment;

import com.brainstem.myestate.exception.NotAuthorizedException;
import com.brainstem.myestate.payment.request.InitTransactionRequest;
import com.brainstem.myestate.payment.response.InitTransactionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InitTransaction {

    @Value("${PAYSTACK_STATUS_CODE_OK}")
    int STATUS_CODE_OK;

    public InitTransactionResponse initTransaction(InitTransactionRequest request) throws Exception {
        InitTransactionResponse initTransactionResponse = null;
        try {
            // convert transaction to json then use it as a body to post json
            Gson gson = new Gson();
            // add paystack charges to the amount
            StringEntity postingString = new StringEntity(gson.toJson(request));

            HttpClient client = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost("https://api.paystack.co/transaction/initialize");
            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer sk_test_81249bb14d3ce4302cebd26b9f308cfd970cdee7");

            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new NotAuthorizedException("Error Occurred while initializing transaction");
            }
            ObjectMapper mapper = new ObjectMapper();

            initTransactionResponse = mapper.readValue(result.toString(), InitTransactionResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failure initializing Paystack transaction");
        }

        return initTransactionResponse;
    }
}
