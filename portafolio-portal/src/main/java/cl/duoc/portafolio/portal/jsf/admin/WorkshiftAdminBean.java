package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Workshift;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.FunctionaryService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Javier González M.
 */
@Component
@Scope("view")
@Qualifier("workshiftAdminBean")
public class WorkshiftAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748451255L;

    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;

    private Workshift workshift = null;
    private List<Workshift> workshifts = null;
    private boolean edit = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkshiftAdminBean.class);

    @PostConstruct
    public void init() {
        refresh();
    }

    public void refresh() {
        workshift = new Workshift();
        workshifts = functionaryService.getWorkshifts();
        edit = false;
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String process() {
        if (workshift != null) {
            try {
                if(workshift.getEndTime().before(workshift.getStartTime())){
                    workshift.setEndTime(DateUtils.addDays(workshift.getEndTime(), 1));
                }
                Workshift save = functionaryService.save(workshift);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("workshiftSaved");
                } else {
                    FacesUtils.errorMessage("workshiftNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al agregar turno: {}", e.toString(), e);
                FacesUtils.fatalMessage("workshiftNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }

    public String cancel() {
        refresh();
        return StringUtils.EMPTY;
    }

    public String delete() {
        boolean ok = false;
        if (workshift != null) {
            try {
                ok = functionaryService.delete(workshift);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("workshiftDeleted");
                } else {
                    FacesUtils.errorMessage("workshiftNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar turno: {}", e.toString(), e);
                FacesUtils.fatalMessage("workshiftNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public Workshift getWorkshift() {
        return workshift;
    }

    public void setWorkshift(Workshift workshift) {
        this.workshift = workshift;
    }

    public List<Workshift> getWorkshifts() {
        return workshifts;
    }

    public void setWorkshifts(List<Workshift> workshifts) {
        this.workshifts = workshifts;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
