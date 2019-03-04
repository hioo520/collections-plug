package javax.servlet;

import java.util.Enumeration;
import java.util.Map;

public interface ServletRequest {


    public Enumeration<String> getParameterNames();

    public Map<String, String[]> getParameterMap();

    public String getParameter(String name);

}
