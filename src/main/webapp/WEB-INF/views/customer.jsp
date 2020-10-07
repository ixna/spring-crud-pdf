<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
 <!DOCTYPE html>
 <html>
  <head>
	  	
	  <!-- BOOTSTRAP DEPENDENCIES -->
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	  <!-- BOOTSTRAP DEPENDENCIES END-->
      <meta charset="UTF-8">
      <title>Customer List</title>
  </head>
  <body>
	  <jsp:include page="customerForm.jsp"/>
      <div align="center">
          <h1>Customer List</h1>
          <!-- Trigger the modal with a button -->
		 <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#newCustomer">Tambah Data</button>

		  <br>
		  <br>
          <table border="1">
              <tr>
	            <th>No</th>
	            <th>Customer ID</th>
                <th>Nama</th>
                <th>Alamat</th>
                <th>Kota</th>
                <th>Pendapatan</th>
              </tr>
              <c:forEach var="customer" items="${customers}" varStatus="status">
              <tr>
                  <td>${status.index + 1}</td>
                  <td>${customer.id}</td>
                  <td>${customer.name}</td>
                  <td>${customer.alamat}</td>
                  <td>${customer.namaKota}</td>
                  <td>${customer.pendapatan}</td>
                  <td>
						<a href="edit?id=${customer.id}" class="editRow" data-toggle="modal" data-target="#updateCustomer"
						data-form="${customer.id}&${customer.name}&${customer.alamat}&${customer.pendapatan}&${customer.idKota}&${customer.namaKota}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="delete?id=${customer.id }">Delete</a>
				  </td>
              </tr>
              </c:forEach>                
          </table>
      </div>
  </body>
</html>