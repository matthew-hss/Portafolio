/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.service;

import cl.duoc.portafolio.model.Function;
import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.WSAssignment;
import cl.duoc.portafolio.model.Workshift;
import java.util.List;

/**
 *
 * @author matthew
 */
public interface FunctionaryService {
    
    public Functionary getFunctionary(final Long id);
    
    public List<Functionary> getFunctionaries();
    
    public List<Functionary> getFunctionaries(final Function function);
    
    public Functionary save(final Functionary functionary);
    
    public boolean delete(final Functionary functionary);
    
    public Workshift getWorkshift(final Long id);
    
    public List<Workshift> getWorkshifts();
    
    public Workshift save(final Workshift workshift);
    
    public boolean delete(final Workshift workshift);
    
    public WSAssignment getWSAssignment(final Long id);
    
    public List<WSAssignment> getWSAssignments();
    
    public List<WSAssignment> getWSAssignments(final Functionary functionary);
    
    public List<WSAssignment> getWSAssignments(final Workshift workshift);
    
    public WSAssignment save(final WSAssignment wsassignment);
    
    public boolean delete(final WSAssignment wsassignment);
    
    public Function getFunction(final Long id);
    
    public List<Function> getFunctions();
    
    public Function save(final Function function);
    
    public boolean delete(final Function function);
}
