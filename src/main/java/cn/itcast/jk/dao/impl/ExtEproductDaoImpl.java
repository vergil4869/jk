package cn.itcast.jk.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExtEproductDao;
import cn.itcast.jk.domain.ExtEproduct;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Sep 2, 2022
 */
@Repository
public class ExtEproductDaoImpl extends BaseDaoImpl<ExtEproduct> implements ExtEproductDao{
	public ExtEproductDaoImpl() {
		setNs("cn.itcast.jk.mapper.ExtEproductMapper");
	}
}
