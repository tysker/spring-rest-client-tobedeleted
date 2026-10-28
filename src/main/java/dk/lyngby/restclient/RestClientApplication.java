package dk.lyngby.restclient;

import dk.lyngby.restclient.aop.LogExecutionTime;
import dk.lyngby.restclient.model.Post;
import dk.lyngby.restclient.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class RestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(PostService postService) {
        return args -> {
            System.out.println("--- Fetching data from external API ---");

            List<Post> data = postService.getData("Hallo ", "World!");
            System.out.println("Received Data: " + data.get(0).getTitle());

            System.out.println("--- Finished fetching data ---");
        }; }
    }


