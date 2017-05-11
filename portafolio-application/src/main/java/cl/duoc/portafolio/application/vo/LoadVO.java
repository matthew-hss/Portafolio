package cl.duoc.portafolio.application.vo;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class LoadVO {

    private boolean ok = false;
    private Long processOk = 0L;
    private Long processNotOk = 0L;
    private Long total = 0L;
    private String message = null;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Long getProcessOk() {
        return processOk;
    }

    public void setProcessOk(Long processOk) {
        this.processOk = processOk;
    }

    public Long getProcessNotOk() {
        return processNotOk;
    }

    public void setProcessNotOk(Long processNotOk) {
        this.processNotOk = processNotOk;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
