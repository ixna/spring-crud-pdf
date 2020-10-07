package id.sna.crud.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import id.sna.crud.model.Customer;

public class CustomerDAO implements CustomerDAOInterface{
	private JdbcTemplate jdbcTemplate;
	
	public CustomerDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Customer> listCustomer(int page, Integer limit) {
		String pagination;
		if (limit != null) {
			int offset = (page - 1) * limit;
			pagination = String.format("LIMIT %s, %s", offset, limit);
		} else {
			pagination = "";
		}
		
		String sql = //
	            "SELECT c.CUST_ID, c.ID_KOTA, c.NAMA, c.ALAMAT, c.PENDAPATAN, "
	            + "k.NAMA NAMAKOTA "
	            + "FROM CUSTOMER c "
	            + "INNER JOIN KOTA k ON c.ID_KOTA=k.ID_KOTA "
	            + pagination;
		Object[] params = new Object[] {};
		List<Customer> listCustomer = jdbcTemplate.query(
				sql, params, new RowMapper<Customer>() {

					@Override
					public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						Customer customer = new Customer();
						customer.setName(rs.getString("NAMA"));
						customer.setId(rs.getString("CUST_ID"));
						customer.setIdKota(rs.getString("ID_KOTA"));
						customer.setNamaKota(rs.getString("NAMAKOTA"));
						customer.setAlamat(rs.getString("ALAMAT"));
						Float pendapatan = rs.getFloat("PENDAPATAN");
						customer.setPendapatan(String.format("%.2f", pendapatan));
						return customer;
					}	
				}
			);
		
		return listCustomer;
	}
	
	@Override
	public void saveOrUpdate(Customer customer) {
		System.out.println("## CHECK: " + customer.getId());
		if (customer.getId() != null && !customer.getId().isEmpty()) {
			// update
			System.out.println("## MASHOOK");
			String sql = "UPDATE CUSTOMER SET ID_KOTA=?, NAMA=?, ALAMAT=?, PENDAPATAN=? "
						+ "WHERE CUST_ID=?";
			jdbcTemplate.update(sql, customer.getIdKota(), customer.getName(), 
					customer.getAlamat(), customer.getPendapatan(), customer.getId());
		} else {
			// insert
			String sql = "INSERT INTO CUSTOMER (CUST_ID, NAMA, ID_KOTA, ALAMAT, PENDAPATAN) "
						+ "VALUES ((SELECT lpad(conv(floor(rand()*pow(36,8)), 10, 36), 6, 0)), "
						+ "?, ?, ?, ?)";
			jdbcTemplate.update(sql, customer.getName(), customer.getIdKota(), customer.getAlamat(),
					customer.getPendapatan());
		}
	}
	
	@Override
	public void delete(String customerId) {
		String sql = "DELETE FROM CUSTOMER WHERE CUST_ID=?";
		jdbcTemplate.update(sql, customerId);
	}
	
	@Override
	public Customer get(String customerId) {
		String sql = //
				"SELECT c.CUST_ID, c.ID_KOTA, c.NAMA, c.ALAMAT, c.PENDAPATAN, "
	            + "k.NAMA NAMAKOTA "
	            + "FROM CUSTOMER c "
	            + "INNER JOIN KOTA k ON c.ID_KOTA=k.ID_KOTA "
	            + "WHERE CUST_ID=?";
		Object[] params = new Object[] {customerId};
		return jdbcTemplate.query(sql, params, new ResultSetExtractor<Customer>() {
			
			@Override
			public Customer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Customer customer = new Customer();
					customer.setName(rs.getString("NAMA"));
					customer.setId(rs.getString("CUST_ID"));
					customer.setIdKota(rs.getString("ID_KOTA"));
					customer.setNamaKota(rs.getString("NAMAKOTA"));
					customer.setAlamat(rs.getString("ALAMAT"));
					Float pendapatan = rs.getFloat("PENDAPATAN");
					customer.setPendapatan(String.format("%.2f", pendapatan));
					return customer;
				}
				
				return null;
			}
		});
	}

}
