package BADA_proj.klienci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KlienciDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public KlienciDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Klient> list() {
        String sql = "SELECT * FROM KLIENCI";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klient.class));
    }

    public void save(Klient klient) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("klienci").usingColumns("imie", "nazwisko", "nr_telefonu", "plec", "id_adresu", "haslo");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
        insertActor.execute(param);
    }

    public Klient get(int id) {
        String sql = "SELECT * FROM KLIENCI WHERE ID_KLIENTA = ?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Klient.class));
    }

    public void update(Klient klient) {
        String sql = "UPDATE KLIENCI SET IMIE=:imie, NAZWISKO=:nazwisko, NR_TELEFONU=:nr_telefonu, PLEC=:plec, " +
                "ID_ADRESU=:id_adresu, HASLO=:haslo WHERE ID_KLIENTA=:id_klienta";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM KLIENCI WHERE ID_KLIENTA = ?";
        jdbcTemplate.update(sql, id);
    }

    public Klient findByPhoneNumber(String phoneNumber){
        String sql = "SELECT * FROM KLIENCI WHERE NR_TELEFONU = ?";
        Object[] args = {phoneNumber};
        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Klient.class));
    }
}
