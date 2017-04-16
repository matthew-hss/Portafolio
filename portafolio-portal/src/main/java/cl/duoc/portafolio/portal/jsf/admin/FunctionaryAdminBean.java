package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.Functionary;
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
@Qualifier("functionaryAdminBean")
public class FunctionaryAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748531255L;

    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;

    private Functionary functionary = null;
    private List<Functionary> functionaries = null;
    private boolean edit = false;
    private List<JobTitle> jobTitles = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionaryAdminBean.class);

    @PostConstruct
    public void initBean() {
        jobTitles = functionaryService.getJobTitles();
        refresh();
    }

    private void refresh() {
        edit = false;
        functionary = new Functionary();
        functionaries = functionaryService.getFunctionaries();
    }

    public String editBeneficiary() {
        return StringUtils.EMPTY;
    }

    public String deleteFunctionary() {
        if (functionary != null) {
            boolean ok = functionaryService.delete(functionary);
            if (ok) {
                refresh();
                FacesUtils.infoMessage("functionaryDeleted");
            } else {
                FacesUtils.errorMessage("functionaryNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public String process() {
        if (functionary != null) {
            try {
                Functionary save = functionaryService.save(functionary);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("functionarySaved");
                } else {
                    FacesUtils.errorMessage("functionaryNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al insertar el funcionario: {}", e.toString(), e);
                FacesUtils.fatalMessage("functionaryNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }
    
    

    public Functionary getFunctionary() {
        return functionary;
    }

    public void setFunctionary(Functionary functionary) {
        this.functionary = functionary;
    }

    public List<Functionary> getFunctionaries() {
        return functionaries;
    }

    public void setFunctionaries(List<Functionary> functionaries) {
        this.functionaries = functionaries;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public List<JobTitle> getJobTitles() {
        return jobTitles;
    }
}
