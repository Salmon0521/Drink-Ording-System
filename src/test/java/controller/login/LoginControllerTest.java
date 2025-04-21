package controller.login;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void test_showLoginPage() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/Login";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }
}