package easyqr.unir.easyqr.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestInterceptorAppConfig implements WebMvcConfigurer {

    @Value("${token}")
    public String token;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor(token))
                .excludePathPatterns("/v3/api-docs/**", "/swagger-ui/*", "/documentation.html");
    }
}
