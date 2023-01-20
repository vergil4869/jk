package cn.itcast.jk.controller.cargo.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.domain.SysCode;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.jk.service.FactoryService;

/**
 * @Description: 
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Jan 2, 2022
 */
@Controller
public class ExtCproductController {
	@Resource
	private ExtCproductService extCproductService;
	@Resource
	private FactoryService factoryService;

	//转向新增页面
	@RequestMapping("/cargo/extCproduct/toCreate.action")
	public String toCreate(String contractProductId, Model model) {
		model.addAttribute("contractProductId", contractProductId);//传递主表的id

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("state", 1);
		List<Factory> factoryList = factoryService.find(paraMap );
		model.addAttribute("factoryList", factoryList);
		
		//准备分类下拉列表
		List<SysCode> ctypeList = extCproductService.getCtypeList();
		model.addAttribute("ctypeList", ctypeList);
		
		//准备数据
		paraMap.put("contractProductId", contractProductId);
		List<ExtCproduct> productList = extCproductService.find(paraMap);
		model.addAttribute("dataList", productList);
		return "/cargo/contract/jExtCproductCreate.jsp";
	}
	
	@RequestMapping("/cargo/extCproduct/insert.action")
	public String insert(ExtCproduct extCproduct, Model model) {
		extCproductService.insert(extCproduct);
		/**
		 * 子表永远都要携带他的主表id
		 */
		model.addAttribute("contractProductId", extCproduct.getContractProductId());
		return "redirect:/cargo/extCproduct/toCreate.action";
	}
	
	//转向修改页面
	@RequestMapping("/cargo/extCproduct/toUpdate.action")
	public String toUpdate(String id, Model model) {
		ExtCproduct extCproduct = extCproductService.get(id);
		model.addAttribute("obj", extCproduct);
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);
		List<SysCode> ctypeList = extCproductService.getCtypeList();
		model.addAttribute("ctypeList", ctypeList);
		return "/cargo/contract/jExtCproductUpdate.jsp";
	}
	//修改保存
	@RequestMapping("/cargo/extCproduct/update.action")
	public String update(ExtCproduct product, Model model) {
		extCproductService.update(product);
		
		model.addAttribute("contractProductId", product.getContractProductId());
		return "redirect:/cargo/extCproduct/toCreate.action";
	}
	
	@RequestMapping("cargo/extCproduct/deleteById.action")
	public String delete(String id, Model model, String contractProductId) {
		extCproductService.deleteById(id);
		model.addAttribute("contractProductId", contractProductId);
		return "redirect:/cargo/extCproduct/toCreate.action";
	}
}
