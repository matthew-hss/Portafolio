package cl.duoc.portafolio.utils;

import java.io.Serializable;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author matthew
 */
public class CodeUtils implements Serializable {

    private static final long serialVersionUID = 5811924880452204544L;

    /**
     * Clase utilitaria, no debería instanciarse nunca.
     */
    public CodeUtils() {
        throw new AssertionError();
    }

    public static String generateCode(final int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
