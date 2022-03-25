package BADA_proj.rodzaje_wydarzenia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Rodzaje_WydarzeniaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Rodzaje_WydarzeniaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Rodzaj_Wydarzenia> list() {
        String sql = "SELECT * FROM RODZAJE_WYDARZENIA";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Rodzaj_Wydarzenia.class));
    }

    public void save(Rodzaj_Wydarzenia rodzaj_wydarzenia) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("rodzaje_wydarzenia")
                .usingColumns("nazwa_rodzaju", "opis");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(rodzaj_wydarzenia);
        insertActor.execute(param);
    }

    public Rodzaj_Wydarzenia get(int id) {
        String sql = "SELECT * FROM rodzaje_wydarzenia WHERE id_rodzaju = ?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Rodzaj_Wydarzenia.class));
    }

    public void update(Rodzaj_Wydarzenia rodzaj_wydarzenia) {
        String sql = "UPDATE rodzaje_wydarzenia SET nazwa_rodzaju=:nazwa_rodzaju, opis=:opis " +
                "WHERE id_rodzaju=:id_rodzaju";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(rodzaj_wydarzenia);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM rodzaje_wydarzenia WHERE id_rodzaju = ?";
        jdbcTemplate.update(sql, id);
    }

}
