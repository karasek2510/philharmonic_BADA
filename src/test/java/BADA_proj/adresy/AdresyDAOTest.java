package BADA_proj.adresy;

import BADA_proj.filharmonie.Filharmonia;
import BADA_proj.sale.SaleDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


class AdresyDAOTest {

    private AdresyDAO dao;
    int id;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
        dataSource.setUsername("BADACZ2");
        dataSource.setPassword("mario123");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new AdresyDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Adres> listaAdresów = dao.list();
        for(Adres adres : listaAdresów){
            System.out.println(adres);
        }
    }

    @Test
    void save() {
        Adres adres = new Adres("MiastoTEST","UlicaTEST","TEST",null,"20-050");
        dao.save(adres);
    }

    @Test
    void get() {
        id = dao.list().get(0).getId_adresu();
        System.out.println(dao.get(id));
    }

    @Test
    void update() {
        id = dao.list().get(0).getId_adresu();
        Adres adres = new Adres(id,"MiastoUPDATE","UlicaTEST","UPDA",null,"20-050");
        dao.update(adres);
    }

    @Test
    void delete() {
        id = dao.list().get(0).getId_adresu();
        dao.delete(id);
    }
}