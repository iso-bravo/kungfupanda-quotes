package mx.edu.cetys.iso.kungfupanda.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    @Value("${api.key}")
    private String apiKey;

    public String getResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String body = """
        {
            "model": "gpt-3.5-turbo",
            "messages": [{"role": "user", "content": "%s"}],
            "max_tokens": 100
        }
        """.formatted(prompt);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

            System.out.println("API Response: " + response.getBody());

            JSONObject jsonResponse = new JSONObject(response.getBody());
            JSONArray choices = jsonResponse.getJSONArray("choices");

            String chatGPTResponse = choices.getJSONObject(0).getJSONObject("message").getString("content");
            System.out.println("ChatGPT Response: " + chatGPTResponse);

            return chatGPTResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al obtener respuesta de ChatGPT";
        }
    }
}
