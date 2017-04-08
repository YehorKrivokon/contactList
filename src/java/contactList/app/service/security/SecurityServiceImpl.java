package contactList.app.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by комп on 05.04.2017.
 */

@Service
public class SecurityServiceImpl implements SecurityService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername() {
        String userDetails = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDetails;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password);
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            logger.debug(String.format("Successfully auto logged in", username));
        }
    }

    @Override
    public String returnPageByCheckingOnAnonymous(String ifNotAnonymous, String ifAnonymous) {
        String result = ifNotAnonymous;
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            result = ifAnonymous;
        }
        return result;
    }
}
