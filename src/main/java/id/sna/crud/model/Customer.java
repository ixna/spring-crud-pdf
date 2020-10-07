package id.sna.crud.model;

public class Customer {
	private String id;
    private String name;
    private String idKota;
    private String namaKota;
    private String alamat;
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
