package cl.duoc.portafolio.service.impl;

import cl.duoc.portafolio.model.Role;
import cl.duoc.portafolio.model.User;
import cl.duoc.portafolio.repository.RoleRepository;
import cl.duoc.portafolio.repository.UserRepository;
import cl.duoc.portafolio.service.UserService;
import cl.duoc.portafolio.utils.CryptoUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    @Resource(name = "roleRepository")
    private RoleRepository roleRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public Role getRole(Long id) {
        Role role = null;
        try {
            if (id != null && id > 0) {
                role = roleRepository.findOne(id);
            }
        } catch (Exception e) {
            role = null;
            LOGGER.error("Error al obtener rol: {}", e.toString());
            LOGGER.debug("Error al obtener rol: {}", e.toString());
        }
        return role;
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        try {
            roles = roleRepository.findAll();
        } catch (Exception e) {
            roles = new ArrayList<>();
            LOGGER.error("Error al obtener roles: {}", e.toString());
            LOGGER.debug("Error al obtener roles: {}", e.toString());
        }
        return roles;
    }

    @Override
    @Transactional
    public boolean delete(Role role) {
        boolean deleted = false;
        try {
            if (role != null) {
                roleRepository.delete(role);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar rol: {}", e.toString());
            LOGGER.debug("Error al eliminar rol: {}", e.toString());
        }
        return deleted;
    }

    @Override
    @Transactional
    public Role save(Role role) {
        Role saved = null;
        try {
            if(role != null){
                saved = roleRepository.save(role);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar rol: {}", e.toString());
            LOGGER.debug("Error al guardar rol: {}", e.toString());
        }
        return saved;
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
