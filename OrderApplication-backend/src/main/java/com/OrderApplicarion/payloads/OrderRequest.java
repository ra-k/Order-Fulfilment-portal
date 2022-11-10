package com.OrderApplicarion.payloads;

import com.OrderApplicarion.entities.OrderList;

public class OrderRequest {
	private OrderList orderList;

	public OrderList getOrderList() {
		return orderList;
	}

	public void setOrderList(OrderList orderList) {
		this.orderList = orderList;
	}

	public OrderRequest(OrderList orderList) {
		super();
		this.orderList = orderList;
	}

	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderRequest [orderList=" + orderList + "]";
	}
	
	

}

