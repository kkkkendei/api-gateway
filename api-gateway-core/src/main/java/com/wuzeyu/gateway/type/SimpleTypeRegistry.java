package com.wuzeyu.gateway.type;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wuzeyu
 * @description 基本类型注册器
 * @github github.com/kkkkendei
 */
public class SimpleTypeRegistry {

    private static final Set<String> SIMPLE_TYPE_SET = new HashSet<>();

    static {
        SIMPLE_TYPE_SET.add(String.class.getName());
        SIMPLE_TYPE_SET.add(Byte.class.getName());
        SIMPLE_TYPE_SET.add(Short.class.getName());
        SIMPLE_TYPE_SET.add(Character.class.getName());
        SIMPLE_TYPE_SET.add(Integer.class.getName());
        SIMPLE_TYPE_SET.add(Long.class.getName());
        SIMPLE_TYPE_SET.add(Float.class.getName());
        SIMPLE_TYPE_SET.add(Double.class.getName());
        SIMPLE_TYPE_SET.add(Boolean.class.getName());
        SIMPLE_TYPE_SET.add(Date.class.getName());
        SIMPLE_TYPE_SET.add(Class.class.getName());
        SIMPLE_TYPE_SET.add(BigInteger.class.getName());
        SIMPLE_TYPE_SET.add(BigDecimal.class.getName());
    }

    private SimpleTypeRegistry() {

    }

    public static boolean isSimpleType(String clazz) {
        return SIMPLE_TYPE_SET.contains(clazz);
    }

}
