package com.user.service.configration.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class feignClientInterceptor implements RequestInterceptor {
    @Autowired
    private OAuth2AuthorizedClientManager manager;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenType().getValue();
        System.out.println("Feign Client:-"+token);
        requestTemplate.header("Authorization","Bearer "+token);


    }
}
