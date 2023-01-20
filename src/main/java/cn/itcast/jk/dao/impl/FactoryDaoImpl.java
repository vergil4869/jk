package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao{
	public FactoryDaoImpl() {
		//设置命名空间
		super.setNs("cn.itcast.jk.mapper.FactoryMapper");
	}

	@Override
	public void updateState(Map map) {
		this.getSqlSession().update(this.getNs() + ".updateState", map);
		
	}
}
