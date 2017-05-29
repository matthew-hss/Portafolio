package cl.duoc.portafolio.application.security;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.service.FunctionaryService;
import cl.duoc.portafolio.utils.RutUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service("portafolioAuthenticationProvider")
public class PortafolioAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "functionaryService")
    private FunctionaryService functionaryService;
    private final static String BAD_USER = "Invalid user or password";
    private final static String INVALID_ACCESS = "You do not have privileges to access the system.";
    private final static Logger LOGGER = LoggerFactory.getLogger(PortafolioAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication auth = null;

        String rutStr = StringUtils.trimToEmpty((String) authentication.getPrincipal());
        String password = StringUtils.trimToEmpty((String) authentication.getCredentials());
        if (RutUtils.isRut(rutStr) && StringUtils.isNotBlank(password)) {
            Integer rut = RutUtils.parseRut(rutStr);
            boolean ok = functionaryService.authenticate(rut, password);
            if (ok) {
                Functionary functionary = functionaryService.getFunctionary(rut);

                List<GrantedAuthority> roles = new ArrayList<>();
                roles.add(new SimpleGrantedAuthority(StringUtils.trimToEmpty(String.format("ROLE_%s", "USER"))));
//                roles.add(new SimpleGrantedAuthority(StringUtils.trimToEmpty(String.format("ROLE_%s", user.getRole().name()))));
                auth = new UsernamePasswordAuthenticationToken(functionary, password, roles);
                if (auth.isAuthenticated()) {
                    LOGGER.info("Validación correcta para RUT {}", rut);
                } else {
                    throw new BadCredentialsException(BAD_USER);
                }

//                if (!roles.isEmpty()) {
//                    auth = new UsernamePasswordAuthenticationToken(user, password, roles);
//                    if (auth.isAuthenticated()) {
//                        LOGGER.info("Validación correcta para RUT {}", rut);
//                    } else {
//                        throw new BadCredentialsException(BAD_USER);
//                    }
//                } else {
//                    throw new BadCredentialsException(INVALID_ACCESS);
//                }
            } else {
                throw new BadCredentialsException(BAD_USER);
            }
        } else {
            throw new BadCredentialsException(INVALID_ACCESS);
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> type) {
        return UsernamePasswordAuthenticationToken.class.equals(type);
    }
}
