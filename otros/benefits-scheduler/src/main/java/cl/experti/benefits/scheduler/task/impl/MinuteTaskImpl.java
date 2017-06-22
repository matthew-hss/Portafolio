package cl.experti.benefits.scheduler.task.impl;

import cl.experti.benefits.model.User;
import cl.experti.benefits.scheduler.task.MinuteTask;
import cl.experti.benefits.service.UserService;
import cl.experti.benefits.vo.Rol;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastián Salazar Molina.
 */
@Service("minuteTask")
public class MinuteTaskImpl implements MinuteTask, Serializable {

    private static final long serialVersionUID = 5044962634676146176L;

    @Resource(name = "userService")
    private transient UserService userService;
    private static final Integer EXPERTI_RUT = 76370470;
    private static final Logger LOGGER = LoggerFactory.getLogger(MinuteTaskImpl.class);

    @Override
    public void process() {
        try {
            validateAdminRole();
        } catch (Exception e) {
            LOGGER.error("Error al procesar tareas por minuto: {}", e.toString());
            LOGGER.debug("Error al procesar tareas por minuto: {}", e.toString(), e);
        }
    }

    public void validateAdminRole() {
        try {
            List<User> users = userService.getUsers(Rol.ADMIN);
            for (User user : users) {
                if (!EXPERTI_RUT.equals(user.getCompany().getRut())) {
                    user.setRol(Rol.STAFF);
                    userService.save(user);
                    LOGGER.info("Usuario: {} de compañía: {} actualizado", user.getName(), user.getCompany().getLegalName());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error al validar rol de administrador: {}", e.toString());
            LOGGER.debug("Error al validar rol de administrador: {}", e.toString(), e);
        }
    }
}
