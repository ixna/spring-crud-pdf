package id.sna.crud.dao;

import java.util.List;

import id.sna.crud.model.Customer;

public interface CustomerDAOInterface {
	public void saveOrUpdate(Customer customer);
	
	public void delete(String customerId);
	
	public Customer get(String customerId);
	
	public List<Customer> listCustomer(int page, Integer limit);
}