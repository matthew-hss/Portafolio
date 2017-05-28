package cl.duoc.portafolio.service.impl;

import cl.duoc.portafolio.vo.Role;
import cl.duoc.portafolio.model.User;
import cl.duoc.portafolio.repository.UserRepository;
import cl.duoc.portafolio.service.UserService;
import cl.duoc.portafolio.utils.CryptoUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author matthew
 */
@Service("userService")
public class UserServiceImpl implements UserService, Serializable {

    private static final long serialVersionUID = 7419845182456047534L;

    @Resource(name = "userRepository")
    private UserRepository userRepository;
    
    private List<Role> roles = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @PostConstruct
    public void initService() {
        /**
         * @Warning Cargo listado de roles, es una tarea manual, si se agrega un
         * rol nuevo debe modificarse.
         */
        roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.STAFF);
        roles.add(Role.ADMIN);
    }

    @Override
    public User getUser(Long id) {
        User user = null;
        try {
            if (id != null && id > 0) {
                user = userRepository.findOne(id);
            }
        } catch (Exception e) {
            user = null;
            LOGGER.error("Error al obtener usuario: ", e.toString());
            LOGGER.debug("Error al obtener usuario: ", e.toString());
        }
        return user;
    }
    
    @Override
    public User getUser(Integer rut) {
        User user = null;
        try {
            if (rut != null && rut > 0) {
                user = userRepository.findByRut(rut);
            }
        } catch (Exception e) {
            user = null;
            LOGGER.error("Error al obtener usuario: {}", e.toString());
            LOGGER.debug("Error al obtener usuario: {}", e.toString(), e);
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = userRepository.findAll();
        } catch (Exception e) {
            users = null;
            LOGGER.error("Error al obtener usuarios: ", e.toString());
            LOGGER.debug("Error al obtener usuarios: ", e.toString());
        }
        return users;
    }

    @Override
    public List<User> getUsers(Role role) {
        List<User> users = new ArrayList<>();
        try {
            if (role != null) {
                users = userRepository.findByRole(role);
            }
        } catch (Exception e) {
            users = null;
            LOGGER.error("Error al obtener usuarios: ", e.toString());
            LOGGER.debug("Error al obtener usuarios: ", e.toString());
        }
        return users;
    }

    @Override
    @Transactional
    public boolean delete(User user) {
        boolean deleted = false;
        try {
            if (user != null) {
                userRepository.delete(user);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar usuario: ", e.toString());
            LOGGER.debug("Error al eliminar usuario: ", e.toString());
        }
        return deleted;
    }

    @Override
    @Transactional
    public User save(User user) {
        User saved = null;
        try {
            if (user != null) {
                saved = userRepository.save(user);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar usuario: {}", e.toString());
            LOGGER.debug("Error al guardar usuario: {}", e.toString());
        }
        return saved;
    }

    @Override
    public List<Role> getRoles() {
        return new ArrayList<>(roles);
    }

    @Override
    public boolean authenticate(Integer rut, String password) {
        boolean ok = false;
        try {
            if (rut != null && StringUtils.isNotBlank(password)) {
                // Salto para hash corresponde a 4 digitos intermedios del rut.
                String salt = StringUtils.substring(Integer.toString(rut), 2, 6);
                String passwd = CryptoUtils.hashSha512(password, salt);
                User user = userRepository.findByRutAndPassword(rut, passwd);
                if (user != null) {
                    ok = user.isActive();
                }
            }
        } catch (Exception e) {
            ok = false;
            LOGGER.error("Error al autenticar usuario: {}", e.toString());
            LOGGER.debug("Error al autenticar usuario: {}", e.toString(), e);
        }
        return ok;
    }
}
