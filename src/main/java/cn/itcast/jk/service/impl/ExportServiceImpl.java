package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.dao.ExportProductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.ExportProduct;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.vo.ContractVO;
import cn.itcast.util.UtilFuns;
import sun.org.mozilla.javascript.internal.ast.ForInLoop;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Dec 19, 2021
 */
@Service
public class ExportServiceImpl implements ExportService {
	@Resource
	ExportDao exportDao;
	@Resource
	ExportProductDao exportProductDao;
	@Resource
	ContractDao contractDao;

	@Override
	public List<Export> findPage(Page page) {
		return exportDao.findPage(page);
	}

	@Override
	public List<Export> find(Map paraMap) {
		return exportDao.find(paraMap);
	}

	@Override
	public Export get(Serializable id) {
		return exportDao.get(id);
	}

	@Override
	public void insert(String... contractIds) {
		/**
		 * 步骤: 
		 * 1. 根据合同id获取合同对象, 获取合同号
		 * 2. 将合同下的货物信息搬家到报运下的货物表中
		 * 3. 将合同下的附件信息搬家到报运下的附件表中
		 */
		
		//拼接合同号, 报运号
		StringBuilder contractNo = new StringBuilder();
		for(int i=0; i<contractIds.length; i++) {
			ContractVO contract = contractDao.view(contractIds[i]);
			contractNo.append(contract.getContractNo())
			.append(" ");//以空格作为分隔符
			
			List<ContractProduct> products = contract.getContractProducts();
		}
		String contractNoStr = UtilFuns.delLastChar(contractNo.toString());//删除最后一个字符
		
		Export export = new Export();
		export.setExportId(UUID.randomUUID().toString());
		export.setContractIds(UtilFuns.joinStr(contractIds, ","));//工具类, 拼串
		export.setCustomerContract(contractNoStr);

		exportDao.insert(export);
		
		//处理货物信息
		for (int i = 0; i < contractIds.length; i++) {
			ExportProduct exportProduct = new ExportProduct();
			exportProduct.setExportId(export.getExportId());
			
			ContractVO contractVO = contractDao.view(contractIds[i]);
			for (ContractProduct contract : contractVO.getContractProducts()) {
				
			}
			
			//数据搬家, 将合同下的对应的货物信息写到报运下的货物信息中
			exportProduct.setFactoryId(factoryId);
			
			exportProductDao.insert(ep);
		}			
	}

	@Override
	public void update(Export entity) {
		exportDao.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		exportDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		exportDao.delete(ids);
	}

	@Override
	public void submit(Serializable[] ids) {
		Map map = new HashMap();
		map.put("ids", ids);
		map.put("state", 1);
		exportDao.updateState(map);
	}

	@Override
	public void cancel(Serializable[] ids) {
		Map map = new HashMap();
		map.put("ids", ids);
		map.put("state", 0);
		exportDao.updateState(map);
	}

	@Override
	public List<Contract> getContractList() {
		Map paraMap = new HashMap();
		paraMap.put("state", 1);//1:已上报
		List<Contract> contractList = contractDao.find(paraMap);
		return contractList;
	}
}
