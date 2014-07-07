package rugal.sample.common;

/**
 *
 * Message delivery class.
 *
 * @author Rugal Berntein
 */
public class Message
{

    public static Message failMessage(String message)
    {
        return new Message(FAIL, message);
    }

    public static Message successMessage(String message, Object data)
    {
        return new Message(SUCCESS, message, data);
    }

    public static final String SUCCESS = "SUCCESS";

    public static final String FAIL = "FAIL";

    private String status = FAIL;

    private String message = null;

    private Object data = null;

    private Message()
    {
    }

    private Message(String status, String message, Object data)
    {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private Message(String status, String message)
    {
        this(status, message, null);
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

}
