package rugal.common.page;

/**
 *
 * @author Rugal Bernstein
 */
public class SimplePage implements Paginable
{

    private static final long serialVersionUID = 1L;

    public static final int DEF_COUNT = 20;

    /**
     * check the page number and give it valid number.
     *
     * @param pageNo
     * @return return 1 if page number is null or less than 1; Or just return the page number.
     */
    public static int cpn(Integer pageNo)
    {
        return (pageNo == null || pageNo < 1) ? 1 : pageNo;
    }

    public SimplePage()
    {
    }

    /**
     *
     * @param pageNo
     * @param pageSize
     * @param totalCount
     */
    public SimplePage(int pageNo, int pageSize, int totalCount)
    {
        setTotalCount(totalCount);
        setPageSize(pageSize);
        setPageNo(pageNo);
        adjustPageNo();
    }

    /**
     * Adjust page number if it is not accurate
     */
    public void adjustPageNo()
    {
        if (pageNo == 1)
        {
            return;
        }
        int tp = getTotalPage();
        if (pageNo > tp)
        {
            pageNo = tp;
        }
    }

    @Override
    public int getPageNo()
    {
        return pageNo;
    }

    @Override
    public int getPageSize()
    {
        return pageSize;
    }

    @Override
    public int getTotalCount()
    {
        return totalCount;
    }

    /**
     * Get the total page number of a query.
     * to see total row number divided by page size per page.
     *
     * @return get total page a query could have.
     */
    @Override
    public int getTotalPage()
    {
        int totalPage = totalCount / pageSize;
        if (totalPage == 0 || totalCount % pageSize != 0)
        {
            totalPage++;
        }
        return totalPage;
    }

    /**
     * Judge if this is the first(1) page.
     */
    @Override
    public boolean isFirstPage()
    {
        return pageNo <= 1;
    }

    /**
     * To see if current page is the last page if page number exceeded total page number.
     */
    @Override
    public boolean isLastPage()
    {
        return pageNo >= getTotalPage();
    }

    /**
     *
     * Get next page number, if current page is the last one just return current one.
     *
     * @return given page number calculated
     */
    @Override
    public int getNextPage()
    {
        if (isLastPage())
        {
            return pageNo;
        } else
        {
            return pageNo + 1;
        }
    }

    /**
     *
     * Get previous page number, if current page is the first one just return current one.
     *
     * @return given page number calculated
     */
    @Override
    public int getPrePage()
    {
        if (isFirstPage())
        {
            return pageNo;
        } else
        {
            return pageNo - 1;
        }
    }

    protected int totalCount = 0;

    protected int pageSize = 20;

    protected int pageNo = 1;

    /**
     * To set total count for a query.
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount)
    {
        if (totalCount < 0)
        {
            this.totalCount = 0;
        } else
        {
            this.totalCount = totalCount;
        }
    }

    /**
     * set page size to query, start from 1, will use default page size of 20 if parameter less than 1..
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize)
    {
        if (pageSize < 1)
        {
            this.pageSize = DEF_COUNT;
        } else
        {
            this.pageSize = pageSize;
        }
    }

    /**
     *
     * Set page number for this query, if given parameter less than 1, adjust it to 1.
     *
     * @param pageNo
     */
    public void setPageNo(int pageNo)
    {
        if (pageNo < 1)
        {
            this.pageNo = 1;
        } else
        {
            this.pageNo = pageNo;
        }
    }
}
