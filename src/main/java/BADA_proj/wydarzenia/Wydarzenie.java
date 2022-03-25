package BADA_proj.wydarzenia;

import BADA_proj.filharmonie.Filharmonia;
import BADA_proj.rodzaje_wydarzenia.Rodzaj_Wydarzenia;
import BADA_proj.sale.Sala;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Wydarzenie {

    private int id_wydarzenia;
    private String nazwa_wydarzenia;
    private LocalDateTime czas_rozpoczecia;
    private String czas_rozpoczecia_str;
    private String opis;
    private int id_filharmonii;
    private int id_sali;
    private int id_rodzaju;
    private Sala sala;
    private Filharmonia filharmonia;
    private Rodzaj_Wydarzenia rodzaj_wydarzenia;



    public Wydarzenie() {
    }

    public Wydarzenie(String nazwa_wydarzenia, LocalDateTime czas_rozpoczecia, String opis, int id_filharmonii, int id_sali, int id_rodzaju) {
        this.nazwa_wydarzenia = nazwa_wydarzenia;
        this.czas_rozpoczecia = czas_rozpoczecia;
        this.opis = opis;
        this.id_filharmonii = id_filharmonii;
        this.id_sali = id_sali;
        this.id_rodzaju = id_rodzaju;
    }

    public Wydarzenie(int id_wydarzenia, String nazwa_wydarzenia, LocalDateTime czas_rozpoczecia, String opis, int id_filharmonii, int id_sali, int id_rodzaju) {
        this.id_wydarzenia = id_wydarzenia;
        this.nazwa_wydarzenia = nazwa_wydarzenia;
        this.czas_rozpoczecia = czas_rozpoczecia;
        this.opis = opis;
        this.id_filharmonii = id_filharmonii;
        this.id_sali = id_sali;
        this.id_rodzaju = id_rodzaju;
    }

    public int getId_wydarzenia() {
        return id_wydarzenia;
    }

    public void setId_wydarzenia(int id_wydarzenia) {
        this.id_wydarzenia = id_wydarzenia;
    }

    public String getNazwa_wydarzenia() {
        return nazwa_wydarzenia;
    }

    public void setNazwa_wydarzenia(String nazwa_wydarzenia) {
        this.nazwa_wydarzenia = nazwa_wydarzenia;
    }

    public LocalDateTime getCzas_rozpoczecia() {
        return czas_rozpoczecia;
    }

    public void setCzas_rozpoczecia(LocalDateTime czas_rozpoczecia) {
        this.czas_rozpoczecia = czas_rozpoczecia;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getId_filharmonii() {
        return id_filharmonii;
    }

    public void setId_filharmonii(int id_filharmonii) {
        this.id_filharmonii = id_filharmonii;
    }

    public int getId_sali() {
        return id_sali;
    }

    public void setId_sali(int id_sali) {
        this.id_sali = id_sali;
    }

    public int getId_rodzaju() {
        return id_rodzaju;
    }

    public void setId_rodzaju(int id_rodzaju) {
        this.id_rodzaju = id_rodzaju;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Filharmonia getFilharmonia() {
        return filharmonia;
    }

    public void setFilharmonia(Filharmonia filharmonia) {
        this.filharmonia = filharmonia;
    }

    public Rodzaj_Wydarzenia getRodzaj_wydarzenia() {
        return rodzaj_wydarzenia;
    }

    public void setRodzaj_wydarzenia(Rodzaj_Wydarzenia rodzaj_wydarzenia) {
        this.rodzaj_wydarzenia = rodzaj_wydarzenia;
    }

    public String getCzas_rozpoczecia_str() {
        return czas_rozpoczecia_str;
    }

    public void setCzas_rozpoczecia_str(String czas_rozpoczecia_str) {
        this.czas_rozpoczecia_str = czas_rozpoczecia_str;
    }

    @Override
    public String toString() {
        return "Wydarzenie{" +
                "id_wydarzenia=" + id_wydarzenia +
                ", nazwa_wydarzenia='" + nazwa_wydarzenia + '\'' +
                ", czas_rozpoczecia=" + czas_rozpoczecia +
                ", opis='" + opis + '\'' +
                ", id_filharmonii=" + id_filharmonii +
                ", id_sali=" + id_sali +
                ", id_rodzaju=" + id_rodzaju +
                '}';
    }
}
