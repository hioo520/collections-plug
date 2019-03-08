package javax.servlet;

import java.util.Enumeration;
import java.util.Map;

/**
 * The interface Servlet request.
 *
 * @author hihuzi 2019/3/4 17:28
 */
public interface ServletRequest {


    /**
     * Gets parameter names.
     *
     * @return the parameter names
     */
    public Enumeration<String> getParameterNames();

    /**
     * Gets parameter map.
     *
     * @return the parameter map
     */
    public Map<String, String[]> getParameterMap();

    /**
     * Gets parameter.
     *
     * @param name the name
     * @return the parameter
     */
    public String getParameter(String name);

}
