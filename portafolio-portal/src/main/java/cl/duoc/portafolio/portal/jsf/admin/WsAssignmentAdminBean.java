package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Workshift;
import cl.duoc.portafolio.model.WsAssignment;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.FunctionaryService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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
@Qualifier("wsAssignmentAdminBean")
public class WsAssignmentAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748451255L;

    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;

    private WsAssignment wsAssignment = null;
    private List<WsAssignment> wsAssignments = null;
    private List<Functionary> functionaries = null;
    private List<Workshift> workshifts = null;
    private boolean edit = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(WsAssignmentAdminBean.class);

    @PostConstruct
    public void init() {
        functionaries = functionaryService.getFunctionaries();
        workshifts = functionaryService.getWorkshifts();
        refresh();
    }

    public void refresh() {
        wsAssignment = new WsAssignment();
        wsAssignments = functionaryService.getWsAssignments();
        edit = false;
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String process() {
        if (wsAssignment != null) {
            try {
                WsAssignment save = functionaryService.save(wsAssignment);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("wsAssignmentSaved");
                } else {
                    FacesUtils.errorMessage("wsAssignmentNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al agregar asignación: {}", e.toString(), e);
                FacesUtils.fatalMessage("wsAssignmentNotSaved");
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
        if (wsAssignment != null) {
            try {
                ok = functionaryService.delete(wsAssignment);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("wsAssignmentDeleted");
                } else {
                    FacesUtils.errorMessage("wsAssignmentNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar asignación: {}", e.toString(), e);
                FacesUtils.fatalMessage("wsAssignmentNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public WsAssignment getWsAssignment() {
        return wsAssignment;
    }

    public void setWsAssignment(WsAssignment wsAssignment) {
        this.wsAssignment = wsAssignment;
    }

    public List<WsAssignment> getWsAssignments() {
        return wsAssignments;
    }

    public void setWsAssignments(List<WsAssignment> wsAssignments) {
        this.wsAssignments = wsAssignments;
    }

    public List<Functionary> getFunctionaries() {
        return functionaries;
    }

    public void setFunctionaries(List<Functionary> functionaries) {
        this.functionaries = functionaries;
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
