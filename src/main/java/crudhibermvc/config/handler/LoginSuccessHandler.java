package crudhibermvc.config.handler;

import crudhibermvc.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Pavel Tokarev, 19.05.2020
 */

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        redirectStrategy.sendRedirect(request, response, determineTargetUrl(request, authentication));
    }

    protected String determineTargetUrl(HttpServletRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Long id = user.getId();
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        for (GrantedAuthority grantedAuthority : user.getRoles()) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                return "/admin";
            }
        }
        for (GrantedAuthority grantedAuthority : user.getRoles()) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                return "/user";
            }
        }
        return "/";
    }

}
