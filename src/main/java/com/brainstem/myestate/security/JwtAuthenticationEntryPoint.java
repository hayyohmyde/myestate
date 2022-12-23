package com.brainstem.myestate.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component // make it a spring component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint { //JWT step 1
    //commence method is called whenever an exception is thrown when unauthorised user is accessing
    //resource that requires authentication
    @Override   //- JWT step 2
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Request is unauthorized");
    }
}
