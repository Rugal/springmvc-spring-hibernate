package rugal.sample.common;

/**
 *
 * @author rugal
 */
public interface CommonLogContent
{

    String USER_TRY_ACCESS = "User [{0}] is trying to access [{1}] from host [{2}]";

    String USER_ACCESS_FAILED = "User [{0}] with credential [{1}] failed to access [{2}] from host [{3}]";

    String USER_ACCESS_SUCCEEDED = "User [{0}] access [{1}] from host [{2}] succeeded";
}
