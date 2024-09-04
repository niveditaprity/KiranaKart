package com.ecommerce.KiranaKart.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
@Service
public class PaymentService {
    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    private RestTemplate restTemplate = new RestTemplate();

    public String createOrder(double amount) {
            String url = "https://api.razorpay.com/v1/orders";

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100); // amount in the smallest currency unit (e.g., paise for INR)
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_11");
            orderRequest.put("payment_capture", true);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + encodeCredentials(keyId, keySecret));
            headers.set("Content-Type", "application/json");
            HttpEntity<String> requestEntity = new HttpEntity<>(orderRequest.toString(), headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return "Error: " + response.getStatusCode();
            }
    }

    private String encodeCredentials(String keyId, String keySecret) {
        String credentials = keyId + ":" + keySecret;
        return java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
