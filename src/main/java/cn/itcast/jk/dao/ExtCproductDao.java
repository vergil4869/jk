package cn.itcast.jk.dao;

import java.io.Serializable;
import java.util.Map;

import cn.itcast.jk.domain.ExtCproduct;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
public interface ExtCproductDao extends BaseDao<ExtCproduct>{

	void deleteByContractProductId(Serializable[] contractProductIds);
	void deleteByContractId(Serializable[] contractId);
}
