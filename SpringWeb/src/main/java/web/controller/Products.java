package web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.database.DAOFactory;
import web.database.DataProductDAOInterface;
import web.database.DbConnector;
import web.database.ProductDBService;
import web.model.Product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class Products {
	
	private ProductDBService productService;
	
	@GetMapping
	public String printProduts(ModelMap modelMap, @RequestParam(required=false) String category){
		List<Product> productList = null;
		if (category == null || category.isEmpty()) {
			productList = productService.getProducts();
		}else {
			productList = productService.getProductsByCategory(category);
		}
		modelMap.addAttribute("productList", productList);
		return "products";
	}
	
//	@GetMapping()
//	public String printAllProduts(ModelMap modelMap){
//		List<Product> productList = null;
//		productList = productService.getProducts();
//		modelMap.addAttribute("productList", productList);
//		return "products";
//	}

	@Autowired
	public void setProductService(ProductDBService productService) {
		this.productService = productService;
	}
	
	

}
