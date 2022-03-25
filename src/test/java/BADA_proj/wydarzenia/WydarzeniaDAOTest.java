package BADA_proj.wydarzenia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


class WydarzeniaDAOTest {

    private WydarzeniaDAO dao;
    int id;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
        dataSource.setUsername("BADACZ2");
        dataSource.setPassword("mario123");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new WydarzeniaDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Wydarzenie> listaWydarzen = dao.list();
        for(Wydarzenie wydarzenie : listaWydarzen){
            System.out.println(wydarzenie);
        }
    }

    @Test
    void save() {
        String now = "2022-11-09 10:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
        Wydarzenie wydarzenie = new Wydarzenie("WydarzenieTEST",formatDateTime,
                "OpisTEST",3,7,4);
        dao.save(wydarzenie);
    }

    @Test
    void get() {
        id = dao.list().get(0).getId_wydarzenia();
        System.out.println(dao.get(id));
    }

    @Test
    void update() {
        String now = "2016-11-09 21:37";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
        id = dao.list().get(0).getId_wydarzenia();
        Wydarzenie wydarzenie = new Wydarzenie(id,"WydarzenieUPDATE",formatDateTime,
                "OpisTEST",3,7,4);
        dao.update(wydarzenie);
    }

    @Test
    void delete() {
        id = dao.list().get(0).getId_wydarzenia();
        dao.delete(id);
    }
}