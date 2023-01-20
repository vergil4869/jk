package cn.itcast.jk.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.OutproductDao;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.OutproductVO;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Aug 22, 2022
 */
@Repository
public class OutproductDaoImpl extends BaseDaoImpl<OutproductVO> implements OutproductDao {
	public OutproductDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.OutproductMapper");
	}

}
