package BADA_proj.bilety;

import BADA_proj.wydarzenia.Wydarzenie;

public class Bilet {

    private int id_biletu;
    private Float cena;
    private String rodzaj;
    private String strefa;
    private String ozn_miejsca;
    private Integer id_klienta;
    private int id_wydarzenia;
    private Integer id_kasy;
    private Wydarzenie wydarzenie;
    private Integer ilosc = 0;

    public Bilet() {
    }

    public Bilet(Float cena, String rodzaj, String strefa, String ozn_miejsca, Integer id_klienta, int id_wydarzenia, Integer id_kasy) {
        this.cena = cena;
        this.rodzaj = rodzaj;
        this.strefa = strefa;
        this.ozn_miejsca = ozn_miejsca;
        this.id_klienta = id_klienta;
        this.id_wydarzenia = id_wydarzenia;
        this.id_kasy = id_kasy;
    }

    public Bilet(int id_biletu, Float cena, String rodzaj, String strefa, String ozn_miejsca, Integer id_klienta, int id_wydarzenia, Integer id_kasy) {
        this.id_biletu = id_biletu;
        this.cena = cena;
        this.rodzaj = rodzaj;
        this.strefa = strefa;
        this.ozn_miejsca = ozn_miejsca;
        this.id_klienta = id_klienta;
        this.id_wydarzenia = id_wydarzenia;
        this.id_kasy = id_kasy;
    }

    public int getId_biletu() {
        return id_biletu;
    }

    public void setId_biletu(int id_biletu) {
        this.id_biletu = id_biletu;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getStrefa() {
        return strefa;
    }

    public void setStrefa(String strefa) {
        this.strefa = strefa;
    }

    public String getOzn_miejsca() {
        return ozn_miejsca;
    }

    public void setOzn_miejsca(String ozn_miejsca) {
        this.ozn_miejsca = ozn_miejsca;
    }

    public Integer getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(Integer id_klienta) {
        this.id_klienta = id_klienta;
    }

    public int getId_wydarzenia() {
        return id_wydarzenia;
    }

    public void setId_wydarzenia(int id_wydarzenia) {
        this.id_wydarzenia = id_wydarzenia;
    }

    public Integer getId_kasy() {
        return id_kasy;
    }

    public void setId_kasy(Integer id_kasy) {
        this.id_kasy = id_kasy;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Wydarzenie getWydarzenie() {
        return wydarzenie;
    }

    public void setWydarzenie(Wydarzenie wydarzenie) {
        this.wydarzenie = wydarzenie;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "id_biletu=" + id_biletu +
                ", cena=" + cena +
                ", rodzaj='" + rodzaj + '\'' +
                ", strefa='" + strefa + '\'' +
                ", ozn_miejsca='" + ozn_miejsca + '\'' +
                ", id_klienta=" + id_klienta +
                ", id_wydarzenia=" + id_wydarzenia +
                ", id_kasy=" + id_kasy +
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

        Bilet bilet = (Bilet) o;

        if (id_wydarzenia != bilet.id_wydarzenia) {
            return false;
        }
        if (rodzaj != null ? !rodzaj.equals(bilet.rodzaj) : bilet.rodzaj != null) {
            return false;
        }
        if (strefa != null ? !strefa.equals(bilet.strefa) : bilet.strefa != null) {
            return false;
        }
        return id_klienta != null ? id_klienta.equals(bilet.id_klienta) : bilet.id_klienta == null;
    }

    @Override
    public int hashCode() {
        int result = rodzaj != null ? rodzaj.hashCode() : 0;
        result = 31 * result + (strefa != null ? strefa.hashCode() : 0);
        result = 31 * result + (id_klienta != null ? id_klienta.hashCode() : 0);
        result = 31 * result + id_wydarzenia;
        return result;
    }
}
