package cn.itcast.jk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.OutproductDao;
import cn.itcast.jk.service.OutproductService;
import cn.itcast.jk.vo.OutproductVO;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Aug 22, 2022
 */
@Service
public class OutproductServiceImpl implements OutproductService {
	@Resource
	private OutproductDao outproductDao;

	@Override
	public List<OutproductVO> find(String inputDate) {
		Map paraMap = new HashMap();
		paraMap.put("inputDate", inputDate);
		return outproductDao.find(paraMap);
	}

}
