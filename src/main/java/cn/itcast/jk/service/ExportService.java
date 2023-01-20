package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.pagination.Page;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
public interface ExportService {
	public List<Export> findPage(Page page);	//分页查询
	public List<Export> find(Map paraMap);	//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public Export get(Serializable id);	//只查询一个，常用于修改
	
	public void insert(String... contractIds);
	public void update(Export entity);	//修改，用实体作为参数
	@Deprecated//实际上我们以后只用批量删除的methodb了
	public void deleteById(Serializable id);	//按id删除，删除一条；支持整数型和字符串类型ID
	public void delete(Serializable[] ids);	//批量删除；支持整数型和字符串类型ID
	
	//启用, state参数已经在这一层固化下来, 因此我们只需要接收ids即可, 就不用再接收map了
	public void submit(Serializable[] ids);//上报
	public void cancel(Serializable[] ids);	//取消
	
	List<Contract> getContractList();//获取购销合同列表(已上报的)
}
