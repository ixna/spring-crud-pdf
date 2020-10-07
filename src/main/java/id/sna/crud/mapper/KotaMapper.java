package id.sna.crud.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import id.sna.crud.model.Kota;
import org.springframework.jdbc.core.RowMapper;

public class KotaMapper implements RowMapper<Kota>{
    public static final String BASE_SQL = //
            "SELECT ID_KOTA, NAMA FROM KOTA";
 
    @Override
    public Kota mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("ID_KOTA");
        String name = rs.getString("NAMA");
        
        return new Kota(id, name);
    }	
}