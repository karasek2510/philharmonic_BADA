package BADA_proj.wydarzenia;

import BADA_proj.filharmonie.FilharmonieDAO;
import BADA_proj.rodzaje_wydarzenia.Rodzaje_WydarzeniaDAO;
import BADA_proj.sale.SaleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

@Repository
public class WydarzeniaDAO {

    @Autowired
    private SaleDAO saleDAO;
    @Autowired
    private FilharmonieDAO filharmonieDAO;
    @Autowired
    private Rodzaje_WydarzeniaDAO rodzaje_wydarzeniaDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WydarzeniaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Wydarzenie getNextEvent() {
        String sql = "SELECT * FROM WYDARZENIA";
        List<Wydarzenie> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
        Wydarzenie nextWydarzenie = list.get(0);
        long minutes = Long.MAX_VALUE;
        for (Wydarzenie wydarzenie : list) {
            long tempMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), wydarzenie.getCzas_rozpoczecia());
            if (tempMinutes > 0 && tempMinutes < minutes) {
                nextWydarzenie = wydarzenie;
            }
        }
        return nextWydarzenie;
    }

    public List<Wydarzenie> list() {
        String sql = "SELECT * FROM WYDARZENIA";
        List<Wydarzenie> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
        for (Wydarzenie wydarzenie : list) {
            wydarzenie.setSala(saleDAO.get(wydarzenie.getId_sali()));
            wydarzenie.setFilharmonia(filharmonieDAO.get(wydarzenie.getId_filharmonii()));
            wydarzenie.setRodzaj_wydarzenia(rodzaje_wydarzeniaDAO.get(wydarzenie.getId_rodzaju()));
        }
        return list;
    }

    public void save(Wydarzenie wydarzenie) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("wydarzenia").usingColumns("id_wydarzenia", "nazwa_wydarzenia", "czas_rozpoczecia",
                "opis", "id_filharmonii", "id_sali", "id_rodzaju");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wydarzenie);
        insertActor.execute(param);
    }

    public Wydarzenie get(int id) {
        String sql = "SELECT * FROM WYDARZENIA WHERE ID_WYDARZENIA= ?";
        Object[] args = {id};
        Wydarzenie wydarzenie = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
        wydarzenie.setSala(saleDAO.get(wydarzenie.getId_sali()));
        wydarzenie.setFilharmonia(filharmonieDAO.get(wydarzenie.getId_filharmonii()));
        wydarzenie.setRodzaj_wydarzenia(rodzaje_wydarzeniaDAO.get(wydarzenie.getId_rodzaju()));
        return wydarzenie;
    }

    public void update(Wydarzenie wydarzenie) {
        String sql = "UPDATE WYDARZENIA SET NAZWA_WYDARZENIA=:nazwa_wydarzenia, CZAS_ROZPOCZECIA=:czas_rozpoczecia, " +
                "OPIS=:opis, ID_FILHARMONII=:id_filharmonii, ID_SALI=:id_sali, ID_RODZAJU=:id_rodzaju WHERE ID_WYDARZENIA=:id_wydarzenia";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wydarzenie);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM WYDARZENIA WHERE ID_WYDARZENIA = ?";
        jdbcTemplate.update(sql, id);
    }

    public  List<Wydarzenie> getCurrentWydarzenia(){
        List<Wydarzenie> wydarzeniaList = list();
        List<Wydarzenie> currentWydarzenia = new LinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        for(Wydarzenie wydarzenie : wydarzeniaList){
            if(ChronoUnit.DAYS.between(now, wydarzenie.getCzas_rozpoczecia())>=0){
                currentWydarzenia.add(wydarzenie);
            }
        }
        return currentWydarzenia;
    }

}
