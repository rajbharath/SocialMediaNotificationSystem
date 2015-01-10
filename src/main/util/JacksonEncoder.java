package main.util;

import main.model.Post;
import org.atmosphere.config.managed.Encoder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JacksonEncoder implements Encoder<Post, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(Post m) {
        try {
            return mapper.writeValueAsString(m);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
