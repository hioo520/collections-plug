package javax.servlet;

import java.util.Enumeration;
import java.util.Map;

/**
 * @author: hihuzi 2019/3/4 17:28
 */
public interface ServletRequest {


    public Enumeration<String> getParameterNames();

    public Map<String, String[]> getParameterMap();

    public String getParameter(String name);

}
