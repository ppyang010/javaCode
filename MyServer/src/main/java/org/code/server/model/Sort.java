package org.code.server.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

class Sort implements Serializable
{
    /**   
	 * @Fields: serialVersionUID  
	 * @Todo: TODO  
	 */ 
	private static final long serialVersionUID = 8427010807649949556L;
	@JsonProperty("SortName")
    public String sortName;
    @JsonProperty("SortType")
    public int sortType;
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public int getSortType() {
		return sortType;
	}
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
    
}