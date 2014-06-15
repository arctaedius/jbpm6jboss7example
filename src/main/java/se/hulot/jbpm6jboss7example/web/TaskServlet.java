package se.hulot.jbpm6jboss7example.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kie.api.task.model.TaskSummary;

import se.hulot.jbpm6jboss7example.ejb.TaskLocal;

public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = -3495288996705288613L;

	@EJB
    private TaskLocal taskService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String cmd = req.getParameter("cmd");
        String user = req.getParameter("user");

        if (cmd.equals("list")) {

            List<TaskSummary> taskList;
            try {
                taskList = taskService.retrieveTaskList(user);
            } catch (Exception e) {
                throw new ServletException(e);
            }
            req.setAttribute("taskList", taskList);
            try {
                taskList = taskService.retrievePotentialTaskList(user);
            } catch (Exception e) {
                throw new ServletException(e);
            }
            req.setAttribute("potentialTaskList", taskList);
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/task.jsp");
            dispatcher.forward(req, res);

        } else if (cmd.equals("claim")) {

            String message = "";
            long taskId = Long.parseLong(req.getParameter("taskId"));
            try {
                taskService.claimTask(user, taskId);
                message = "Task (id = " + taskId + ") has been reserved by " + user;
            } catch (Exception e) {
                // Unexpected exception
                throw new ServletException(e);
            }
            req.setAttribute("message", message);
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, res);

	    } else if (cmd.equals("approve")) {
	
	        String message = "";
	        long taskId = Long.parseLong(req.getParameter("taskId"));
	        try {
	            taskService.approveTask(user, taskId);
	            message = "Task (id = " + taskId + ") has been completed by " + user;
	        } catch (Exception e) {
	            // Unexpected exception
	            throw new ServletException(e);
	        }
	        req.setAttribute("message", message);
	        ServletContext context = this.getServletContext();
	        RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
	        dispatcher.forward(req, res);
	
	    }
    }
}