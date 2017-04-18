package cl.duoc.portafolio.service;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.WsAssignment;
import cl.duoc.portafolio.model.Workshift;
import java.util.List;

/**
 *
 * @author matthew
 */
public interface FunctionaryService {
    
    public Functionary getFunctionary(final Long id);
    
    public List<Functionary> getFunctionaries();
    
    public List<Functionary> getFunctionaries(final JobTitle function);
    
    public Functionary save(final Functionary functionary);
    
    public boolean delete(final Functionary functionary);
    
    public Workshift getWorkshift(final Long id);
    
    public List<Workshift> getWorkshifts();
    
    public Workshift save(final Workshift workshift);
    
    public boolean delete(final Workshift workshift);
    
    public WsAssignment getWsAssignment(final Long id);
    
    public List<WsAssignment> getWsAssignments();
    
    public List<WsAssignment> getWsAssignments(final Functionary functionary);
    
    public List<WsAssignment> getWsAssignments(final Workshift workshift);
    
    public WsAssignment save(final WsAssignment wsassignment);
    
    public boolean delete(final WsAssignment wsassignment);
    
    public JobTitle getJobTitle(final Long id);
    
    public List<JobTitle> getJobTitles();
    
    public JobTitle save(final JobTitle jobTitle);
    
    public boolean delete(final JobTitle jobTitle);
}
