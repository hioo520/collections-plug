package top.hihuzi.collection.utils;

import top.hihuzi.collection.common.PublicMethod;

import javax.servlet.ServletRequest;
import java.util.*;

/**
 * <p> 通用参数处理 paramter(K v)--转换成 entity
 *
 * <p> 没有缓冲效率比较低(可以用pick 或者fill 工具
 *
 * @author hihuzi 2018/6/14 10:18
 */
public class ParamUtils {

    /**
     * <p> 对请求进行参数进行装填--MAP  空值不保存
     *
     * @param request request
     * @return Map map
     * @author hihuzi 2018/6/14 14:51
     */
    public static Map fill(ServletRequest request) {

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
     * tips 获取类的所有属性包括继承的属性(排除 param)
     *
     * @param obj the obj
     * @return the string [ ]
     * @author hihuzi 2019/3/6 15:51
     */
    public static final String[] fields(Object obj, String... param) {


        Class clazz = PublicMethod.createClazz(obj);
        if (clazz != null) {
            List<String> fields = PublicMethod.fields(clazz, param);
            String[] str = new String[fields.size()];
            return fields.toArray(str);
        } else {
            return null;
        }
    }

    /**
     * <p> ServletRequest--MAP    是否舍弃空值 并且舍弃str特定字段
     *
     * @param request request
     * @param str     str
     * @return Map map
     * @author hihuzi 2018/6/14 14:51
     */
    public Map fill(ServletRequest request, String... str) {

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