package cl.duoc.portafolio.portal.utils;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class AsciiUtils implements Serializable {

    private AsciiUtils() {
        throw new AssertionError();
    }

    /**
     * Función que elimina acentos y caracteres especiales de una cadena de
     * texto.
     *
     * @param input
     * @return cadena de texto limpia de acentos y caracteres especiales.
     */
    public static String removeAccents(final String input) {
        String output = input;
        if (StringUtils.isNotBlank(input)) {
            // Cadena de caracteres original a sustituir.
            String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
            // Cadena de caracteres ASCII que reemplazarán los originales.
            String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
            for (int i = 0; i < original.length(); i++) {
                // Reemplazamos los caracteres especiales.
                output = output.replace(original.charAt(i), ascii.charAt(i));
            }
        }
        return output;
    }
}
