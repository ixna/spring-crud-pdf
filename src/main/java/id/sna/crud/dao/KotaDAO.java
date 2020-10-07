package id.sna.crud.dao;
import java.util.List;

import javax.sql.DataSource;
import id.sna.crud.mapper.KotaMapper;
import id.sna.crud.model.Kota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class KotaDAO extends JdbcDaoSupport {
	@Autowired
	public KotaDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public List<Kota> listKota() {
		String sql = KotaMapper.BASE_SQL;
		Object[] params = new Object[] {};
		KotaMapper mapper = new KotaMapper();
		
		List<Kota> list = this.getJdbcTemplate().query(sql, params, mapper);
		return list;
	}
	
	public Kota findKota(String id_kota) {
        String sql = KotaMapper.BASE_SQL //
                + " WHERE ID_KOTA = ?";
 
        Object[] params = new Object[] { id_kota };
         
        KotaMapper mapper = new KotaMapper();
 
        Kota dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }
}
