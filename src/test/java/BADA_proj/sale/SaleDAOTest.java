package BADA_proj.sale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleDAOTest {

    private SaleDAO dao;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
        dataSource.setUsername("BADACZ2");
        dataSource.setPassword("mario123");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new SaleDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Sala> listaSal = dao.list();
        for(Sala sala: listaSal){
            System.out.println(sala);
        }
    }

    @Test
    void save(){
        Sala sala = new Sala("4R",20,30,30,3);
        dao.save(sala);
    }

    @Test
    void get(){
        System.out.println(dao.get(7));
    }

    @Test
    void update() {
        Sala sala = new Sala(8,"TestUpdate",20,30,30,3);
        dao.update(sala);
    }

    @Test
    void delete() {
        dao.delete(8);
    }
}