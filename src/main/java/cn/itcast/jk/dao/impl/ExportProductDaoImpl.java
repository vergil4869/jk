package cn.itcast.jk.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExportProductDao;
import cn.itcast.jk.domain.ExportProduct;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Sep 2, 2022
 */
@Repository
public class ExportProductDaoImpl extends BaseDaoImpl<ExportProduct> implements ExportProductDao{
	public ExportProductDaoImpl() {
		setNs("cn.itcast.jk.mapper.ExportProductMapper");
	}
}
