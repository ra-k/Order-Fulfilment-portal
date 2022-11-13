package com.OrderApplicarion.controllers;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.OrderApplicarion.entities.Employe;
import com.OrderApplicarion.entities.OrderList;
import com.OrderApplicarion.payloads.OrderResponse;
import com.OrderApplicarion.payloads.OrderService;
import com.OrderApplicarion.repositories.OrderListRepository;
import com.OrderApplicarion.repositories.ProductsRepository;
import com.OrderApplicarion.services.EmployeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.OrderApplicarion.entities.Products;




@RestController
@RequestMapping("/api/v1/order/")
public class OrderController {
	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;
	@Autowired
	private OrderListRepository orderListRepository;
	
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private OrderService orderService;
	


	@PostMapping("/upload-file")
	
	public ResponseEntity<?> fileupload(@RequestParam("file")MultipartFile file) throws IOException{
		System.out.println("Connection created");
		try {
			//validation
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}
			//
			if(!file.getContentType().equals("text/xml")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Must be in xml form ");	
			}
			
		File myfile=new File(FILE_DIRECTORY+file.getOriginalFilename());
		myfile.createNewFile();
		FileOutputStream fos=new FileOutputStream(myfile);
		fos.write(file.getBytes());
		fos.close();
		ObjectMapper mapper=new XmlMapper();
		File xmlfile= new File((FILE_DIRECTORY+file.getOriginalFilename()));
		TypeReference<OrderList> typeReference= new TypeReference<OrderList>() {};
		OrderList ordermapper= mapper.readValue(xmlfile, typeReference);
		System.out.println(ordermapper.getAccount()+" "+ordermapper.getDue_date()+" "+ordermapper.getProducts());
		
		OrderList orderList=new OrderList();
		orderList.setAccount(ordermapper.getAccount());
		orderList.setDue_date(ordermapper.getDue_date());
		orderList.setProducts(ordermapper.getProducts());
		
OrderList save= orderService.save(orderList);
		
		
		return new ResponseEntity<OrderList>(save,HttpStatus.OK);
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
				body("something went wrong! Try again..");
		}
		

	@GetMapping("/findAllOrders")
	public List<OrderList> findAllOrders(){
		return orderListRepository.findAll();
	}
	@GetMapping("/Allproducts")
	public List<Products> findAllProducts(){
		return productsRepository.findAll();
	}
	@GetMapping("/getInfo")
	public List<OrderResponse> getJoinInformation(){
		return orderListRepository.getJoinInformation();
	}
//	@GetMapping("/orders/{accountname}")
//	public List<OrderList> findByAccount(@PathVariable String accountname){
//		return orderListRepository.findByAccount(accountname);
//		
//	}
	@GetMapping("/orders/{accountname}")
	public List<OrderResponse> findByAccount(@PathVariable String accountname){
		return orderListRepository.findByAccount(accountname);
		
		
	}
	@GetMapping("/category/{category}")
	public List<OrderResponse> findByCategory(@PathVariable String category){
		return orderListRepository.findByCategory(category);
	}

//
   @GetMapping("/param/{param}/key/{key}")
   public List<OrderResponse> findByParam(@PathVariable String param ,@PathVariable String key){
	switch(param){case"CATEGORY":
		return orderListRepository.findByCategory(key);	
	case"ACCOUNT":
		return orderListRepository.findByAccount(key);
		default: return new ArrayList();
	}
}
}