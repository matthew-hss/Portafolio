package cl.duoc.portafolio.service.impl;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.WSAssignment;
import cl.duoc.portafolio.model.Workshift;
import cl.duoc.portafolio.repository.FunctionRepository;
import cl.duoc.portafolio.repository.FunctionaryRepository;
import cl.duoc.portafolio.repository.WSAssignmentRepository;
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
    @Resource(name = "wsassignmentRepository")
    private WSAssignmentRepository wsassignmentRepository;
    @Resource(name = "functionRepository")
    private FunctionRepository functionRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionaryServiceImpl.class);

    @Override
    public Functionary getFunctionary(Long id) {
        Functionary functionary = null;
        try {
            if (id != null && id > 0) {
                functionary = functionaryRepository.findById(id);
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
    public List<Functionary> getFunctionaries(JobTitle function) {
        List<Functionary> functionaries = new ArrayList<>();
        try {
            if (function != null) {
                functionaries = functionaryRepository.findByFunction(function);
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
                workshift = workshiftRepository.findById(id);
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
    public WSAssignment getWSAssignment(Long id) {
        WSAssignment wsa = null;
        try {
            if (id != null && id > 0) {
                wsa = wsassignmentRepository.findById(id);
            }
        } catch (Exception e) {
            wsa = null;
            LOGGER.error("Error al obtener asignación de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener asignación de turno de trabajo: {}", e.toString());
        }
        return wsa;
    }

    @Override
    public List<WSAssignment> getWSAssignments() {
        List<WSAssignment> wsas = new ArrayList<>();
        try {
            wsas = wsassignmentRepository.findAll();
        } catch (Exception e) {
            wsas = new ArrayList<>();
            LOGGER.error("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
        }
        return wsas;
    }

    @Override
    public List<WSAssignment> getWSAssignments(Functionary functionary) {
        List<WSAssignment> wsas = new ArrayList<>();
        try {
            if (functionary != null) {
                wsas = wsassignmentRepository.findByFunctionary(functionary);
            }
        } catch (Exception e) {
            wsas = new ArrayList<>();
            LOGGER.error("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
            LOGGER.debug("Error al obtener asignaciones de turno de trabajo: {}", e.toString());
        }
        return wsas;
    }

    @Override
    public List<WSAssignment> getWSAssignments(Workshift workshift) {
        List<WSAssignment> wsas = new ArrayList<>();
        try {
            if (workshift != null) {
                wsas = wsassignmentRepository.findByWorkshift(workshift);
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
    public WSAssignment save(WSAssignment wsassignment) {
        WSAssignment saved = null;
        try {
            if (wsassignment != null) {
                saved = wsassignmentRepository.save(wsassignment);
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
    public boolean delete(WSAssignment wsassignment) {
        boolean deleted = false;
        try {
            if (wsassignment != null) {
                wsassignmentRepository.delete(wsassignment);
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
    public JobTitle getFunction(Long id) {
        JobTitle function = null;
        try {
            if (id != null && id > 0) {
                function = functionRepository.findById(id);
            }
        } catch (Exception e) {
            function = null;
            LOGGER.error("Error al obtener función: {}", e.toString());
            LOGGER.debug("Error al obtener función: {}", e.toString());
        }
        return function;
    }

    @Override
    public List<JobTitle> getFunctions() {
        List<JobTitle> functions = new ArrayList<>();
        try {
            functions = functionRepository.findAll();
        } catch (Exception e) {
            functions = new ArrayList<>();
            LOGGER.error("Error al obtener funciones: {}", e.toString());
            LOGGER.debug("Error al obtener funciones: {}", e.toString());
        }
        return functions;
    }

    @Override
    @Transactional
    public JobTitle save(JobTitle function) {
        JobTitle saved = null;
        try {
            if (function != null) {
                saved = functionRepository.save(function);
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
    public boolean delete(JobTitle function) {
        boolean deleted = false;
        try {
            functionRepository.delete(function);
            deleted = true;
        } catch (Exception e) {
            deleted = false;
            LOGGER.error("Error al eliminar funcion: {}", e.toString());
            LOGGER.debug("Error al eliminar funcion: {}", e.toString());
        }
        return deleted;
    }

}
