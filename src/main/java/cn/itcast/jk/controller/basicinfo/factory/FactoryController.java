package cn.itcast.jk.controller.basicinfo.factory;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.FactoryService;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Oct 16, 2021
 */
@Controller
public class FactoryController extends BaseController{
	@Resource
	private FactoryService factoryService;

	//列表
	@RequestMapping("/basicinfo/factory/list.action")
	public String list(Model model) {
		List<Factory> dataList = factoryService.find(null);
		model.addAttribute("dataList", dataList);	//将数据传递到页面
		
		return "/basicinfo/factory/jFactoryList.jsp";	//转向页面
	}
	
	//转向新增页面
	@RequestMapping("/basicinfo/factory/toCreate.action")
	public String toCreate() {
		return "/basicinfo/factory/jFactoryCreate.jsp";
	}

	//新增保存
	@RequestMapping("/basicinfo/factory/insert.action")
	public String insert(Factory entity) {
		factoryService.insert(entity);
		return "redirect:/basicinfo/factory/list.action"; //redirect和后面的url之间不能有空格
	}
	
	@RequestMapping("/basicinfo/factory/toUpdate.action")
	public String toUpdate(String id, Model model) {
		model.addAttribute("obj", factoryService.get(id));//取名叫obj, 是为了以后复用的话, 改动少
		return "/basicinfo/factory/jFactoryUpdate.jsp";
	}
	
	@RequestMapping("/basicinfo/factory/update.action")
	public String update(Factory entity) {
		factoryService.update(entity);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//删除一个
	@RequestMapping("/basicinfo/factory/deleteById.action")
	public String deleteById(String id) {
		factoryService.deleteById(id);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//删除多条
	@RequestMapping("/basicinfo/factory/delete.action")
	public String delete(@RequestParam("id")String [] ids) {
		factoryService.delete(ids);//还有一种方式: id.split(',');
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//查看
	@RequestMapping("/basicinfo/factory/toview.action")
	public String toview(String id, Model model) {
		model.addAttribute("obj", factoryService.get(id));
		return "/basicinfo/factory/jFactoryView.jsp";
	}
	
	//批量启用
	@RequestMapping("/basicinfo/factory/start.action")
	public String start(@RequestParam("id")Serializable[] ids) {
		factoryService.start(ids);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//批量停用
	@RequestMapping("/basicinfo/factory/stop.action")
	public String stop(@RequestParam("id")Serializable[] ids) {
		factoryService.stop(ids);
		return "redirect:/basicinfo/factory/list.action";
	}

}
