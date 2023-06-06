package com.revature.katieskritters;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class api {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fish-species.p.rapidapi.com/fish_api/fish/Tarpon"))
                .header("X-RapidAPI-Key", "f6d27034b0msh232a845713d021ep1f797ajsne6d51817055f")
                .header("X-RapidAPI-Host", "fish-species.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}