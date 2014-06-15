package se.hulot.jbpm6jboss7example.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.hulot.jbpm6jboss7example.domain.Message;
import se.hulot.jbpm6jboss7example.ejb.MessageService;
import se.hulot.jbpm6jboss7example.ejb.ProcessLocal;

public class ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = -6637792492207480164L;

	@EJB
    private ProcessLocal processService;

	@EJB
    private MessageService messageService;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        long processInstanceId = -1;
        try {
        	Message message = messageService.createMessage("hallo");
        	Map<String, Object> params = new HashMap<String, Object>();
        	params.put("messageId", message.getId().toString());
         	processInstanceId = processService.startProcess(params);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        req.setAttribute("message", "process instance (id = "
                + processInstanceId + ") has been started.");

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context
                .getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, res);
    }
}