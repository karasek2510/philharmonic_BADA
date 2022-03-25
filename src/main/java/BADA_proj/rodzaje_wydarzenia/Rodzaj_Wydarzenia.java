package BADA_proj.rodzaje_wydarzenia;

public class Rodzaj_Wydarzenia {

    private int id_rodzaju;
    private String nazwa_rodzaju;
    private String opis;

    public Rodzaj_Wydarzenia() {
    }

    public Rodzaj_Wydarzenia(String nazwa_rodzaju, String opis) {
        this.nazwa_rodzaju = nazwa_rodzaju;
        this.opis = opis;
    }

    public Rodzaj_Wydarzenia(int id_rodzaju, String nazwa_rodzaju, String opis) {
        this.id_rodzaju = id_rodzaju;
        this.nazwa_rodzaju = nazwa_rodzaju;
        this.opis = opis;
    }

    public int getId_rodzaju() {
        return id_rodzaju;
    }

    public void setId_rodzaju(int id_rodzaju) {
        this.id_rodzaju = id_rodzaju;
    }

    public String getNazwa_rodzaju() {
        return nazwa_rodzaju;
    }

    public void setNazwa_rodzaju(String nazwa_rodzaju) {
        this.nazwa_rodzaju = nazwa_rodzaju;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Rodzaj_Wydarzenia{" +
                "id_rodzaju=" + id_rodzaju +
                ", nazwa_rodzaju='" + nazwa_rodzaju + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}
