package rugal.sample.controller;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import rugal.sample.common.Message;

/**
 * This is the fundamental exception and HTTP error code mapping class.
 *
 * @author Rugal Bernstein
 */
@ControllerAdvice
@Controller
public class ExceptionAction
{

    private static final String BAD_REQUEST = "400 (Bad Request)";

    private static final String NOT_FOUND = "404 (Not Found)";

    private static final String METHOD_NOT_ALLOWED = "405 (Method Not Allowed)";

    private static final String NOT_ACCEPTABLE = "406 (Not Acceptable)";

    private static final String UNSUPPORTED_MEDIA_TYPE = "415 (Unsupported Media Type)";

    private static final String INTERNAL_SERVER_ERROR = "500 (Internal Server Error)";

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAction.class.getName());

    /**
     *
     * This method is to address no handler request, throw exception into 404 exception advisor
     *
     * @param request
     * @return
     * @throws org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException
     */
    @RequestMapping("/**")
    @ResponseBody
    public Message PathNotFoundHandler(HttpServletRequest request) throws NoSuchRequestHandlingMethodException
    {
        LOG
            .warn(MessageFormat
                .format("{0} occured, request URL: {1}, request host: {2}", NOT_FOUND, request
                    .getRequestURI(), request.getRemoteAddr()));
        throw new NoSuchRequestHandlingMethodException(request);
    }

    /**
     * Mapping for 400
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(
        {
            BindException.class, HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class, MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class, TypeMismatchException.class
        })
    public Message badRequest(HttpServletRequest req, Exception e)
    {
        LOG.error(e.getMessage(), e);
        return Message.failMessage(BAD_REQUEST);
    }

    /**
     * Mapping for 404
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(
        {
            NoHandlerFoundException.class, NoSuchRequestHandlingMethodException.class
        })
    public Message notFound(HttpServletRequest req, Exception e)
    {
        LOG.error(e.getMessage(), e);
        return Message.failMessage(NOT_FOUND);
    }

    /**
     * Mapping for 405
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(
        {
            HttpRequestMethodNotSupportedException.class
        })
    public Message methodNotAllowed(HttpServletRequest req, Exception e)
    {
        LOG.error(e.getMessage(), e);
        return Message.failMessage(METHOD_NOT_ALLOWED);
    }

    /**
     * Mapping for 406
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(
        {
            HttpMediaTypeNotAcceptableException.class
        })
    public Message notAcceptable(HttpServletRequest req, Exception e)
    {
        LOG.error(e.getMessage(), e);
        return Message.failMessage(NOT_ACCEPTABLE);
    }

    /**
     * Mapping for 415
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(
        {
            HttpMediaTypeNotSupportedException.class
        })
    public Message unsupportedMediaType(HttpServletRequest req, Exception e)
    {
        LOG.error(e.getMessage(), e);
        return Message.failMessage(UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Mapping for 500
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(
        {
            ConversionNotSupportedException.class, HttpMessageNotWritableException.class,
        })
    public Message internalServerError(HttpServletRequest req, Exception e)
    {
        LOG.error(e.getMessage(), e);
        return Message.failMessage(INTERNAL_SERVER_ERROR);
    }

    /**
     * Mapping for other exceptions
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(
        {
            Exception.class
        })
    public Message otherException(HttpServletRequest req, Exception e)
    {
        LOG.error("Other exception", e);
        return Message.failMessage(e.getMessage());
    }
}
