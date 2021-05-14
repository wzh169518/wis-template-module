package com.wis.template.infrastructure.general.util;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean工具
 *
 * @author wzengheng
 * @since 2021/05/10
 */
public class BeanUtil {

    private static Logger LOG = LoggerFactory.getLogger(BeanUtil.class);

    public static final String CLASS = "class";

    public static <T> T convertBean2Bean(Object from, Class<T> clazz) {
        T res = null;
        try {
            res = clazz.newInstance();
            BeanUtils.copyProperties(from, res);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return res;

    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T map2Bean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 实体转map
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map bean2Map(Object bean) throws Exception {
        Class type = bean.getClass();
        Map returnMap = new HashMap(16);
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!CLASS.equals(propertyName)) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 将Bean对象进行转换
     *
     * @param from
     * @param to
     * @return
     */
    public static Object convertBean2Bean(Object from, Object to) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(to.getClass());
            PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();
            Class<?> classType = from.getClass();
            for (PropertyDescriptor p : ps) {
                Method getMethod = p.getReadMethod();
                Method setMethod = p.getWriteMethod();
                if (getMethod != null && setMethod != null) {
                    try {
                        Method fromGetMethod = classType.getMethod(getMethod.getName());
                        Object result = fromGetMethod.invoke(from);
                        setMethod.invoke(to, result);
                    } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
        } catch (IntrospectionException e) {
            LOG.error(e.getMessage(), e);
        }
        return to;

    }

    /**
     * 将list对象进行转换
     *
     * @param list
     * @param classz
     * @return
     */
    public static <T> List<T> convertList2List(List list, Class<T> classz) {
        List resultList = new ArrayList();
        if (list == null) {
            return resultList;
        }
        for (Object obj : list) {
            T res = convertBean2Bean(obj, classz);
            resultList.add(res);
        }
        return resultList;
    }

    /**
     * 将map的list改为小写的
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> lowerCaseList(List<Map<String, Object>> list) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Map<String, Object> m = new HashMap<>(16);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof List) {
                    List<Object> temp = (List<Object>) value;
                    if (null != temp && !temp.isEmpty()) {
                        if (temp.get(0) instanceof Map) {
                            value = lowerCaseList((List<Map<String, Object>>) value);
                        }
                    }
                }
                if (isFirstUpperCase(entry.getKey())) {
                    m.put(entry.getKey().toLowerCase(), value);
                } else {
                    m.put(entry.getKey(), value);
                }
            }
            result.add(m);
        }
        return result;
    }

    /**
     * 判断字符串的首字母是否大写
     *
     * @param str
     * @return
     */
    public static boolean isFirstUpperCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
            return false;
        }
        return false;
    }
//
//    /**
//     * 将下划线改为驼峰
//     *
//     * @param list
//     * @return
//     */
//    public static List<Map<String, Object>> camelList(List<Map<String, Object>> list) {
//        if (CollectionUtils.isEmpty(list)) {
//            return list;
//        }
//        List<Map<String, Object>> result = new ArrayList<>();
//        for (Map<String, Object> map : list) {
//            Map<String, Object> m = new HashMap<>(16);
//            if (!MapUtils.isEmpty(map)) {
//                for (Map.Entry<String, Object> entry : map.entrySet()) {
//                    Object value = entry.getValue();
//                    if (value instanceof List) {
//                        List<Object> temp = (List<Object>) value;
//                        if (null != temp && !temp.isEmpty()) {
//                            if (temp.get(0) instanceof Map) {
//                                value = camelList((List<Map<String, Object>>) value);
//                            }
//                        }
//                    }
//                    if (entry.getKey().contains("_")) {
//                        m.put(StringUtils.underline2Camel(entry.getKey()), value);
//                    } else {
//                        m.put(entry.getKey(), value);
//                    }
//                }
//            }
//            result.add(m);
//        }
//        return result;
//    }
}
