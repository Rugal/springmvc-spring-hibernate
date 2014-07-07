package rugal.common.springmvc.method.annotation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerMapping;

/**
 *
 * @author Rugal Bernstein
 */
public abstract class BaseMethodArgumentResolver implements HandlerMethodArgumentResolver
{

    /**
     * Get parameter with specific prefix, including variable/uri/parameter
     *
     * @param namePrefix
     * @param request
     * @param subPrefix
     * @return
     * @subPrefix whether prefix need to truncated
     */
    protected Map<String, String[]> getPrefixParameterMap(String namePrefix,
        NativeWebRequest request, boolean subPrefix)
    {
        Map<String, String[]> result = new HashMap<String, String[]>();

        Map<String, String> variables = getUriTemplateVariables(request);

        int namePrefixLength = namePrefix.length();
        for (String name : variables.keySet())
        {
            if (name.startsWith(namePrefix))
            {
                if (subPrefix)
                {
                    char ch = name.charAt(namePrefix.length());
                    //If next character is not digit, then is not query.
                    if (illegalChar(ch))
                    {
                        continue;
                    }
                    result.put(name.substring(namePrefixLength + 1), new String[]
                    {
                        variables.get(name)
                    });
                } else
                {
                    result.put(name, new String[]
                    {
                        variables.get(name)
                    });
                }
            }
        }

        Iterator<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasNext())
        {
            String name = parameterNames.next();
            if (name.startsWith(namePrefix))
            {
                //page.pn  则截取 pn
                if (subPrefix)
                {
                    char ch = name.charAt(namePrefix.length());
                    if (illegalChar(ch))
                    {
                        continue;
                    }
                    result.put(name.substring(namePrefixLength + 1), request
                        .getParameterValues(name));
                } else
                {
                    result.put(name, request.getParameterValues(name));
                }
            }
        }

        return result;
    }

    private boolean illegalChar(char ch)
    {
        return ch != '.' && ch != '_' && !(ch >= '0' && ch <= '9');
    }

    @SuppressWarnings("unchecked")
    protected final Map<String, String> getUriTemplateVariables(NativeWebRequest request)
    {
        Map<String, String> variables
            = (Map<String, String>) request.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        return (variables != null) ? variables : Collections.<String, String>emptyMap();
    }

}
