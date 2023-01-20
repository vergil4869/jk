package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.util.UtilFuns;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Dec 19, 2021
 */
@Service
public class ContractProductServiceImpl implements ContractProductService {

	@Resource
	private ContractProductDao contractProductDao;
	@Resource
	private ExtCproductDao extCproductDao;

	@Override
	public List<ContractProduct> findPage(Page page) {
		return contractProductDao.findPage(page);
	}

	@Override
	public List<ContractProduct> find(Map paraMap) {
		return contractProductDao.find(paraMap);
	}

	@Override
	public ContractProduct get(Serializable id) {
		return contractProductDao.get(id);
	}

	@Override
	public void insert(ContractProduct entity) {
		entity.setId(UUID.randomUUID().toString());
		if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
			/*
				 * 自动计算总金额=数量*单价		
				 * ...修改, 删除都应该算这个总金额; 这个功能需要补全
				 * 同时, 不但要算总金额, 还要算合同的总金额; 这个功能需要补全
				 * 
				 * 这里之所以会报空指针, 因为页面中没有填写cnumber的值, 导致getCnumber得到null
				 * 接着编译器自动拆箱, 调用Integer类的intValue(), 即: null.intValue()
				 * 在进你在Integer类打的断点之前, 报错: null pointer
				 */
			entity.setAmount(entity.getCnumber() * (entity.getPrice()));
		}
		contractProductDao.insert(entity);
	}

	@Override
	public void update(ContractProduct entity) {
		contractProductDao.update(entity);
	}

	@Override
	public void delete(Serializable[] ids) {
		/**
		 * 有外键关系, 你必须先删除子表, 再删除主表
		 */
		extCproductDao.deleteByContractProductId(ids);//删除当前这些货物下的所有附件
		contractProductDao.delete(ids);
	}

	@Override
	public void deleteById(Serializable id) {
		extCproductDao.deleteByContractProductId(new Serializable[]{id});//删除当前这些货物下的所有附件
		contractProductDao.deleteById(id);
	}
}
