
package com.demo.product.dto;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * @param product
	 */
	public void save(Product product) {
		logger.info("saving the product informartion in Cart Table.");
		productRepository.save(product);
	}

	public Optional<Product> getProduct(long id) {
		logger.info("finding product.");
		return productRepository.findById(id);
	}

	public List<Product> findProductsByUserId(List list) {
		return productRepository.findAllById(list);
	}

}
