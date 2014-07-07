package rugal.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Locale;
import org.springframework.util.Assert;

/**
 * Bean utility to update bean without getter and setter.
 *
 * @author Rugal Bernstein
 */
public class BeanUtils
{

    /**
     * Get field value from bean without hinder of access.
     *
     * @param object
     * @param fieldName
     *
     * @return
     */
    public static Object getFieldValue(final Object object, final String fieldName)
    {
        Field field = getDeclaredField(object, fieldName);

        if (field == null)
        {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        Object result = null;

        try
        {
            result = field.get(object);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException("never happend exception!", e);
        }

        return result;
    }

    /**
     * Set field value into bean without hinder of access.
     *
     * @param object    original to be updated
     * @param fieldName field name to be updated.
     * @param value     value to set
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value)
    {
        Field field = getDeclaredField(object, fieldName);

        if (field == null)
        {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        try
        {
            field.set(object, value);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException("never happend exception!", e);
        }
    }

    /**
     * Get specific field from a object.
     *
     * @param object    to reflect as object
     * @param fieldName
     * @return
     */
    protected static Field getDeclaredField(final Object object, final String fieldName)
    {
        Assert.notNull(object);

        return getDeclaredField(object.getClass(), fieldName);
    }

    /**
     * Get specific field from a class.
     *
     * @param clazz     class to reflect from
     * @param fieldName
     * @return
     */
    protected static Field getDeclaredField(final Class clazz, final String fieldName)
    {
        Assert.notNull(clazz);
        Assert.hasText(fieldName);

        //traverse all declared field from inheritence
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass
            .getSuperclass())
        {
            try
            {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException exception)
            {
                //But it is fair well to have this exception.
            }
        }
        return null;
    }

    /**
     * Make a field accessible.
     *
     * @param field This is target field accessible
     */
    protected static void makeAccessible(final Field field)
    {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass()
            .getModifiers()))
        {
            field.setAccessible(true);
        }
    }

    /**
     * This method used to get property or field value by its getter.
     *
     * @param bean     target bean.
     * @param propName the property to fetch.
     *
     * @return This is value of property.
     *
     * @throws IllegalAccessException    if unable to access this field
     * @throws IllegalArgumentException  if argument is illegal
     * @throws InvocationTargetException if unable to invoke
     * @throws NoSuchMethodException     if no such method in class definition
     * @throws SecurityException
     */
    public static Object getSimpleProperty(Object bean, String propName)
        throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
               NoSuchMethodException
    {
        return bean.getClass().getMethod(getReadMethod(propName)).invoke(bean);
    }

    /**
     * This method is to generate getter method name.
     *
     * @param name this is field name.
     * @return this is name of getter method
     */
    private static String getReadMethod(String name)
    {
        return "get" + name.substring(0, 1).toUpperCase(Locale.ENGLISH) + name.substring(1);
    }
}
