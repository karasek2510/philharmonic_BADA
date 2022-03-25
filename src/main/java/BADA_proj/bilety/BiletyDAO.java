package BADA_proj.bilety;


import BADA_proj.wydarzenia.WydarzeniaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BiletyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private WydarzeniaDAO wydarzeniaDAO;

    public BiletyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bilet> list() {
        String sql = "SELECT * FROM BILETY";
        List<Bilet> bilety = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Bilet.class));
        for(Bilet bilet : bilety){
            bilet.setWydarzenie(wydarzeniaDAO.get(bilet.getId_wydarzenia()));
        }
        return bilety;
    }

    public void save(Bilet bilet) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("bilety").usingColumns("cena", "rodzaj", "strefa", "ozn_miejsca", "id_klienta", "id_wydarzenia", "id_kasy");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(bilet);
        insertActor.execute(param);
    }

    public Bilet get(int id) {
        String sql = "SELECT * FROM BILETY WHERE ID_BILETU= ?";
        Object[] args = {id};
        Bilet bilet = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Bilet.class));
        bilet.setWydarzenie(wydarzeniaDAO.get(bilet.getId_biletu()));
        return bilet;
    }

    public List<Bilet> findByKlientId(int id) {
        String sql = "SELECT * FROM BILETY WHERE ID_KLIENTA= ?";
        Object[] args = {id};
        try{
            List<Bilet> bilety = jdbcTemplate.query(sql,args, BeanPropertyRowMapper.newInstance(Bilet.class));
            for(Bilet bilet : bilety){
                bilet.setWydarzenie(wydarzeniaDAO.get(bilet.getId_wydarzenia()));
            }
            return bilety;
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public void update(Bilet bilet) {
        String sql = "UPDATE BILETY SET CENA=:cena, RODZAJ=:rodzaj, STREFA=:strefa, OZN_MIEJSCA=:ozn_miejsca, ID_KLIENTA=:id_klienta," +
                "ID_WYDARZENIA=:id_wydarzenia, ID_KASY=:id_kasy WHERE ID_BILETU=:id_biletu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(bilet);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM BILETY WHERE ID_BILETU = ?";
        jdbcTemplate.update(sql, id);
    }

}
