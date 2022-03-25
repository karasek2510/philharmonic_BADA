package BADA_proj.filharmonie;

import BADA_proj.adresy.Adres;

import java.time.LocalDateTime;

public class Filharmonia {

    private int id_filharmonii;
    private String nazwa_filharmonii;
    private LocalDateTime data_zalozenia;
    private int id_adresu;
    private Adres adres;

    public Filharmonia() {
    }

    public Filharmonia(String nazwa_filharmonii, LocalDateTime data_zalozenia, int id_adresu) {
        this.nazwa_filharmonii = nazwa_filharmonii;
        this.data_zalozenia = data_zalozenia;
        this.id_adresu = id_adresu;
    }

    public Filharmonia(int id_filharmonii, String nazwa_filharmonii, LocalDateTime data_zalozenia, int id_adresu) {
        this.id_filharmonii = id_filharmonii;
        this.nazwa_filharmonii = nazwa_filharmonii;
        this.data_zalozenia = data_zalozenia;
        this.id_adresu = id_adresu;
    }

    public int getId_filharmonii() {
        return id_filharmonii;
    }

    public void setId_filharmonii(int id_filharmonii) {
        this.id_filharmonii = id_filharmonii;
    }

    public String getNazwa_filharmonii() {
        return nazwa_filharmonii;
    }

    public void setNazwa_filharmonii(String nazwa_filharmonii) {
        this.nazwa_filharmonii = nazwa_filharmonii;
    }

    public LocalDateTime getData_zalozenia() {
        return data_zalozenia;
    }

    public void setData_zalozenia(LocalDateTime data_zalozenia) {
        this.data_zalozenia = data_zalozenia;
    }

    public int getId_adresu() {
        return id_adresu;
    }

    public void setId_adresu(int id_adresu) {
        this.id_adresu = id_adresu;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Filharmonia{" +
                "id_filharmonii=" + id_filharmonii +
                ", nazwa_filharmonii='" + nazwa_filharmonii + '\'' +
                ", data_zalozenia=" + data_zalozenia +
                ", id_adresu=" + id_adresu +
                '}';
    }
}
