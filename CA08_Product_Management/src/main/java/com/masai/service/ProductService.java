package com.masai.service;

import java.util.List;

import com.masai.model.Product;

public interface ProductService {

	Product createProduct(Product product);
	Product getProductById(Integer productId);
	List<Product> getAllProduct();
	String deleteProductById(Integer productId);
}
