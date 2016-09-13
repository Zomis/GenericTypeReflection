# GenericTypeReflection
Small demonstration of how to get the generic type arguments of a field with reflection

The important things are:

    Field field; // a field that you get through java.lang.reflect, for example with `object.getClass().getDeclaredFields()`
    Type type = field.getGenericType();
    if (type instanceof ParameterizedType) {
        ParameterizedType parameterized = (ParameterizedType) type;
        Type[] types = parameterized.getActualTypeArguments();
    }

Remember that `Class extends Type`, so your resulting `Type` objects may be for example `String.class`
