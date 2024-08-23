package in.g77tech.sb_jwt_app.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.g77tech.sb_jwt_app.services.JwtService;
import in.g77tech.sb_jwt_app.services.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailsService userDtlsSvc;
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        String username = null;

        if (token!=null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            username = jwtService.extractUsername(token);
        }
        
        // update security context
        if (username != null && 
                SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDtlsSvc.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        
        filterChain.doFilter(request, response);

    }


}