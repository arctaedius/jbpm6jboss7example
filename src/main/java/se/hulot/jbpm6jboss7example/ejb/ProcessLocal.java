package se.hulot.jbpm6jboss7example.ejb;

import java.util.Map;

import javax.ejb.Local;

@Local
public interface ProcessLocal {
    public long startProcess(Map<String, Object> params) throws Exception;
}
