package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Product;
import com.masai.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/createProduct")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(productService.createProduct(product), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/productById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Integer productId){
		return new ResponseEntity<Product>(productService.getProductById(productId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/allProducts")
	public ResponseEntity<List<Product>> getAllProduct(){
		return new ResponseEntity<List<Product>>(productService.getAllProduct(), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/deleteProduct/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable("productId") Integer productId){
		return new ResponseEntity<String>(productService.deleteProductById(productId), HttpStatus.ACCEPTED);
	}
	
}
