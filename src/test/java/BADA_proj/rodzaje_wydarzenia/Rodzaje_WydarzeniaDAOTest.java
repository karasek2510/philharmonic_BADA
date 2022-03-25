package BADA_proj.rodzaje_wydarzenia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;


class Rodzaje_WydarzeniaDAOTest {

    private Rodzaje_WydarzeniaDAO dao;
    int id;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
        dataSource.setUsername("BADACZ2");
        dataSource.setPassword("mario123");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new Rodzaje_WydarzeniaDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Rodzaj_Wydarzenia> listaRodzajów = dao.list();
        for(Rodzaj_Wydarzenia rodzaj_wydarzenia : listaRodzajów){
            System.out.println(rodzaj_wydarzenia);
        }
    }

    @Test
    void save() {
        Rodzaj_Wydarzenia rodzaj_wydarzenia =
                new Rodzaj_Wydarzenia("RodzajTEST","OpisTEST");
        dao.save(rodzaj_wydarzenia);
    }

    @Test
    void get() {
        id = dao.list().get(0).getId_rodzaju();
        System.out.println(dao.get(id));
    }

    @Test
    void update() {
        id = dao.list().get(0).getId_rodzaju();
        Rodzaj_Wydarzenia rodzaj_wydarzenia =
                new Rodzaj_Wydarzenia(id,"RodzajUPDATE","OpisUPDATE");
        dao.update(rodzaj_wydarzenia);
    }

    @Test
    void delete() {
        id = dao.list().get(0).getId_rodzaju();
        dao.delete(id);
    }
}