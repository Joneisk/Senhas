package sicoob.credivale.senhas.Security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        // if(token!=null && JWTTokenProvider.verifyToken(token)) // Comente esta linha para desabilitar a verificação do token
        chain.doFilter(request, response); // Comente esta linha também
        // else
        //     ((HttpServletResponse)response).setStatus(500);
        //     response.getOutputStream().write("Não autorizado ".getBytes());
    }
}