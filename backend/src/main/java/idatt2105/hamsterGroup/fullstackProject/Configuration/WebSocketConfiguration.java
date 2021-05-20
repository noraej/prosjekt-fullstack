package idatt2105.hamsterGroup.fullstackProject.Configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration for WebSockets, used for chat features in the application
 */
@Configuration
@Profile("!test")
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * Method that configures MessageBroker to our chat endpoints
     * Setting up broker is important for secure and reliable messaging.
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/api/v1/chat");
        config.setApplicationDestinationPrefixes("/api/v1/chat");
    }

    /**
     * Integrates support for Stomp (Simple Text Orientated Messaging Protocol)
     * One of the main components of Spring messaging framework
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/v1/websocket").setAllowedOrigins("http://localhost:3000").withSockJS();

    }
}
