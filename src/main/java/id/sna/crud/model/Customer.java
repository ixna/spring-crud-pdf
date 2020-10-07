package id.sna.crud.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Customer {
	private String id;
	
	@NotNull
	@Size(min=1, max=50)
    @Pattern(regexp="^[a-zA-Z0-9]+$", message="Data nama tidak sesuai ketentuan")
    private String name;
	
	@NotNull
	@Size(max=3)
    @Pattern(regexp="^[a-zA-Z]+$", message="Data Kota tidak sesuai ketentuan")
    private String idKota;
    private String namaKota;
    
    @NotNull
	@Size(min=1, max=255)
    @Pattern(regexp="^[a-zA-Z0-9]+$", message="Data Alamat tidak sesuai ketentuan")
    private String alamat;
    
    @Pattern(regexp="^\\d{0,16}(?:\\.\\d{0,2})?$", message="Data nama tidak sesuai ketentuan")
    private String  pendapatan;
    
    public Customer() {
    	
    }
    
    public Customer(
    		String id, 
    		String name, 
    		String idKota, 
    		String namaKota,
    		String alamat,
    		String pendapatan
    		) {
    	this.id = id;
    	this.name = name;
    	this.idKota = idKota;
    	this.namaKota = namaKota;
    	this.alamat = alamat;
    	this.pendapatan = pendapatan;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAlamat() {
    	return alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public String getIdKota() {
    	return idKota;
    }
    
    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }
    
    public void setNamaKota(String namaKota) {
    	this.namaKota = namaKota;
    }
    
    public String getNamaKota() {
    	return namaKota;
    }
    
    public void setPendapatan(String pendapatan) {
    	// Validasi pendapatan tidak boleh empty string
    	if (pendapatan.isEmpty()) {
    		pendapatan = null;
    	}
    	this.pendapatan = pendapatan;
    }
    
    public String getPendapatan() {
    	return pendapatan;
    }
    
}
