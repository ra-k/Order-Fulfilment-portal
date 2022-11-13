

package com.OrderApplicarion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.OrderApplicarion.entities.OrderList;
import com.OrderApplicarion.payloads.OrderResponse;




public interface OrderListRepository extends JpaRepository<OrderList, Integer>{
	
	@Query("SELECT new com.OrderApplicarion.payloads.OrderResponse( c.account,c.due_date,p.name) FROM OrderList c JOIN c.products p")
	public List<OrderResponse> getJoinInformation();
	
	@Query("SELECT new com.OrderApplicarion.payloads.OrderResponse( c.account,c.due_date,p.name,p.pid,p.category,p.quantity) FROM OrderList c JOIN c.products p WHERE c.account=?1 ")
	public List<OrderResponse> findByAccount(String accountname);

	@Query("SELECT new com.OrderApplicarion.payloads.OrderResponse( c.account,c.due_date,p.name,p.pid,p.category,p.quantity) FROM OrderList c JOIN c.products p WHERE p.category=?1 ")
	public List<OrderResponse> findByCategory(String category);
	

}

