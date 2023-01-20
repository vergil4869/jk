package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.FactoryService;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
@Service
public class FactoryServiceImpl implements FactoryService {
	@Resource
	FactoryDao factoryDao;

	@Override
	public List<Factory> findPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Factory> find(Map paraMap) {
		// TODO Auto-generated method stub
		return factoryDao.find(paraMap);
	}

	@Override
	public Factory get(Serializable id) {
		return factoryDao.get(id);
	}

	@Override
	public void insert(Factory entity) {
		entity.setId(UUID.randomUUID().toString());		//设置UUID
		entity.setState("1");	//默认为启用状态
		factoryDao.insert(entity);

	}

	@Override
	public void update(Factory entity) {
		factoryDao.update(entity);

	}

	@Override
	public void deleteById(Serializable id) {
		factoryDao.deleteById(id);

	}

	@Override
	public void delete(Serializable[] ids) {
		factoryDao.delete(ids);

	}

	@Override
	public void start(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", 1);	//1启用
		map.put("ids", ids);
		factoryDao.updateState(map);
	}

	@Override
	public void stop(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", 0);	//0停用
		map.put("ids", ids);
		factoryDao.updateState(map);
		
	}

	@Override
	public List<Factory> getFactoryList() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("state", 1);//1 启用, 代表只查询启用的生产厂家
		
		return find(paraMap);
	}

}
