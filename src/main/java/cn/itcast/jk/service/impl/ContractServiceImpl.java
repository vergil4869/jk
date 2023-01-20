package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.vo.ContractVO;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Dec 19, 2021
 */
@Service
public class ContractServiceImpl implements ContractService {

	@Resource
	private ContractDao contractDao;
	@Resource
	private ContractProductDao contractProductDao;
	@Resource
	private ExtCproductDao extCproductDao;

	@Override
	public List<Contract> findPage(Page page) {
		return contractDao.findPage(page);
	}

	@Override
	public List<Contract> find(Map paraMap) {
		return contractDao.find(paraMap);
	}

	@Override
	public Contract get(Serializable id) {
		return contractDao.get(id);
	}

	@Override
	public void insert(Contract entity) {
		entity.setId(UUID.randomUUID().toString());
		entity.setState(0);//0 草稿, 1 已上报
		contractDao.insert(entity);
	}

	@Override
	public void update(Contract entity) {
		contractDao.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		extCproductDao.deleteByContractId(new Serializable[]{id});//删除当前这些合同下的附件信息
		contractProductDao.deleteByContractId(new Serializable[]{id});//删除当前这些合同下的货物信息
		contractDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		extCproductDao.deleteByContractId(ids);//删除当前这些合同下的附件信息
		contractProductDao.deleteByContractId(ids);//删除当前这些合同下的货物信息
		contractDao.delete(ids);
	}

	@Override
	public void submit(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", 1);//1 已上报
		map.put("ids", ids);
		contractDao.updateState(map );
	}

	@Override
	public void cancel(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", 0);//0 草稿
		map.put("ids", ids);
		contractDao.updateState(map);
	}

	@Override
	public ContractVO view(String contractId) {
		return contractDao.view(contractId);
	}
}
