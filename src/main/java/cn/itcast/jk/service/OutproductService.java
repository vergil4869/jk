package cn.itcast.jk.service;

import java.util.List;

import cn.itcast.jk.vo.OutproductVO;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
public interface OutproductService {
	List<OutproductVO> find(String inputDate);
}
