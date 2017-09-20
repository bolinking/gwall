package gwall.entity;

import java.util.List;

public class PageModel<T> {

	private Integer pageNumber; //��ǰҳ��
    private Integer pageSize;   //һҳ��ʾ����
    private Integer startRow;   //��ѯ��ʼ��
    private Integer total;      //�ܼ�¼����
    private List<T> rows;       //��ѯ�������
    private T queryObj;         //��ѯ����

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void setQueryObj(T queryObj) {
        this.queryObj = queryObj;
    }

    public T getQueryObj() {
        return queryObj;
    }

	public Integer getStartRow() {
		if(pageNumber!=null && pageSize!=null) {
			startRow = (pageNumber - 1) * pageSize;
        } else {
        	startRow = 0;
        }
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
    
}
