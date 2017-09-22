package org.code.server.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;


public class Example implements Serializable
{
	private static final long serialVersionUID = 440524843024655412L;
	public int start;
    public int limit;
    public String bigCategoryId;
    public test filter;
   /* public Sort sort;*/
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getBigCategoryId() {
		return bigCategoryId;
	}
	public void setBigCategoryId(String bigCategoryId) {
		this.bigCategoryId = bigCategoryId;
	}
	public test getFilter() {
		return filter;
	}
	public void setFilter(test filter) {
		this.filter = filter;
	}
	
	
	
/*	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
    */
}




