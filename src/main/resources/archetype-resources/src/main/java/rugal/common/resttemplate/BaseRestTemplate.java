package rugal.common.resttemplate;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Rugal Bernstein
 */
public abstract class BaseRestTemplate
{

    @Autowired
    private RestTemplate restTemplate;

    //temporary use this fixed ACK, will use dynamic ACK in the future
    private final String ACK = "";

    /**
     * This method used to get or generate ACK code, I will address it in a ACK generator.
     *
     * @return Given real ACK code for BD developer.
     */
    protected final String getACK()
    {
        return ACK;
    }

    /**
     * This method inherited by sub class, set parameter for query.
     * If unable to generate URL to request, will return NULL.
     *
     * @param <T>
     * @param parameters
     * @param responseType set response type to reflection.
     * @return return specified responseType object, automatically reflect from string; or return null if unable to generate URL to request
     */
    protected <T> T execute(Map<String, String> parameters, Class<T> responseType)
    {
        String url = requestURL(parameters);
        if (StringUtils.isEmpty(url))
        {
            return null;
        }
        return restTemplate.getForObject(url, responseType);
    }

    /**
     * Assemble full URL on a base URL inherited from this class.
     * Automatically append `ak` parameter in this method.
     *
     * @param parameters all parameter
     * @return full URL that could use to make Restful request
     */
    private String requestURL(Map<String, String> parameters)
    {
        StringBuilder baseURL = new StringBuilder(getBaseURL());
        if (StringUtils.isEmpty(baseURL.toString()))
        {
            return null;
        }
        for (Map.Entry<String, String> entry : parameters.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            baseURL.append("&").append(key).append("=").append(value);
        }
        baseURL.append("&").append("ak").append("=").append(getACK());
        return baseURL.toString();
    }

    /**
     * Return base URL for each service.
     *
     * @return A base URL for each service from BD, & not allowed at tail position.<BR/>
     * Also, base URL do not need to includes `ak` parameter
     */
    protected abstract String getBaseURL();
}
