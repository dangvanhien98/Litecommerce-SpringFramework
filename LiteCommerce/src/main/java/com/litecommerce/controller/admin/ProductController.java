package com.litecommerce.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.litecommerce.LiteCommerceApplication;
import com.litecommerce.model.AccountModel;
import com.litecommerce.model.ProductModel;
import com.litecommerce.service.GroupProductService;
import com.litecommerce.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	GroupProductService groupProductService;
	
	@RequestMapping(value = { "/admin-product", "/admin-product/{action}/{id}"}, method = RequestMethod.GET)
	public String productPage(Model model,@PathVariable(required = false) String action, @PathVariable(required = false) Integer id,
			@RequestParam(name = "page", defaultValue = "1") Integer currentPage,
			@RequestParam(name = "size", defaultValue = "2") Integer pageSize,
			@RequestParam(name = "sortField", defaultValue = "") String sortField,
			@RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
			@RequestParam(required = false) String keyName,
			HttpSession session)
	{
		//check login
		if(session.getAttribute("account") !=null){
			AccountModel acc = (AccountModel)session.getAttribute("account");
			model.addAttribute("acc", acc);
		}
		else
			return "redirect:/admin";
		if(action != null && action.equals("delete")) {
			productService.deleteById(id);
		}
		
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("baseUrl", "/admin-product");
		// list product pagination and sort
		Pageable pageable = sortField.length() == 0 
				? PageRequest.of(currentPage - 1, pageSize)
				: PageRequest.of(currentPage - 1, pageSize, sortDir.equals("asc") ? Sort.by(sortField).ascending() :  Sort.by(sortField).descending());
		
		Page<ProductModel> productPage = productService.findPaginated(pageable);
		model.addAttribute("listproduct", productPage);
		
		int totalPages = productPage.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		if(totalPages > 0 ) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			if(pageNumbers.size()>5) {
				int begin = currentPage;
				int end =begin + 4;
				if(end >= totalPages) {
					begin = totalPages - 4;
					end = totalPages;
				}
					
				List<Integer> pageNumbersNew = pageNumbers.subList(begin-1, end);
				model.addAttribute("pageNumbers", pageNumbersNew);
			}
			else
				model.addAttribute("pageNumbers", pageNumbers);
		}
		
		return "admin/product";
	}
	
	@RequestMapping(value = "/admin-searchproduct" , method = RequestMethod.POST)
	public String PageSearch(Model model, @RequestParam(required = false) String keyName) {
	//	model.addAttribute("listproduct", productService.getProductsByName(keyName));
		Page<ProductModel> page = productService.findByProductNameContaining(keyName, PageRequest.of(1, 5));
		System.out.println("search-----------"+page.getContent().get(0).getProductName());
		return "admin/product";
	}
	
	@RequestMapping(value = {"/admin-addProduct", "/admin-updateProduct/{id}"}, method = RequestMethod.GET)
	public String showAddProduct(Model model, @PathVariable(required = false) Integer id) {
		if(id != null)
			model.addAttribute("isUpdate", "isUpdate");
		model.addAttribute("lstGrp", groupProductService.findAll());
		model.addAttribute("product", id==null ? new ProductModel() : productService.findById(id));
		return "admin/actionProduct";
	}
	
	@RequestMapping(value = {"/admin-saveProduct"}, method = RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute("product") ProductModel productModel,@RequestParam("fileImage") MultipartFile file) throws IOException{
		StringBuilder filename = new StringBuilder(file.getOriginalFilename());
		Path filenameAndpath = Paths.get(LiteCommerceApplication.uploadDir, filename.toString());
		Files.write(filenameAndpath, file.getBytes());
		productModel.setImage("/static/upload/"+filename.toString());
		productService.insert(productModel);

		return "redirect:/admin-product";
	}
}
