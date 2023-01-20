package cn.itcast.jk.dao;

import java.util.Map;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
public interface ContractDao extends BaseDao<Contract>{
	public void updateState(Map map);	//修改状态
	ContractVO view(String contractId);//查询某个合同
}
