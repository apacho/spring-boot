package com.demo.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.product.dto.Order;
import com.demo.product.dto.Product;
import com.demo.product.dto.ProductService;

@RestController
@RequestMapping("/orders")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping("")
	public List<Order> getAll() {

		System.out.println("from order controller ===============>>");

		List<Order> orders = new ArrayList<>();

		Order order = new Order();
		order.setId(101);
		order.setDes("desc1");
		orders.add(order);

		order = new Order();
		order.setId(102);
		order.setDes("desc3");
		orders.add(order);

		return orders;
	}

	@GetMapping("/{productId}")
	public Optional<Product> getProduct(@PathVariable long productId) {
		System.out.println("from order controller ===============>>" + productId);
		return productService.getProduct(productId);

	}

	@GetMapping("/byparam")
	public List<Order> getAllByReqParam(@RequestParam String userId) {

		System.out.println("from order controller ===============>>" + userId);

		List<Order> orders = new ArrayList<>();

		Order order = new Order();
		order.setId(105);
		order.setDes("desc5");
		orders.add(order);

		order = new Order();
		order.setId(106);
		order.setDes("desc6");
		orders.add(order);

		return orders;
	}

	@PostMapping("/byparam")
	public List<Order> getAllByPostReqParam(@RequestParam String userId) {

		System.out.println("from order controller ===============>>"+userId);

		List<Order> orders = new ArrayList<>();

		Order order = new Order();
		order.setId(107);
		order.setDes("desc7");
		orders.add(order);

		order = new Order();
		order.setId(108);
		order.setDes("desc8");
		orders.add(order);

		return orders;
	}

	@PostMapping("/bybody")
	public Order getAllByPostReqBody(@RequestBody Order order) {

		System.out.println("from order controller ===============>>");

		order.setDes(order.getDes() + "updated");

		return order;
	}
}
