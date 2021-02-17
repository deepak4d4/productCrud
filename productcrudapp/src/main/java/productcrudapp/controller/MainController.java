package productcrudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

@Controller
public class MainController {
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/")
	public String home(Model m) {
		List<Product> allProduct = productDao.getAllProduct();
		m.addAttribute("products",allProduct);
		return "index";
	}
	
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title","add product");
		return "add_prdouct_form";
	}
	
	
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable int productId,HttpServletRequest request) {
		this.productDao.deleteProduct(productId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	@RequestMapping("/update/{productId}")
	public String updateProduct(@PathVariable("productId") int id,Model m) {
		Product product =this.productDao.getOneProduct(id);
		m.addAttribute("product",product);
		return "update_form";
	
	}
	
	@RequestMapping(path="/handle-product",method=RequestMethod.POST)
	public RedirectView handleForm(@ModelAttribute("product") Product product,HttpServletRequest request) {
		System.out.println(product);
		RedirectView redirectView = new RedirectView();
		this.productDao.saveProduct(product);
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
}
