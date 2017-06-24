package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.EmailService;
import cl.duoc.portafolio.service.FunctionaryService;
import cl.duoc.portafolio.utils.FunctionaryUtils;
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
@Qualifier("userAdminBean")
public class FunctionaryAdminBean implements Serializable {

    private static final long serialVersionUID = 2377930989521500160L;

    @Resource(name = "emailService")
    private transient EmailService emailService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;
    private Functionary functionary = null;
    private List<Functionary> functionaries = null;
    private List<JobTitle> jobTitles = null;
    private boolean edit = false;
    private String passOne = null;
    private String passTwo = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionaryAdminBean.class);

    @PostConstruct
    public void initBean() {
        jobTitles = functionaryService.getJobTitles();
        refresh();
    }

    private void refresh() {
        functionary = new Functionary();
        functionaries = functionaryService.getFunctionaries();
        edit = false;
        passOne = StringUtils.EMPTY;
        passTwo = StringUtils.EMPTY;
    }

    public String process() {
        try {
            if (functionary != null) {
                // Esta variable la usaremos para ver si persistimos o no al usuario
                boolean persist = false;
                /**
                 * Lo primero es determinar si el usuario es nuevo o se está
                 * editando.
                 */
                if (edit) {
                    // Es un usuario antiguo
                    if (StringUtils.isNotBlank(passOne) || StringUtils.isNotBlank(passTwo)) {
                        // Ha modificado la contraseña, deberíamos hashear
                        if (StringUtils.equals(passOne, passTwo)) {
                            persist = true;
                            functionary = FunctionaryUtils.hashPasswd(functionary, passOne);
                        }
                    } else {
                        // Quiere decir que no se han cambiado las contraseñas, pero igual persisto
                        persist = true;
                    }

                } else {
                    // Es un usuario nuevo
                    if (StringUtils.isNotBlank(passOne) && StringUtils.equals(passOne, passTwo)) {
                        // Las contraseñas deben existir y ser iguales
                        persist = true;
                        functionary = FunctionaryUtils.hashPasswd(functionary, passOne);
                    }
                }

                if (persist) {
                    Functionary saved = functionaryService.save(functionary);
                    if (saved != null) {
                        refresh();
                        FacesUtils.infoMessage("functionarySaved");
                    } else {
                        FacesUtils.errorMessage("functionaryNotSaved");
                    }
                } else {
                    // Si no persisto es porque las contraseñas no cuadran
                    FacesUtils.errorMessage("passwordNotEquals");
                }
            } else {
                FacesUtils.errorMessage("genericSaveError");
            }
            
            boolean flag = emailService.sendMail("matthew.hss@hotmail.com", "Test", "prueba de correo", false);
            if(flag == true){
                return StringUtils.EMPTY;
            }
        } catch (Exception e) {
            LOGGER.error("Error al intentar persistir funcionario: {}", e.toString());
            FacesUtils.fatalMessage("genericSaveError");
        }        
        return StringUtils.EMPTY;
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String cancel() {
        refresh();
        return StringUtils.EMPTY;
    }

    public String delete() {
        try {
            if (functionary != null) {
                boolean ok = functionaryService.delete(functionary);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("functionaryDeleted");
                } else {
                    FacesUtils.errorMessage("functionaryNotDeleted");
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error al intentar eliminar funcionario: {}", e.toString());
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

    public String getPassOne() {
        return passOne;
    }

    public void setPassOne(String passOne) {
        this.passOne = passOne;
    }

    public String getPassTwo() {
        return passTwo;
    }

    public void setPassTwo(String passTwo) {
        this.passTwo = passTwo;
    }

    public List<JobTitle> getJobTitles() {
        return jobTitles;
    }
}
