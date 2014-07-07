package rugal.common.springmvc.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binding request parameter into mode and expose them with view<BR/>
 * Different from <code>@ModelAttribute</code>
 *
 * @author Rugal Bernstein
 *
 */
@Target(
    {
        ElementType.PARAMETER
    })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormModel
{

    /**
     * specify the prefix name of request parameter and the model for exposing into view
     *
     * <p>
     * 1. binding rule<br/>
     * if form is：<br>
     * <pre class="code">
     * <input name="student.name" value="Kate" /><br>
     * <input name="student.type" value="bachelor" /><br>
     * </pre> then process like：<br/>
     * <pre class="code">
     *
     * @return
     * @RequestMapping(value = "/test") public String test(@FormModel("student") Student student) //this will bind into
     * student.name student.type
     *
     */
    String value();

}
