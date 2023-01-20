package cn.itcast.jk.dao;

import java.util.Map;

import cn.itcast.jk.domain.Factory;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
public interface FactoryDao extends BaseDao<Factory>{
	public void updateState(Map map);	//修改状态
}
