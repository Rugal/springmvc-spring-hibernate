package rugal.sample.core.service;

/**
 *
 * @author Rugal Bernstein
 * @param <DAO>
 */
public interface BaseService<DAO>
{

    DAO getDAO();
}
