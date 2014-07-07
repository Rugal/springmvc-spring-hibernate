package rugal.common.springmvc.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This annotation used to binding request parameter
 * 
 * @author Rugal Bernstein
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJsonParam {

	/**
	 * parameter name for binding
     * @return 
	 */
	String value() default "";
	
	/**
	 * binding option, true in default
	 */
	boolean required() default true;

}
