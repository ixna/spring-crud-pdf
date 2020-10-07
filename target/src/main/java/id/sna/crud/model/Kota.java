package id.sna.crud.model;

public class Kota {
    private String id;
    private String name;
    
    public Kota(String id, String name) {
    	this.id = id;
    	this.name = name;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
    	return name;
    }
}
