package se.hulot.jbpm6jboss7example.ejb;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local(ProcessLocal.class)
@Stateless(mappedName="ProcessLocal")
public class ProcessBean implements ProcessLocal {

    @EJB
    JbpmService jbpmService;
    
    public long startProcess(Map<String, Object> params) throws Exception {
        long processInstanceId = jbpmService.createProcess("se.hulot.jbpm6jboss7example", params);

        return processInstanceId;
    }

}
