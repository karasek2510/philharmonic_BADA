package BADA_proj.filharmoanie;

import BADA_proj.filharmonie.Filharmonia;
import BADA_proj.filharmonie.FilharmonieDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

class FilharmonieDAOTest {

    private FilharmonieDAO dao;
    int id;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
        dataSource.setUsername("BADACZ2");
        dataSource.setPassword("mario123");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new FilharmonieDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Filharmonia> listaFilharmonii = dao.list();
        for(Filharmonia filharmoniaa : listaFilharmonii){
            System.out.println(filharmoniaa);
        }
    }

    @Test
    void save() {
        String now = "2016-11-09 10:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
        Filharmonia filharmonia = new Filharmonia("FilharmoniaTest",
                formatDateTime,17);
        dao.save(filharmonia);
    }

    @Test
    void get() {
        id = dao.list().get(0).getId_filharmonii();
        System.out.println(dao.get(id));
    }

    @Test
    void update() {
        String now = "2016-11-09 21:37";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
        id = dao.list().get(0).getId_filharmonii();
        Filharmonia filharmonia = new Filharmonia(id,"FilharmoniaUpdate",
                formatDateTime,17);
        dao.update(filharmonia);
    }

    @Test
    void delete() {
        id = dao.list().get(0).getId_filharmonii();
        dao.delete(id);
    }
}