package mx.edu.cetys.iso.kungfupanda.service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;

class OpenAIServiceTest {

    @InjectMocks
    private OpenAIService openAIService;

    @Test
    void testGetResponseSuccess() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        openAIService = new OpenAIService();

        String prompt = "Kung Fu";
        String mockResponseBody = "{ \"choices\": [{\"message\": {\"content\": \"La leyenda del Kung Fu es eterna.\"}}]}";
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<String> mockResponse = new ResponseEntity<>(mockResponseBody, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class))).thenReturn(mockResponse);

        String result = openAIService.getResponse(prompt);
        assertEquals("La leyenda del Kung Fu es eterna.", result);
    }

    @Test
    void testGetResponseError() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        openAIService = new OpenAIService();

        String prompt = "Error";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class))).thenThrow(new RuntimeException("API Error"));

        String result = openAIService.getResponse(prompt);
        assertEquals("Error al obtener respuesta de ChatGPT", result);
    }
}
