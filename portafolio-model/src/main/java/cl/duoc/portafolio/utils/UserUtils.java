package cl.duoc.portafolio.utils;

import cl.duoc.portafolio.model.User;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Javier González M.
 */
public class UserUtils implements Serializable {

    private static final long serialVersionUID = 7176835846353785344L;

    /**
     * Clase utilitaria, no debería instanciarse nunca.
     */
    private UserUtils() {
        throw new AssertionError();
    }

    /**
     *
     * @param user Usuario cuyo password será hasheado antes de persistir.
     * @return Objeto de clase cl.experti.benefits.model.User con contraseña
     * hasheada en SHA512 con salto aleatorio en función al RUT del usuario.
     */
    public static User hashPasswd(User user) {
        if (user != null) {
            // Salto para hash corresponde a 4 digitos intermedios del rut.
            // Se realiza el cambio de password antes de persistir al usuario.
            String salt = StringUtils.substring(StringUtils.trimToEmpty(Integer.toString(user.getRut())), 2, 6);
            String password = CryptoUtils.hashSha512(user.getPassword(), salt);
            user.setPassword(password);
        }
        return user;
    }

    /**
     *
     * @param user Usuario cuyo password será hasheado antes de persistir.
     * @param password Password a usar
     * @return Objeto de clase cl.experti.benefits.model.User con contraseña
     * hasheada en SHA512 con salto aleatorio en función al RUT del usuario.
     */
    public static User hashPasswd(final User user, final String password) {
        User hasedUser = null;
        if (user != null && StringUtils.isNotBlank(password)) {
            // Salto para hash corresponde a 4 digitos intermedios del rut.
            // Se realiza el cambio de password antes de persistir al usuario.
            String salt = StringUtils.substring(StringUtils.trimToEmpty(Integer.toString(user.getRut())), 2, 6);
            String hasedPassword = CryptoUtils.hashSha512(password, salt);

            // Construimos un objeto nuevo
            hasedUser = new User();
//            hasedUser.setActive(user.isActive());
//            hasedUser.setCompany(user.getCompany());
            hasedUser.setId(user.getId());
            hasedUser.setName(user.getName());
            hasedUser.setPassword(hasedPassword);
            hasedUser.setRole(user.getRole());
            hasedUser.setRut(user.getRut());
        }
        return hasedUser;
    }
}