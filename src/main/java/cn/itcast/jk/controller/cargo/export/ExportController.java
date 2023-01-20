package cn.itcast.jk.controller.cargo.export;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.service.ExportService;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Aug 31, 2022
 */
@Controller
public class ExportController extends BaseController{
	@Resource
	ExportService exportService;
	
	@RequestMapping("/cargo/export/list.action")
	public String list(Model model) {
		List<Export> dataList = exportService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jExportList.jsp";
	}
	
	/**
	 * 购销合同查询列表
	 * @return
	 */
	@RequestMapping("/cargo/export/contractList.action")
	public String contractList(Model model) {
		List<Contract> dataList = exportService.getContractList();
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jContractList.jsp";//报运目录下调用购销合同列表
	}
	
	/**
	 * 报运新增, 直接进行后台保存
	 * @param contractIds 合同id的集合
	 */
	@RequestMapping("/cargo/export/toCreate.action")
	public String insert(String[] contractIds) {
		exportService.insert(entity);
		return "redirect:/cargo/export/list.action"
	}
}
