package se.hulot.jbpm6jboss7example.ejb;

import java.util.List;

import javax.ejb.Local;

import org.kie.api.task.model.TaskSummary;

@Local
public interface TaskLocal {
    List<TaskSummary> retrievePotentialTaskList(String actorId) throws Exception;
    List<TaskSummary> retrieveTaskList(String actorId) throws Exception;
    void claimTask(String actorId, long taskId) throws Exception;
    void approveTask(String actorId, long taskId) throws Exception;
}
