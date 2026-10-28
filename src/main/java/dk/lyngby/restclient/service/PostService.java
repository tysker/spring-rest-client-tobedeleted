package dk.lyngby.restclient.service;

import dk.lyngby.restclient.aop.LogExecutionTime;
import dk.lyngby.restclient.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
public class PostService {

    private final RestClient restClient;

    public PostService(RestClient restClient) {
        this.restClient = restClient;
    }

    @LogExecutionTime
    public List<Post> getData(String args1, String args2) {
        try {
            Post[] postsArray =  restClient.get()
                    .uri("/posts")
                    .retrieve()
                    .body(Post[].class);
            return  postsArray != null ? List.of(postsArray) : List.of();
        } catch (Exception e) {
            log.error("[LOGGING] -> Something went wrong: {}", e.getMessage());
            throw new RuntimeException("Something went wrong: " + e.getMessage());
        }
    }
}
