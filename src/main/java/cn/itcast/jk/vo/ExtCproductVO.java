package cn.itcast.jk.vo;

import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;

/**
 * @Description: 合同货物 下的 合同附件
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Jan 1, 2022
 */
public class ExtCproductVO {
	private String id;
 	
	private ContractProduct contractProduct;
	private Factory factory;
	
//	private String factoryName;//冗余字段也不需要了
	private String cType;
	private String productNo;
	/**
	 * 实际上附件是可以不用提供照片的, 但是预留着有没有关系? 没有, 所以这个一般
	 * 我们都是留着
	 */
	private String productImage;
	private String productDesc;
	private Integer cnumber;//附件再便宜, 那张包装纸也得跟你要钱那
//	private Integer outNumber;//没有出货概念
	private String packingUnit;
	private Double price;
	private Double amount;
	private String productRequest;
	private Integer orderNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getcType() {
		return cType;
	}
	public void setcType(String cType) {
		this.cType = cType;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Integer getCnumber() {
		return cnumber;
	}
	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getProductRequest() {
		return productRequest;
	}
	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public ContractProduct getContractProduct() {
		return contractProduct;
	}
	public void setContractProduct(ContractProduct contractProduct) {
		this.contractProduct = contractProduct;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	
}