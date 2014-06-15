/**
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.hulot.jbpm6jboss7example.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
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
