package BADA_proj.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SaleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sala> list() {

        String query = "SELECT * FROM SALE";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Sala.class));
    }

    public void save(Sala sale) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("sale").usingColumns("ozn_sali", "liczba_miejsc_strefa_a",
                "liczba_miejsc_strefa_b", "liczba_miejsc_strefa_c", "id_filharmonii");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
        insertActor.execute(param);
    }

    public Sala get(int id) {
        String sql = "SELECT * FROM SALE WHERE id_sali = ?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Sala.class));
    }

    public void update(Sala sala) {
        String sql = "UPDATE SALE SET ozn_sali=:ozn_sali, liczba_miejsc_strefa_a=:liczba_miejsc_strefa_a, " +
                "liczba_miejsc_strefa_b=:liczba_miejsc_strefa_b, liczba_miejsc_strefa_c=:liczba_miejsc_strefa_c," +
                "id_filharmonii=:id_filharmonii WHERE id_sali=:id_sali";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sala);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);

    }

    public void delete(int id) {
        String sql = "DELETE FROM SALE WHERE ID_SALI = ?";
        jdbcTemplate.update(sql, id);
    }


}
