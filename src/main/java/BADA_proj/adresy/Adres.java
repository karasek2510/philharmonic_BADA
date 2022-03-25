package BADA_proj.adresy;

public class Adres {

    private int id_adresu;
    private String miasto;
    private String ulica;
    private String nr_budynku;
    private String nr_lokalu;
    private String kod_pocztowy;

    public Adres() {
    }

    public Adres(String miasto, String ulica, String nr_budynku, String nr_lokalu, String kod_pocztowy) {
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_budynku = nr_budynku;
        this.nr_lokalu = nr_lokalu;
        this.kod_pocztowy = kod_pocztowy;
    }

    public Adres(int id_adresu, String miasto, String ulica, String nr_budynku, String nr_lokalu, String kod_pocztowy) {
        this.id_adresu = id_adresu;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_budynku = nr_budynku;
        this.nr_lokalu = nr_lokalu;
        this.kod_pocztowy = kod_pocztowy;
    }

    public int getId_adresu() {
        return id_adresu;
    }

    public void setId_adresu(int id_adresu) {
        this.id_adresu = id_adresu;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNr_budynku() {
        return nr_budynku;
    }

    public void setNr_budynku(String nr_budynku) {
        this.nr_budynku = nr_budynku;
    }

    public String getNr_lokalu() {
        return nr_lokalu;
    }

    public void setNr_lokalu(String nr_lokalu) {
        this.nr_lokalu = nr_lokalu;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id_adresu=" + id_adresu +
                ", miasto='" + miasto + '\'' +
                ", ulica='" + ulica + '\'' +
                ", nr_budynku='" + nr_budynku + '\'' +
                ", nr_lokalu='" + nr_lokalu + '\'' +
                ", kod_pocztowy='" + kod_pocztowy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Adres adres = (Adres) o;

        if (!miasto.equals(adres.miasto)) {
            return false;
        }
        if (!ulica.equals(adres.ulica)) {
            return false;
        }
        if (!nr_budynku.equals(adres.nr_budynku)) {
            return false;
        }
        if (nr_lokalu != null ? !nr_lokalu.equals(adres.nr_lokalu) : adres.nr_lokalu != null) {
            return false;
        }
        return kod_pocztowy.equals(adres.kod_pocztowy);
    }

    @Override
    public int hashCode() {
        int result = miasto.hashCode();
        result = 31 * result + ulica.hashCode();
        result = 31 * result + nr_budynku.hashCode();
        result = 31 * result + (nr_lokalu != null ? nr_lokalu.hashCode() : 0);
        result = 31 * result + kod_pocztowy.hashCode();
        return result;
    }
}
