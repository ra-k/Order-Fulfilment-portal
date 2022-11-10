package com.OrderApplicarion.payloads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderApplicarion.entities.OrderList;
import com.OrderApplicarion.repositories.OrderListRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderListRepository orderRepo;
	
	public OrderList save(OrderList order)
	{
		return orderRepo.save(order);
	}


}

