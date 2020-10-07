<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <!-- Modal -->
 <div class="modal fade" id="newCustomer" role="dialog">
   <div class="modal-dialog">
   
     <!-- Modal content-->
     <div class="modal-content">
       <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">Add Customer</h4>
       </div>
       
       	<form:form action="/crud/customer/save" method="post" modelAttribute="newCustomer">
		<table>
			<form:hidden path="id"/>			
			<tr>
				<td>Nama:</td>
				<td><form:input path="name" required="true" maxlength="50"/></td>
			</tr>
			
			<tr>
				<td>Alamat:</td>
				<td><form:input path="alamat" required="true" maxlength="255" id="customer-alamat"/></td>
			</tr>
			
			<tr>
				<td>Pilih Kota</td>
				<td>
				<form:select cssClass="form-control"  path="idKota">
					<c:forEach var="kota" items="${kotas}" varStatus="status">
						<option value="${kota.id}">${kota.name}</option>
					</c:forEach>	

				</form:select>
				</td>
			</tr>
			
			<tr>
				<td>Pendapatan:</td>
				<td><form:input path="pendapatan" type="text" pattern="^\d{0,16}(?:\.\d{0,2})?$" id="customer-pendapatan"/></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
     </div>
     
   </div>
 </div>
 
 <div class="modal fade" id="updateCustomer" role="dialog">
   <div class="modal-dialog">
   
     <!-- Modal content-->
     <div class="modal-content">
       <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title" id="modalTitle"></h4>
       </div>
       
       	<form:form id="customer-data" action="/crud/customer/save" method="post" modelAttribute="newCustomer">
		<table>
			<tr>
				<td>Customer ID:</td>
				<td><form:input path="" disabled="true" id="customerId"/></td>
			</tr>
			<tr>
				<td>Nama:</td>
				<td><form:input path="name" required="true" maxlength="50" id="customerName"/></td>
			</tr>
			
			<tr>
				<td>Alamat:</td>
				<td><form:input path="alamat" required="true" maxlength="255" id="customerAlamat"/></td>
			</tr>
			
			<tr>
				<td>Pilih Kota</td>
				<td>
				<form:select cssClass="form-control"  path="idKota" id="kota">
					<c:forEach var="kota" items="${kotas}" varStatus="status">
						<option value="${kota.id}">${kota.name}</option>
					</c:forEach>	

				</form:select>
				</td>
			</tr>
			
			<tr>
				<td>Pendapatan:</td>
				<td><form:input path="pendapatan" type="text" pattern="^\d{0,16}(?:\.\d{0,2})?$" id="customerPendapatan"/></td>
			</tr>
			
			<form:hidden path="id" id="id"/>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
     </div>
     
   </div>
 </div>

 <script>
$(document).ready(function(){
	$(".editRow").click(function(){
 		var data = $(this).attr("data-form");
		/* ${customer.id}&${customer.name}&${customer.alamat}&${customer.pendapatan}&${customer.idKota}&${customer.namaKota} */
		var items = data.split('&');
		$("#modalTitle").html("Update Customer "+items[0]);
		var form = $("#customer-data");
	  	form.find("#customerId").val(items[0]);
	  	form.find("#id").val(items[0]);
  	 	form.find("#customerName").val(items[1]);
  		form.find("#customerAlamat").val(items[2]);
  		form.find("#customerPendapatan").val(items[3]);
	  	$('#kota').append($('<option>', { 
 		        value: items[4],
 		        text : items[5] 
	    }));
	}); 		
});
</script>