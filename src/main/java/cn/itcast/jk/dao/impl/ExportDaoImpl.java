package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.domain.Export;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
@Repository
public class ExportDaoImpl extends BaseDaoImpl<Export> implements ExportDao{
	public ExportDaoImpl() {
		//设置命名空间
		super.setNs("cn.itcast.jk.mapper.ExportMapper");
	}

	@Override
	public void updateState(Map map) {
		this.getSqlSession().update(this.getNs() + ".updateState", map);
		
	}
}
