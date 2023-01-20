package cn.itcast.jk.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ExtCproduct;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao{
	public ExtCproductDaoImpl() {
		//设置命名空间
		super.setNs("cn.itcast.jk.mapper.ExtCproductMapper");
	}
	@Override
	public void deleteByContractProductId(Serializable[] contractProductIds) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractProductId", contractProductIds);
	}
	@Override
	public void deleteByContractId(Serializable[] contractIds) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractId", contractIds);
	}
}
