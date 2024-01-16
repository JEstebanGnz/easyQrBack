package easyqr.unir.easyqr.Security;

import easyqr.unir.easyqr.Exceptions.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
    private final String token;
    public RequestInterceptor(String token) {
        this.token = token;
    }
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tokenHeader = request.getHeader("token");
        if (tokenHeader!= null && tokenHeader.equals(token)){
            return true;
        }
        throw new InvalidTokenException("Unauthorized, please check your token");
    }
}
