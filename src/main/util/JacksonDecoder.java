package main.util;

import main.model.Post;
import org.atmosphere.config.managed.Decoder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JacksonDecoder implements Decoder<String, Post> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Post decode(String s) {
        try {
            return mapper.readValue(s, Post.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
