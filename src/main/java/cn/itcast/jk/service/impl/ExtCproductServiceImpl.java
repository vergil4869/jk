package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.dao.SysCodeDao;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.SysCode;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.util.UtilFuns;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Dec 19, 2021
 */
@Service
public class ExtCproductServiceImpl implements ExtCproductService {

	@Resource
	private ExtCproductDao extCproductDao;
	@Resource
	SysCodeDao sysCodeDao;

	@Override
	public List<ExtCproduct> findPage(Page page) {
		return extCproductDao.findPage(page);
	}

	@Override
	public List<ExtCproduct> find(Map paraMap) {
		return extCproductDao.find(paraMap);
	}

	@Override
	public ExtCproduct get(Serializable id) {
		return extCproductDao.get(id);
	}

	@Override
	public void insert(ExtCproduct entity) {
		entity.setId(UUID.randomUUID().toString());
		if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
			entity.setAmount(entity.getCnumber() * (entity.getPrice()));
		}
		extCproductDao.insert(entity);
	}

	@Override
	public void update(ExtCproduct entity) {
		extCproductDao.update(entity);
	}

	@Override
	public void delete(Serializable[] ids) {
		extCproductDao.delete(ids);
	}

	@Override
	public void deleteById(Serializable id) {
		extCproductDao.deleteById(id);
	}

	@Override
	public List<SysCode> getCtypeList() {
		
		Map paraMap = new HashMap();
		paraMap.put("parentId", "0104");//0104代表 附件分类
		return sysCodeDao.find(paraMap );
	}
}
