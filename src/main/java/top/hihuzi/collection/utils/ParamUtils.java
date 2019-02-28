package top.hihuzi.collection.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * tips 通用参数处理 paramter(K v)-->转换成 entity
 *
 * @notice: 没有缓冲效率比较低(可以用pick 或者fill 工具
 * @author: hihuzi  2018/6/14 10:18
 */
public class ParamUtils {

    /**
     * tips 对请求进行参数进行装填-->MAP  空值不保存
     *
     * @author: hihuzi 2018/6/14 14:51
     */
    public static Map fill(HttpServletRequest request) {

        Enumeration pars = request.getParameterNames();
        Map parameter = new HashMap(request.getParameterMap().size());
        while (pars.hasMoreElements()) {
            String name = String.valueOf(pars.nextElement());
            String value = request.getParameter(name);
            if (value != null || "".equals(value)) {
                parameter.put(name, value);
            }

        }
        return parameter;
    }

    /**
     * tips HttpServletRequest-->MAP    是否舍弃空值 并且舍弃str特定字段
     *
     * @author: hihuzi 2018/6/14 14:51
     */
    public Map fill(HttpServletRequest request, String... str) {

        Map map = new HashMap(request.getParameterMap().size() - str.length);
        List<String> exclude = null;
        if (StrUtils.isNNoE(str)) {
            exclude = Arrays.asList(str);
        }
        Enumeration pars = request.getParameterNames();
        while (pars.hasMoreElements()) {
            String name = pars.nextElement().toString();
            String value = request.getParameter(name);
            if (StrUtils.isNNoEC(exclude)) {
                if (!exclude.contains(name)) {
                    if (StrUtils.isNNoE(value)) {
                        map.put(name, value);
                    }
                }
            } else {
                if (StrUtils.isNNoE(value)) {
                    map.put(name, value);
                }

            }
        }
        return map;
    }

}