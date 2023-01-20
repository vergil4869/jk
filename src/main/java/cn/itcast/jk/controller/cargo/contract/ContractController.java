package cn.itcast.jk.controller.cargo.contract;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.vo.ContractVO;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Dec 19, 2021
 */
@Controller
public class ContractController extends BaseController{
	@Resource
	private ContractService contractService;

	@RequestMapping("/cargo/contract/list.action")
	public String list(Model model) {
		List<Contract> dataList = contractService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/contract/jContractList.jsp";
	}
	
	@RequestMapping("/cargo/contract/toCreate.action")
	public String toCreate() {
		return "/cargo/contract/jContractCreate.jsp";
	}
	
	@RequestMapping("/cargo/contract/insert.action")
	public String insert(Contract entity) {
		contractService.insert(entity);
		return "redirect:/cargo/contract/list.action";
	}
	
	@RequestMapping("/cargo/contract/toUpdate.action")
	public String toUpdate(String id, Model model) {//需要Model对象, 是因为页面要访问Contract对象
		Contract attributeValue = contractService.get(id);
		model.addAttribute("obj", attributeValue);
		return "/cargo/contract/jContractUpdate.jsp";
	}

	@RequestMapping("/cargo/contract/update.action")
	public String update(Contract entity) {
		contractService.update(entity);
		return "redirect:/cargo/contract/list.action";
	}
	
	@RequestMapping("/cargo/contract/delete.action")
	public String delete(@RequestParam("id")String[] ids) {
		contractService.delete(ids);
		return "redirect:/cargo/contract/list.action";
	}
	
	@RequestMapping("/cargo/contract/toView.action")
	public String toView(String id, Model model) {
//		model.addAttribute("obj", contractService.get(id));
		ContractVO obj = contractService.view(id);
		model.addAttribute("obj", obj);
		return "/cargo/contract/jContractView.jsp";
	}
	
	//上报
	@RequestMapping("/cargo/contract/submit.action")
	public String submit(@RequestParam("id")String[] ids) {
		contractService.submit(ids);
		return "redirect:/cargo/contract/list.action";
	}
	//取消
	@RequestMapping("/cargo/contract/cancel.action")
	public String cancel(@RequestParam("id")String[] ids) {
		contractService.cancel(ids);
		return "redirect:/cargo/contract/list.action";
	}
	
}
