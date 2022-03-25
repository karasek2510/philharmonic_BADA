package BADA_proj.klienci;

import BADA_proj.wydarzenia.WydarzeniaDAO;
import BADA_proj.wydarzenia.Wydarzenie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KlienciDAOTest {
    private KlienciDAO dao;
    int id;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
        dataSource.setUsername("BADACZ2");
        dataSource.setPassword("mario123");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new KlienciDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Klient> listaKlientow = dao.list();
        for(Klient klient : listaKlientow){
            System.out.println(klient);
        }
    }

    @Test
    void save() {
        Klient klient = new Klient("ImieTEST","NazwiskoTEST","123123123","K",17);
        dao.save(klient);
    }

    @Test
    void get() {
        id = dao.list().get(0).getId_klienta();
        System.out.println(dao.get(id));
    }

    @Test
    void update() {
        id = dao.list().get(0).getId_klienta();
        Klient klient = new Klient(id,"ImieUPDATE","NazwiskoUPDATE","123123123","K",17);
        dao.update(klient);
    }

    @Test
    void delete() {
        id = dao.list().get(0).getId_klienta();
        dao.delete(id);
    }

    @Test
    void findByPhoneNumber() {
        System.out.println(dao.findByPhoneNumber("123123123"));
    }
}