package cn.itcast.jk.dao;

import java.io.Serializable;
import java.util.Map;

import cn.itcast.jk.domain.ContractProduct;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
public interface ContractProductDao extends BaseDao<ContractProduct>{
	void deleteByContractId(Serializable[] ids);
}
