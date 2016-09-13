package net.zomis.generictype;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GenericTypeReflection {

    private List<String> stringList;
    private Map<String, Number> stringNumberMap;
    private List<Map<Integer, Object>> integerObjectMapList;

    public static void main(String[] args) {
        Class<?> clazz = GenericTypeReflection.class;
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(GenericTypeReflection::printFieldType);
    }

    public static void printFieldType(Field field) {
        System.out.println("Inspecting field " + field.getName() +
            " of field.getType().toString() == " + field.getType().toString() +
            " and field.getGenericType().toString() == " + field.getGenericType().toString());
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterized = (ParameterizedType) type;
            System.out.println(field.getName() + " is a parameterized type! " + getSimpleNameTypes(type));
        } else {
            System.out.println(field.getName() + " is not of a parameterized type. It is: " + field.getType());
        }
    }

    public static String getSimpleNameTypes(Type type) {
        StringBuilder str = new StringBuilder();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterized = (ParameterizedType) type;
            str.append(getSimpleNameTypes(parameterized.getRawType()));
            str.append('<');
            Type[] types = parameterized.getActualTypeArguments();
            for (int i = 0; i < types.length; i++) {
                str.append(getSimpleNameTypes(types[i]));
                if (i < types.length - 1) {
                    str.append(", ");
                }
            }
            str.append('>');
        } else if (type instanceof Class) {
            str.append(((Class) type).getSimpleName());
        } else {
            str.append(type.getTypeName());
        }
        return str.toString();
    }

}
