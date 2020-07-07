package huntertran.corsproxy.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {
    private static HttpClient client = HttpClient.newHttpClient();
    private static String apiPrefix = "https://opendata.concordia.ca/API/v1";

    @GetMapping(path = "/open-concordia", produces = { "application/json" })
    public String openConcordia(@RequestParam(value = "api") String api,
                                @RequestHeader("Authorization") String authorization) {
        HttpResponse<String> response = null;

        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(apiPrefix + api.replace(" ", "%20")))
                                         .header("Authorization", authorization)
                                         .build();
        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response.body();
    }
}