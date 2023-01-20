package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao{
	public ContractDaoImpl() {
		//设置命名空间
		super.setNs("cn.itcast.jk.mapper.ContractMapper");
	}

	@Override
	public void updateState(Map map) {
		this.getSqlSession().update(this.getNs() + ".updateState", map);
		
	}

	@Override
	public ContractVO view(String contractId) {
		return super.getSqlSession().selectOne(this.getNs()+".view", contractId);
	}
}
