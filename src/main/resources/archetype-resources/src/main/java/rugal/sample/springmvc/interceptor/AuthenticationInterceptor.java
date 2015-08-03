package rugal.sample.springmvc.interceptor;

import com.google.gson.Gson;
import java.io.IOException;
import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ml.rugal.sshcommon.springmvc.util.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import rugal.sample.common.CommonLogContent;
import rugal.sample.common.CommonMessageContent;
import rugal.sample.common.SystemDefaultProperties;

/**
 *
 * A authentication interceptor than authenticate any matched request by some
 * credential. Store username and credential in request header.
 * <p>
 * Please implement your own authentication class to ensure the right method you
 * need to verify access.
 * <p>
 * Useful when implementing Restful API.
 * <p>
 * @author Rugal Bernstein
 * @since 0.6
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor
{

    private final Gson gson = new Gson();

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String id = request.getHeader(SystemDefaultProperties.ID);
        String credential = request.getHeader(SystemDefaultProperties.CREDENTIAL);
        boolean status = true;
        LOG.info(MessageFormat.format(CommonLogContent.USER_TRY_ACCESS,
                                      id,
                                      request.getRequestURI(),
                                      request.getRemoteAddr()));

        if (!isAuthenticatedUser(id, credential))
        {
            status = false;
            forbiddenResponse(response);
            LOG.warn(MessageFormat.format(CommonLogContent.USER_ACCESS_FAILED,
                                          id,
                                          credential,
                                          request.getRequestURI(),
                                          request.getRemoteAddr()));
        }
        return status;
    }

    /**
     * This method is just for generating a response with forbidden content.<BR>
     * May throw IOException inside because unable to get response body writer,
     * but this version will shelter it.
     *
     *
     * @param response The response corresponding to the request.
     */
    private void forbiddenResponse(HttpServletResponse response)
    {
        try
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().print(gson.toJson(Message.failMessage(CommonMessageContent.ACCESS_FORBIDDEN)));

        }
        catch (IOException e)
        {
            LOG.error("Unable to get response writer", e);
        }

    }

    /**
     * This method used put authentication.
     * If you need to check with database, please modify code.
     *
     * @param username   user ID
     * @param credential user password
     *
     * @return true if this user and credential meet requirement, otherwise
     *         return false
     */
    private boolean isAuthenticatedUser(String username, String credential)
    {
        return StringUtils.equals(username, "rugal") && StringUtils.equals(credential, "123456");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        LOG.info(MessageFormat.format(CommonLogContent.USER_ACCESS_SUCCEEDED,
                                      request.getHeader(SystemDefaultProperties.ID),
                                      request.getRequestURI(),
                                      request.getRemoteAddr()));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
    }

}
