package BADA_proj.filharmonie;

import BADA_proj.adresy.AdresyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilharmonieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AdresyDAO adresyDAO;

    public FilharmonieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Filharmonia> list() {
        String sql = "SELECT * FROM FILHARMONIE";
        List<Filharmonia> filharmoniaList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Filharmonia.class));
        for (Filharmonia filharmonia : filharmoniaList) {
            filharmonia.setAdres(adresyDAO.get(filharmonia.getId_adresu()));
        }
        return filharmoniaList;
    }

    public void save(Filharmonia filharmonia) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("filharmonie").usingColumns("nazwa_filharmonii",
                "data_zalozenia", "id_adresu");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(filharmonia);
        insertActor.execute(param);
    }

    public Filharmonia get(int id) {
        String sql = "SELECT * FROM FILHARMONIE WHERE ID_FILHARMONII = ?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Filharmonia.class));
    }

    public void update(Filharmonia filharmonia) {
        String sql = "UPDATE FILHARMONIE SET NAZWA_FILHARMONII=:nazwa_filharmonii, DATA_ZALOZENIA=:data_zalozenia, " +
                "ID_ADRESU=:id_adresu WHERE ID_FILHARMONII=:id_filharmonii";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(filharmonia);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM FILHARMONIE WHERE ID_FILHARMONII = ?";
        jdbcTemplate.update(sql, id);
    }

}
