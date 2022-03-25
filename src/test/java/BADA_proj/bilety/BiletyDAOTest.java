package BADA_proj.bilety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

class BiletyDAOTest {

    int id;
    private BiletyDAO dao;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
        dataSource.setUsername("BADACZ2");
        dataSource.setPassword("mario123");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new BiletyDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Bilet> listaBiletow = dao.list();
        for (Bilet bilet : listaBiletow) {
            System.out.println(bilet);
        }
    }

    @Test
    void save() {
        Bilet bilet = new Bilet(42f, "N", "A", "6/12", 7, 3, null);
        dao.save(bilet);
    }

    @Test
    void get() {
        id = dao.list().get(0).getId_biletu();
        System.out.println(dao.get(id));
    }

    @Test
    void update() {
        id = dao.list().get(0).getId_biletu();
        Bilet bilet = new Bilet(id, 42f, "N", "A", "6/12", null, 4, null);
        dao.update(bilet);
    }

    @Test
    void delete() {
        id = dao.list().get(0).getId_biletu();
        dao.delete(id);
    }
}