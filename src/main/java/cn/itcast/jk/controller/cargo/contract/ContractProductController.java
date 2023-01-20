package cn.itcast.jk.controller.cargo.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.FactoryService;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Jan 2, 2022
 */
@Controller
public class ContractProductController {//由于没有日期字段, 这里我们不继承BaseController
	@Resource
	private ContractProductService contractProductService;
	/**
	 * 一般来讲, 最好是调用自己的Service
	 * 由于这里我们的业务比较简单, 就调用别人的了
	 */
	@Resource
	private FactoryService factoryService;

	//转向新增页面
	@RequestMapping("/cargo/contractProduct/toCreate.action")
	public String toCreate(String contractId, Model model) {//传递主表的id
		model.addAttribute("contractId", contractId);

		//准备生产厂家的下拉列表
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("state", 1);
		List<Factory> factoryList = factoryService.find(paraMap );
		model.addAttribute("factoryList", factoryList);
		
		//某个合同下的货物
		paraMap.put("contractId", contractId);
		List<ContractProduct> productList = contractProductService.find(paraMap);
		model.addAttribute("dataList", productList);
		return "/cargo/contract/jContractProductCreate.jsp";//货物的新增页面
	}
	
	@RequestMapping("/cargo/contractProduct/insert.action")
	public String insert(ContractProduct contractProduct, Model model) {
		contractProductService.insert(contractProduct);
		model.addAttribute("contractId", contractProduct.getContractId());//传递主表的id
		return "redirect:/cargo/contractProduct/toCreate.action";//新增完转向新增页面, 为了批量新增, 让用户操作更方便
	}
	
	//转向修改页面
	@RequestMapping("/cargo/contractProduct/toUpdate.action")
	public String toUpdate(String id, Model model) {
		ContractProduct contractProduct = contractProductService.get(id);
		model.addAttribute("product", contractProduct);
		
		/**
		 * 重复代码, 少量的我们直接复制, 如果是大量的, 我们要抽取公共的方法
		 */
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);
		return "/cargo/contract/jContractProductUpdate.jsp";
	}
	//修改保存
	@RequestMapping("/cargo/contractProduct/update.action")
	public String update(ContractProduct product, Model model) {
		contractProductService.update(product);
		
		model.addAttribute("contractId", product.getContractId());//传递主表的id
		return "redirect:/cargo/contractProduct/toCreate.action";
	}
	
	@RequestMapping("cargo/contractProduct/deleteById.action")
	public String delete(String id, Model model, String contractId) {
		/**
		 * 为什么不直接用批量删除的接口? 
		 * 	因为此功能基本不会涉及到批量删除的操作, 预期应该都是单个单个删除的操作
		 * 因此考虑到效率, 毕竟批量删除涉及到sql语句中in关键字
		 */
		contractProductService.deleteById(id);
		model.addAttribute("contractId", contractId);//传递主表的id
		return "redirect:/cargo/contractProduct/toCreate.action";
	}
}
