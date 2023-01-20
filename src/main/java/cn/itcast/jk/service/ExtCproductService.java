package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.SysCode;
import cn.itcast.jk.pagination.Page;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
public interface ExtCproductService {
	public List<ExtCproduct> findPage(Page page);	//分页查询
	public List<ExtCproduct> find(Map paraMap);	//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public ExtCproduct get(Serializable id);	//只查询一个，常用于修改
	public void insert(ExtCproduct entity);	//插入，用实体作为参数
	public void update(ExtCproduct entity);	//修改，用实体作为参数
	public void delete(Serializable[] ids);	//批量删除；支持整数型和字符串类型ID
	public void deleteById(Serializable id);//支持整型和字符型的id
	
	List<SysCode> getCtypeList();//获取分类列表
}
