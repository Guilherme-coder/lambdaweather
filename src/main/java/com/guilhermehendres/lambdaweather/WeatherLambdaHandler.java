package com.guilhermehendres.lambdaweather;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;

public class WeatherLambdaHandler implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    private static final String API_KEY = System.getenv("WEATHER_API_KEY");
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
        try {
            String city = (String) input.get("city");

            if (city == null || city.isEmpty()) {
                return createResponse(400, "city parameter is required.");
            }

            String url = String.format("%s?q=%s&appid=%s&units=metric&lang=pt_br", API_URL, city, API_KEY);

            Request request = new Request.Builder().url(url).build();
            Response response = httpClient.newCall(request).execute();

            if (!response.isSuccessful()) {
                return createResponse(500, "Error getting data from the time API.");
            }

            Map<String, Object> weatherData = mapper.readValue(response.body().string(), Map.class);
            return createResponse(200, weatherData);

        } catch (Exception e) {
            return createResponse(500, "Error: " + e.getMessage());
        }
    }

    private Map<String, Object> createResponse(int statusCode, Object body) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", statusCode);
        response.put("body", toJson(body));
        response.put("headers", Map.of("Content-Type", "application/json"));
        return response;
    }

    private String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "{}";
        }
    }
}
