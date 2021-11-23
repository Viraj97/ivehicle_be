package com.ivehicle.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin(maxAge = 3600, origins = "*", exposedHeaders = "**")//CORS policy enabled
public class MainController {

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String query) throws JsonProcessingException {
        String APIKey = "AIzaSyBLM9clTlpDTeXeqcDjdtfUi7SJnl56zU0";
//        String APIKey = "AIzaSyCld0c32ZTwPugwZSIw3fXEANDEuunRJT8";
        String cx = "6d3501a70f5d29c75";
//        String cx = "67de38385b877d7fe";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("https://customsearch.googleapis.com/customsearch/v1?cx=" + cx + "&q=" + query + "&key=" + APIKey, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return ResponseEntity.ok(mapper.readTree(result).get("items"));
    }

}
