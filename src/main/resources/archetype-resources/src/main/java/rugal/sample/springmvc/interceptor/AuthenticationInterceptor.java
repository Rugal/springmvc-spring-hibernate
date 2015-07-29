package rugal.sample.springmvc.interceptor;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import rugal.sample.common.CommonMessageContent;
import rugal.sample.common.Message;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String username = request.getHeader("username");
        String credential = request.getHeader("credential");
        boolean status = true;

        if (!StringUtils.equals(username, "rugal") || !StringUtils.equals(credential, "123456"))
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            String text = new Gson().toJson(Message.failMessage(CommonMessageContent.ACCESS_FORBIDDEN));
            response.getWriter().print(text);
            status = false;
        }
        return status;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
    }

}
