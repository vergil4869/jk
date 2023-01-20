package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.pagination.Page;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
public interface FactoryService {
	public List<Factory> findPage(Page page);	//分页查询
	public List<Factory> find(Map paraMap);	//带条件查询，条件可以为null，即,没有条件；返回list对象集合
	public Factory get(Serializable id);	//只查询一个，常用于修改
	public void insert(Factory entity);	//插入，用实体作为参数
	public void update(Factory entity);	//修改，用实体作为参数
	public void deleteById(Serializable id);	//按id删除，删除一条；支持整数型和字符串类型ID
	public void delete(Serializable[] ids);	//批量删除；支持整数型和字符串类型ID
	
	//启用, state参数已经在这一层固化下来, 因此我们只需要接收ids即可, 就不用再接收map了
	public void start(Serializable[] ids);
	public void stop(Serializable[] ids);	//停用
	public List<Factory> getFactoryList();
}
