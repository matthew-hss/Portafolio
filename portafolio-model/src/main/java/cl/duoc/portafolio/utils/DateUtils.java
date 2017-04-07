package cl.duoc.portafolio.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class DateUtils implements Serializable {

    private static final long serialVersionUID = 7176835503513785344L;

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss.SSS";
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String COMPACT_DATE_FORMAT = "yyyyMMdd";
    private static final Locale CL = new Locale("es", "CL");
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * Clase utilitaria, no debería instanciarse nunca.
     */
    private DateUtils() {
        throw new AssertionError();
    }

    /**
     *
     * @return El formato ISO para fechas.
     */
    public static String getDateFormat() {
        return DATE_FORMAT;
    }

    /**
     *
     * @return El formato ISO para tiempo con milésimas de segundos.
     */
    public static String getTimeFormat() {
        return TIME_FORMAT;
    }

    /**
     *
     * @return El formato de Fecha y tiempo completa en formato ISO.
     */
    public static String getTimeStampFormat() {
        return TIMESTAMP_FORMAT;
    }

    /**
     *
     * @return Locale usado para el formateo de tiempo.
     */
    public static Locale getLocale() {
        return CL;
    }

    /**
     *
     * @param dateStr Fecha en formato ISO.
     * @param timeStr Hora en formato ISO con milésimas de segundos.
     * @return Un objeto java.util.Date en base a la fecha y hora o null en
     * cualquier otro caso.
     */
    public static Date createTimeStamp(final String dateStr, final String timeStr) {
        Date ts = null;
        try {
            if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(timeStr)) {
                SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT, CL);
                ts = sdf.parse(String.format("%s %s", StringUtils.trimToEmpty(dateStr), StringUtils.trimToEmpty(timeStr)));
            }
        } catch (Exception e) {
            ts = null;
            LOGGER.error("Error al crear Fecha: {}", e.toString());
        }
        return ts;
    }

    /**
     *
     * @param dateStr Fecha en formato ISO.
     * @return Un objeto java.util.Date en base a la fecha o null en cualquier
     * otro caso.
     */
    public static Date makeDate(final String dateStr) {
        Date ts = null;
        try {
            if (StringUtils.isNotBlank(dateStr)) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, CL);
                ts = sdf.parse(StringUtils.trimToEmpty(dateStr));
            }
        } catch (Exception e) {
            ts = null;
            LOGGER.error("Error al crear Fecha: {}", e.toString());
        }
        return ts;
    }

    /**
     *
     * @param dateStr String que contiene la Fecha.
     * @param formatStr Formato válido para java.text.SimpleDateFormat
     * @return Un objeto java.util.Date o null en cualquier otro caso.
     */
    public static Date makeDate(final String dateStr, final String formatStr) {
        Date ts = null;
        try {
            if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(formatStr)) {
                SimpleDateFormat sdf = new SimpleDateFormat(formatStr, CL);
                ts = sdf.parse(StringUtils.trimToEmpty(dateStr));
            }
        } catch (Exception e) {
            ts = null;
            LOGGER.error("Error al crear Fecha: {}", e.toString());
        }
        return ts;
    }

    /**
     *
     * @param day Objeto java.util.Date
     * @return Un objeto java.util.Date que mantiene la fecha, pero cambia el
     * tiempo a las 00:00:00,0
     */
    public static Date firstTime(final Date day) {
        Date first = null;
        try {
            if (day != null) {
                Calendar calendar = new GregorianCalendar(CL);
                calendar.setTime(day);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                first = calendar.getTime();
            }
        } catch (Exception e) {
            first = null;
            LOGGER.error("Error al generar primera hora del día: {}", e.toString());
        }
        return first;
    }

    /**
     *
     * @param day Objeto java.util.Date
     * @return Un objeto java.util.Date que mantiene la fecha, pero cambia el
     * tiempo a las 23:59:59,999
     */
    public static Date lastTime(final Date day) {
        Date last = null;
        try {
            if (day != null) {
                Calendar calendar = new GregorianCalendar(CL);
                calendar.setTime(day);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                last = calendar.getTime();
            }
        } catch (Exception e) {
            last = null;
            LOGGER.error("Error al generar primera hora del día: {}", e.toString());
        }
        return last;
    }

    /**
     *
     * @param day Objeto java.util.Date
     * @return Obtiene la representación en String de la fecha en formato
     * yyyy-MM-dd o un String vacío en cualquier otro caso.
     */
    public static String getISODate(final Date day) {
        String iso = StringUtils.EMPTY;
        try {
            if (day != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, CL);
                iso = StringUtils.trimToEmpty(sdf.format(day));
            }
        } catch (Exception e) {
            iso = StringUtils.EMPTY;
            LOGGER.error("Error al crear fecha ISO: {}", e.toString());
        }
        return iso;
    }

    /**
     *
     * @param day Objeto java.util.Date
     * @return Obtiene el string de la fecha en formato yyyyMMdd o vacío en
     * cualquier otro caso.
     */
    public static String getCompactDateStr(final Date day) {
        String iso = StringUtils.EMPTY;
        try {
            if (day != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(COMPACT_DATE_FORMAT, CL);
                iso = StringUtils.trimToEmpty(sdf.format(day));
            }
        } catch (Exception e) {
            iso = StringUtils.EMPTY;
            LOGGER.error("Error al crear fecha ISO: {}", e.toString());
        }
        return iso;
    }

    /**
     *
     * @return Obtiene el año en curso como valor entero
     */
    public static Integer thisYear() {
        Calendar calendar = new GregorianCalendar(CL);
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }

    /**
     *
     * @param date Fecha
     * @return Objeto con la última hora del día anterior.
     */
    public static Date getLastTimeYesterday(final Date date) {
        Date yesterday = null;
        if (date != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            yesterday = calendar.getTime();
        }
        return yesterday;
    }

    /**
     *
     * @param day Fecha de interés
     * @return El primer día del mes a primera hora del día.
     */
    public static Date firstDayOfMonth(final Date day) {
        Date first = null;
        try {
            if (day != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(day);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                first = firstTime(calendar.getTime());
            }
        } catch (Exception e) {
            first = null;
            LOGGER.error("Error al crear primer día del mes: {}", e.toString());
        }
        return first;
    }

    /**
     *
     * @param day Día de interés
     * @return El último día del mes a la última hora del día.
     */
    public static Date lastDayOfMonth(final Date day) {
        Date last = null;
        try {
            if (day != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(day);
                int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                calendar.set(Calendar.DAY_OF_MONTH, maxDay);
                last = lastTime(calendar.getTime());
            }
        } catch (Exception e) {
            last = null;
            LOGGER.error("Error al crear primer día del mes: {}", e.toString());
        }
        return last;
    }

    /**
     *
     * @param day Día de interés
     * @return El día de ayer en relación al día de interés.
     */
    public static Date yesterday(final Date day) {
        Date ytd = null;
        if (day != null) {
            ytd = org.apache.commons.lang3.time.DateUtils.addDays(day, -1);
        }
        return ytd;
    }

    /**
     *
     * @param day Día de interés
     * @return El día de mañana en relación al día de interés.
     */
    public static Date tomorrow(final Date day) {
        Date next = null;
        if (day != null) {
            next = org.apache.commons.lang3.time.DateUtils.addDays(day, 1);
        }
        return next;
    }

    /**
     *
     * @param one Día uno
     * @param two Día dos
     * @return Determina si las dos fechas son del mismo día independiente de su
     * hora.
     */
    public static boolean isSameDay(final Date one, final Date two) {
        boolean ok = false;
        if (one != null && two != null) {
            ok = org.apache.commons.lang3.time.DateUtils.isSameDay(one, two);
        }
        return ok;
    }

    /**
     *
     * @param date Fecha de interés
     * @return la fecha 7 días en el futuro
     */
    public static Date nextWeek(final Date date) {
        Date week = null;
        if (date != null) {
            week = lastTime(org.apache.commons.lang3.time.DateUtils.addDays(date, 7));
        }
        return week;
    }
}
