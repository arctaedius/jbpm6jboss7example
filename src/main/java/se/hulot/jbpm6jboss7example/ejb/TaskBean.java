package se.hulot.jbpm6jboss7example.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;

@Local(TaskLocal.class)
@Stateless(mappedName="TaskLocal")
public class TaskBean implements TaskLocal {    
    @EJB
    JbpmService jbpmService;
    
    public List<TaskSummary> retrievePotentialTaskList(String actorId) throws Exception {
    	return jbpmService.getMyPotentialTasks(actorId);
    }

    public List<TaskSummary> retrieveTaskList(String actorId) throws Exception {
    	return jbpmService.getMyTasks(actorId);
    }
    public void claimTask(String actorId, long taskId) throws Exception {
    	jbpmService.startTask(actorId, taskId);
    }
    public void approveTask(String actorId, long taskId) throws Exception {
    	Status status = jbpmService.getTaskStatus(taskId);
    	if( status == Status.Reserved ) {
    		jbpmService.startTask(actorId, taskId);
    	}
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("userName", actorId);
    	jbpmService.completeTask(actorId, taskId, parameters);
    }

}
