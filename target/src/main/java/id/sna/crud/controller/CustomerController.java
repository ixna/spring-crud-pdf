package id.sna.crud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import id.sna.crud.dao.CustomerDAOInterface;
import id.sna.crud.dao.KotaDAO;
import id.sna.crud.model.Customer;
import id.sna.crud.model.Kota;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
    @Autowired
	private CustomerDAOInterface customerDao;
    
    @Autowired
	private KotaDAO kotaDao;
    
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView showCustomer(HttpServletRequest request) {
		String page_data = request.getParameter("page");
		int page;
		if (page_data == null || page_data.isEmpty()) {
			page = 1;
		} else {
			page = Integer.parseInt(page_data);
			if (page < 1) {
				page = 1;
			}
		}

		ModelAndView model = new ModelAndView("customer");
		Integer limit = 10;
		List<Customer> list = customerDao.listCustomer(page, limit);
		List<Kota> listKota = kotaDao.listKota();
		model.addObject("customers", list);
		model.addObject("kotas", listKota);
		
		Customer newCustomer = new Customer();
		model.addObject("newCustomer", newCustomer);
		
		model.setViewName("customer");
		
	    return model;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveCustomer(@ModelAttribute Customer customer) {
		customerDao.saveOrUpdate(customer);	
		return new ModelAndView("redirect:/customer/");
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteCustomer(HttpServletRequest request) {
		String customerId = request.getParameter("id");
		customerDao.delete(customerId);
		return new ModelAndView("redirect:/customer/");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editCustomer(HttpServletRequest request) {
		String customerId = request.getParameter("id");
		Customer customer = customerDao.get(customerId);
		ModelAndView model = new ModelAndView("customerForm");
		model.addObject("Customer", customer);
		
		return model;
	}
	
	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	@ResponseBody
	public void getRpt1(HttpServletResponse response) throws JRException, IOException {
	    InputStream jasperStream = this.getClass().getResourceAsStream("/customer.jasper");
	    Map<String,Object> parameters = new HashMap<>();
	    
	    List<Customer> dataList = customerDao.listCustomer(1, null);
	    
	    JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
	    
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
	
	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
}
