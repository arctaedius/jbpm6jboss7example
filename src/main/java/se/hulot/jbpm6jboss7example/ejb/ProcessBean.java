package se.hulot.jbpm6jboss7example.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Startup
@javax.ejb.Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class ProcessBean implements ProcessLocal {

    @EJB
    JbpmService jbpmService;
    
    public long startProcess() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        long processInstanceId = jbpmService.createProcess("se.hulot.jbpm6jboss7example", params);

        return processInstanceId;
    }

}
