package dev.imy.kimaradi.config;

import com.sun.security.auth.UserPrincipal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Slf4j
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    @SneakyThrows
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        final String query = request.getURI().getQuery();
        
        final MultiValueMap<String, String> queryParams = UriComponentsBuilder.newInstance().query(query).build().getQueryParams();
        
        if(!queryParams.containsKey("rootNumber")){
            throw new IllegalAccessException();
        }

        final String userId = queryParams.get("rootNumber").get(0);
        
        return new UserPrincipal(userId);
    }
}
