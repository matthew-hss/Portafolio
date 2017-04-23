/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.JobTitle;
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
@Qualifier("jobTitleAdminBean")
public class JobTitleAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748547255L;

    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;

    private JobTitle jobTitle = null;
    private List<JobTitle> jobTitles = null;
    private boolean edit = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTitleAdminBean.class);

    @PostConstruct
    public void init() {
        refresh();
    }

    public void refresh() {
        edit = false;
        jobTitle = new JobTitle();
        jobTitles = functionaryService.getJobTitles();
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String delete() {
        if (jobTitle != null) {
            try {
                boolean ok = functionaryService.delete(jobTitle);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("jobTitleDeleted");
                } else {
                    FacesUtils.errorMessage("jobTitleNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar cargo: {}", e.toString(), e);
                FacesUtils.fatalMessage("jobTitleNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }

    public String process() {
        if (jobTitle != null) {
            try {
                JobTitle save = functionaryService.save(jobTitle);
                if (save != null) {
                    refresh();
                    FacesUtils.infoMessage("jobTitleSaved");
                } else {
                    FacesUtils.infoMessage("jobTitleNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al guardar cargo: {}", e.toString(), e);
                FacesUtils.fatalMessage("jobTitleNotSaved");
            }
        }
        return StringUtils.EMPTY;
    }

    public String cancel() {
        refresh();
        return StringUtils.EMPTY;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<JobTitle> getJobTitles() {
        return jobTitles;
    }

    public void setJobTitles(List<JobTitle> jobTitles) {
        this.jobTitles = jobTitles;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
