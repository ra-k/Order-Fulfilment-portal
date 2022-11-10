package com.OrderApplicarion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.OrderApplicarion.entities.OrderList;
import com.OrderApplicarion.payloads.OrderResponse;




public interface OrderListRepository extends JpaRepository<OrderList, Integer>{
	
	@Query("SELECT new com.OrderApplicarion.payloads.OrderResponse( c.account,c.due_date,p.name) FROM OrderList c JOIN c.products p")
//	@Query(value="select order_list.account,order_list.due_date,products.category,products.name,products.pid ,"
//			+ "products.quantity from order_list,products where order_list.id=products.op_fk",nativeQuery = true)
	public List<OrderResponse> getJoinInformation();
	
	//@Query("")

}

