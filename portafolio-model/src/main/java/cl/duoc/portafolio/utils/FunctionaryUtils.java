package cl.duoc.portafolio.utils;

import cl.duoc.portafolio.model.Functionary;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Javier González M.
 */
public class FunctionaryUtils implements Serializable {

    private static final long serialVersionUID = 7176835846353785344L;

    /**
     * Clase utilitaria, no debería instanciarse nunca.
     */
    private FunctionaryUtils() {
        throw new AssertionError();
    }

    /**
     *
     * @param functionary Usuario cuyo password será hasheado antes de persistir.
     * @return Objeto de clase cl.duoc.portafolio.model.User con contraseña
     * hasheada en SHA512 con salto aleatorio en función al RUT del usuario.
     */
    public static Functionary hashPasswd(Functionary functionary) {
        if (functionary != null) {
            // Salto para hash corresponde a 4 digitos intermedios del rut.
            // Se realiza el cambio de password antes de persistir al usuario.
            String salt = StringUtils.substring(StringUtils.trimToEmpty(Integer.toString(functionary.getRut())), 2, 6);
            String password = CryptoUtils.hashSha512(functionary.getPassword(), salt);
            functionary.setPassword(password);
        }
        return functionary;
    }

    /**
     *
     * @param functionary Usuario cuyo password será hasheado antes de persistir.
     * @param password Password a usar
     * @return Objeto de clase cl.duoc.portafolio.model.User con contraseña
     * hasheada en SHA512 con salto aleatorio en función al RUT del usuario.
     */
    public static Functionary hashPasswd(final Functionary functionary, final String password) {
        Functionary hashedFunctionary = null;
        if (functionary != null && StringUtils.isNotBlank(password)) {
            // Salto para hash corresponde a 4 digitos intermedios del rut.
            // Se realiza el cambio de password antes de persistir al usuario.
            String salt = StringUtils.substring(StringUtils.trimToEmpty(Integer.toString(functionary.getRut())), 2, 6);
            String hashedPassword = CryptoUtils.hashSha512(password, salt);

            // Construimos un objeto nuevo
            hashedFunctionary = new Functionary();
            hashedFunctionary.setId(functionary.getId());
            hashedFunctionary.setRut(functionary.getRut());
            hashedFunctionary.setName(functionary.getName());
            hashedFunctionary.setSurname(functionary.getSurname());
            hashedFunctionary.setPassword(hashedPassword);
            hashedFunctionary.setJobTitle(functionary.getJobTitle());
        }
        return hashedFunctionary;
    }
}
