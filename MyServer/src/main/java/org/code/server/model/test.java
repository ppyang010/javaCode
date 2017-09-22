package org.code.server.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class test {
	/**   
	 * @Fields: serialVersionUID  
	 * @Todo: TODO  
	 */ 
	private static final long serialVersionUID = -3746831010914352731L;
//	@JsonProperty("ServerName")
    public String ServerName;
//    @JsonProperty("CompanyName")
    public String companyName;
//    @JsonProperty("ParentCategoryId")
    public String parentCategoryId;
//    @JsonProperty("ChildrenCategoryId")
    public String childrenCategoryId;
//    @JsonProperty("LowerPrice")
    public String lowerPrice;
//    @JsonProperty("HigherPrice")
    public String higherPrice;
    
	public String getServerName() {
		return ServerName;
	}
	public void setServerName(String serverName) {
		ServerName = serverName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public String getChildrenCategoryId() {
		return childrenCategoryId;
	}
	public void setChildrenCategoryId(String childrenCategoryId) {
		this.childrenCategoryId = childrenCategoryId;
	}
	public String getLowerPrice() {
		return lowerPrice;
	}
	public void setLowerPrice(String lowerPrice) {
		this.lowerPrice = lowerPrice;
	}
	public String getHigherPrice() {
		return higherPrice;
	}
	public void setHigherPrice(String higherPrice) {
		this.higherPrice = higherPrice;
	}
}
