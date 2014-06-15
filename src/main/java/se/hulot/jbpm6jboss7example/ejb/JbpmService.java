package se.hulot.jbpm6jboss7example.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.task.api.InternalTaskService;

@Singleton
@Startup
public class JbpmService {
    @Inject
    @org.kie.internal.runtime.manager.cdi.qualifier.Singleton
    private RuntimeManager singletonManager;

    private Logger log = Logger.getLogger(this.getClass().getName());

    @PostConstruct
    public void configure() {
        // use toString to make sure CDI initializes the bean
        // this makes sure that RuntimeManager is started asap,
        // otherwise after server restart complete task won't move process forward 
    	log.info("Running PostConstruct");
        singletonManager.toString();
    }

    public RuntimeManager getRuntimeManager() {
    	return singletonManager;
    }

    public Long createProcess(String processName, Map<String, Object> parameters) {
        RuntimeEngine runtime = getRuntimeManager().getRuntimeEngine(EmptyContext.get());
        KieSession ksession = runtime.getKieSession();
        long processInstanceId = -1;

        ProcessInstance processInstance = ksession.startProcess(processName, parameters);

        processInstanceId = processInstance.getId();
        return processInstanceId;
    }
    
    public List<TaskSummary> getMyPotentialTasks(String userId) {
        List<Status> status = new ArrayList<Status>();
        status.add(Status.Ready);
        List<TaskSummary> result = getTaskService().getTasksAssignedAsPotentialOwnerByStatus(userId, status, "en-UK");
            
    	return result;
    }
    public List<TaskSummary> getMyTasks(String userId) {
        List<Status> status = new ArrayList<Status>();
        status.add(Status.Reserved);
        status.add(Status.InProgress);
		List<TaskSummary> result = getTaskService().getTasksOwnedByStatus(userId, status, "en-UK");
            
    	return result;
    }

    public void startTask(String userId, Long taskId) {
    	TaskService taskService = getTaskService();
    	Status statusBefore = taskService.getTaskById(taskId).getTaskData().getStatus();
    	taskService.start(taskId, userId);    
    	Status statusAfter = taskService.getTaskById(taskId).getTaskData().getStatus();
    	log.info("startTask:Changed status for task " + taskId + " from " + statusBefore + " to " + statusAfter);
    }
    public void completeTask(String userId, Long taskId, Map<String,Object> parameters) {
    	TaskService taskService = getTaskService();
    	Status statusBefore = taskService.getTaskById(taskId).getTaskData().getStatus();
    	taskService.complete(taskId, userId, parameters);            
    	Status statusAfter = taskService.getTaskById(taskId).getTaskData().getStatus();
    	log.info("completeTask: Changed status for task " + taskId + " from " + statusBefore + " to " + statusAfter);
    	long processId = taskService.getTaskById(taskId).getTaskData().getProcessInstanceId();
    	InternalTaskService its = (InternalTaskService)taskService;
    	List<Long> tasks = its.getTasksByProcessInstanceId(processId);
    	for(Long tid : tasks) {
    		Task t = taskService.getTaskById(tid);
    		log.info(t.getTaskData().toString());
    	}
    }
    private TaskService getTaskService() {
        RuntimeEngine runtime = getRuntimeManager().getRuntimeEngine(EmptyContext.get());
        return runtime.getTaskService();
    }

	public Status getTaskStatus(long taskId) {
		return getTaskService().getTaskById(taskId).getTaskData().getStatus();
	}
}
