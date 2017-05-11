package cl.duoc.portafolio.service.impl;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.WsAssignment;
import cl.duoc.portafolio.model.Workshift;
import cl.duoc.portafolio.repository.FunctionaryRepository;
import cl.duoc.portafolio.repository.WorkshiftRepository;
import cl.duoc.portafolio.service.FunctionaryService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import cl.duoc.portafolio.repository.JobTitleRepository;
import cl.duoc.portafolio.repository.WsAssignmentRepository;
import cl.duoc.portafolio.utils.CryptoUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author matthew
 */
@Service("functionaryService")
public class FunctionaryServiceImpl implements FunctionaryService, Serializable {

    private static final long serialVersionUID = 7419845182456042834L;

    @Resource(name = "functionaryRepository")
    private FunctionaryRepository functionaryRepository;
    @Resource(name = "workshiftRepository")
    private WorkshiftRepository workshiftRepository;
    @Resource(name = "wsAssignmentRepository")
    private WsAssignmentRepository wsAssignmentRepository;
    @Resource(name = "jobTitleRepository")
    private JobTitleRepository jobTitleRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionaryServiceImpl.class);

    @Override
    public Functionary getFunctionary(Long id) {
        Functionary functionary = null;
        try {
            if (id != null && id > 0) {
                functionary = functionaryRepository.findOne(id);
            }
        } catch (Exception e) {
            functionary = null;
            LOGGER.error("Error al obtener funcionario: {}", e.toString());
            LOGGER.debug("Error al obtener funcionario: {}", e.toString());
        }
        return functionary;
    }

    @Override
    public List<Functionary> getFunctionaries() {
        List<Functionary> functionaries = new ArrayList<>();
        try {
            functionaries = functionaryRepository.findAll();
        } catch (Exception e) {
            functionaries = new ArrayList<>();
            LOGGER.error("Error al obtener funcionarios: {}", e.toString());
            LOGGER.debug("Error al obtener funcionarios: {}", e.toString());
        }
        return functionaries;
    }

    @Override
    public List<Functionary> getFunctionaries(JobTitle jobTitle) {
        List<Functionary> functionaries = new ArrayList<>();
        try {
            if (jobTitle != null) {
                functionaries = functionaryRepository.findByJobTitle(jobTitle);
            }
        } catch (Exception e) {
            functionaries = new ArrayList<>();
            LOGGER.error("Error al obtener funcionarios: {}", e.toString());
            LOGGER.debug("Error al obtener funcionarios: {}", e.toString());
        }
        return functionaries;
    }

    @Override
    @Transactional
    public Functionary save(Functionary functionary) {
        Functionary saved = null;
        try {
            if (functionary != null) {
                saved = functionaryRepository.save(functionary);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar funcionario: {}", e.toString());
            LOGGER.debug("Error al guardar funcionario: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(Functionary functionary) {
        boolean deleted = false;
        try {
            if (functionary != null && functionary.getId() > 0) {
                functionaryRepository.delete(functionary);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar funcionario: {}", e.toString());
            LOGGER.debug("Error al eliminar funcionario: {}", e.toString());
        }
        return deleted;
    }

    @Override
    public Workshift getWorkshift(Long id) {
        Workshift workshift = null;
        try {
            if (id != null && id > 0) {
                workshift = workshiftRepository.findOne(id);
            }
        } catch (Exception e) {
            workshift = null;
            LOGGER.error("Error al obtener turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener turno de trabajo: {}", e.toString());
        }
        return workshift;
    }

    @Override
    public List<Workshift> getWorkshifts() {
        List<Workshift> workshifts = new ArrayList<>();
        try {
            workshifts = workshiftRepository.findAll();
        } catch (Exception e) {
            workshifts = new ArrayList<>();
            LOGGER.error("Error al obtener turnos de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener turnos de trabajo: {}", e.toString());
        }
        return workshifts;
    }

    @Override
    @Transactional
    public Workshift save(Workshift workshift) {
        Workshift saved = null;
        try {
            if (workshift != null) {
                saved = workshiftRepository.save(workshift);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al guardar turno de trabajo: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(Workshift workshift) {
        boolean deleted = false;
        try {
            if (workshift != null) {
                workshiftRepository.delete(workshift);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al eliminar turno de trabajo: {}", e.toString());
        }
        return deleted;
    }

    @Override
    public WsAssignment getWsAssignment(Long id) {
        WsAssignment wsa = null;
        try {
            if (id != null && id > 0) {
                wsa = wsAssignmentRepository.findOne(id);
            }
        } catch (Exception e) {
            wsa = null;
            LOGGER.error("Error al obtener asignación de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener asignación de turno de trabajo: {}", e.toString());
        }
        return wsa;
    }

    @Override
    public List<WsAssignment> getWsAssignments() {
        List<WsAssignment> wsas = new ArrayList<>();
        try {
            wsas = wsAssignmentRepository.findAll();
        } catch (Exception e) {
            wsas = new ArrayList<>();
            LOGGER.error("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
        }
        return wsas;
    }

    @Override
    public List<WsAssignment> getWsAssignments(Functionary functionary) {
        List<WsAssignment> wsas = new ArrayList<>();
        try {
            if (functionary != null) {
                wsas = wsAssignmentRepository.findByFunctionary(functionary);
            }
        } catch (Exception e) {
            wsas = new ArrayList<>();
            LOGGER.error("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
        }
        return wsas;
    }

    @Override
    public List<WsAssignment> getWsAssignments(Workshift workshift) {
        List<WsAssignment> wsas = new ArrayList<>();
        try {
            if (workshift != null) {
                wsas = wsAssignmentRepository.findByWorkshift(workshift);
            }
        } catch (Exception e) {
            wsas = new ArrayList<>();
            LOGGER.error("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
        }
        return wsas;
    }

    @Override
    @Transactional
    public WsAssignment save(WsAssignment wsassignment) {
        WsAssignment saved = null;
        try {
            if (wsassignment != null) {
                saved = wsAssignmentRepository.save(wsassignment);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar asignación de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al guardar asignación de turno de trabajo: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(WsAssignment wsassignment) {
        boolean deleted = false;
        try {
            if (wsassignment != null) {
                wsAssignmentRepository.delete(wsassignment);
                deleted = true;
            }
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar asignación de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al eliminar asignación de turno de trabajo: {}", e.toString());
        }
        return deleted;
    }

    @Override
    public JobTitle getJobTitle(Long id) {
        JobTitle jobTitle = null;
        try {
            if (id != null && id > 0) {
                jobTitle = jobTitleRepository.findOne(id);
            }
        } catch (Exception e) {
            jobTitle = null;
            LOGGER.error("Error al obtener función: {}", e.toString());
            LOGGER.debug("Error al obtener función: {}", e.toString());
        }
        return jobTitle;
    }

    @Override
    public List<JobTitle> getJobTitles() {
        List<JobTitle> jobTitles = new ArrayList<>();
        try {
            jobTitles = jobTitleRepository.findAll();
        } catch (Exception e) {
            jobTitles = new ArrayList<>();
            LOGGER.error("Error al obtener funciones: {}", e.toString());
            LOGGER.debug("Error al obtener funciones: {}", e.toString());
        }
        return jobTitles;
    }

    @Override
    @Transactional
    public JobTitle save(JobTitle jobTitle) {
        JobTitle saved = null;
        try {
            if (jobTitle != null) {
                saved = jobTitleRepository.save(jobTitle);
            }
        } catch (Exception e) {
            saved = null;
            LOGGER.error("Error al guardar funcion: {}", e.toString());
            LOGGER.debug("Error al guardar funcion: {}", e.toString());
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean delete(JobTitle jobTitle) {
        boolean deleted = false;
        try {
            jobTitleRepository.delete(jobTitle);
            deleted = true;
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar funcion: {}", e.toString());
            LOGGER.debug("Error al eliminar funcion: {}", e.toString());
        }
        return deleted;
    }

    @Override
    public boolean authenticate(Integer rut, String password) {
        boolean ok = false;
        try {
            if (rut != null && StringUtils.isNotBlank(password)) {
                // Salto para hash corresponde a 4 digitos intermedios del rut.
                String salt = StringUtils.substring(Integer.toString(rut), 2, 6);
                String passwd = CryptoUtils.hashSha512(password, salt);
                Functionary functionary = functionaryRepository.findByRutAndPassword(rut, passwd);

                if (functionary != null) {
                    ok = true;
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
