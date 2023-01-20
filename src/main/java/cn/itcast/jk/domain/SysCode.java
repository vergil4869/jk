package cn.itcast.jk.domain;
/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Aug 7, 2022
 */
public class SysCode {
	/**
	 * 咱们目前只需要这几个字段, 因此实体类中只写了这几个字段, 并没有全部映射
	 */
	private String id;
	private Integer orderNo;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
