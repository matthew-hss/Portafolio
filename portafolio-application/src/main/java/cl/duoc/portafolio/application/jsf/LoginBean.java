package cl.duoc.portafolio.application.jsf;

import cl.duoc.portafolio.application.utils.FacesUtils;
import cl.duoc.portafolio.model.WsAssignment;
import cl.duoc.portafolio.service.FunctionaryService;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
@Qualifier("loginBean")
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 3116373434557613568L;

    @Resource(name = "functionaryService")
    private FunctionaryService functionaryService;
    private Integer rut = null;
    private String password = null;
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginBean.class);

    public String process() {
        String accion = StringUtils.EMPTY;
        try {
            if (rut != null && StringUtils.isNotBlank(password)) {
                boolean ok = functionaryService.authenticate(rut, password);
                if (ok) {
                    WsAssignment wsa = functionaryService.getWsAssignment(functionaryService.getFunctionary(rut));
                    if (wsa != null) {
                        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

                        String redireccion = "/login";
                        LOGGER.debug("Redirección: '{}'", redireccion);

                        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher(redireccion);
                        dispatcher.forward((ServletRequest) context.getRequest(),
                                (ServletResponse) context.getResponse());

                        FacesContext.getCurrentInstance().responseComplete();
                    }else{
                        FacesUtils.errorMessage("wsaError");
                    }
                } else {
                    FacesUtils.errorMessage("invalidLogin");
                }
            } else {
                FacesUtils.errorMessage("invalidLogin");
            }
        } catch (Exception e) {
            FacesUtils.fatalMessage("loginError");
            LOGGER.error("Error al procesar logueo: {}", e.toString());
        }
        return accion;
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
