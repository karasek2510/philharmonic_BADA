package BADA_proj.sale;

public class Sala {

    private int id_sali;
    private String ozn_sali;
    private int liczba_miejsc_strefa_a;
    private int liczba_miejsc_strefa_b;
    private int liczba_miejsc_strefa_c;
    private int id_filharmonii;

    public Sala() {
    }

    public Sala(String ozn_sali,
                int liczba_miejsc_strefa_a,
                int liczba_miejsc_strefa_b,
                int liczba_miejsc_strefa_c,
                int id_filharmonii) {
        this.ozn_sali = ozn_sali;
        this.liczba_miejsc_strefa_a = liczba_miejsc_strefa_a;
        this.liczba_miejsc_strefa_b = liczba_miejsc_strefa_b;
        this.liczba_miejsc_strefa_c = liczba_miejsc_strefa_c;
        this.id_filharmonii = id_filharmonii;
    }

    public Sala(int id_sali, String ozn_sali,
                int liczba_miejsc_strefa_a,
                int liczba_miejsc_strefa_b,
                int liczba_miejsc_strefa_c,
                int id_filharmonii) {
        this.id_sali = id_sali;
        this.ozn_sali = ozn_sali;
        this.liczba_miejsc_strefa_a = liczba_miejsc_strefa_a;
        this.liczba_miejsc_strefa_b = liczba_miejsc_strefa_b;
        this.liczba_miejsc_strefa_c = liczba_miejsc_strefa_c;
        this.id_filharmonii = id_filharmonii;
    }

    public int getId_sali() {
        return id_sali;
    }

    public void setId_sali(int id_sali) {
        this.id_sali = id_sali;
    }

    public String getOzn_sali() {
        return ozn_sali;
    }

    public void setOzn_sali(String ozn_sali) {
        this.ozn_sali = ozn_sali;
    }

    public int getLiczba_miejsc_strefa_a() {
        return liczba_miejsc_strefa_a;
    }

    public void setLiczba_miejsc_strefa_a(int liczba_miejsc_strefa_a) {
        this.liczba_miejsc_strefa_a = liczba_miejsc_strefa_a;
    }

    public int getLiczba_miejsc_strefa_b() {
        return liczba_miejsc_strefa_b;
    }

    public void setLiczba_miejsc_strefa_b(int liczba_miejsc_strefa_b) {
        this.liczba_miejsc_strefa_b = liczba_miejsc_strefa_b;
    }

    public int getLiczba_miejsc_strefa_c() {
        return liczba_miejsc_strefa_c;
    }

    public void setLiczba_miejsc_strefa_c(int liczba_miejsc_strefa_c) {
        this.liczba_miejsc_strefa_c = liczba_miejsc_strefa_c;
    }

    public int getId_filharmonii() {
        return id_filharmonii;
    }

    public void setId_filharmonii(int id_filharmonii) {
        this.id_filharmonii = id_filharmonii;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id_sali=" + id_sali +
                ", ozn_sali='" + ozn_sali + '\'' +
                ", liczba_miejsc_strefa_a=" + liczba_miejsc_strefa_a +
                ", liczba_miejsc_strefa_b=" + liczba_miejsc_strefa_b +
                ", liczba_miejsc_strefa_c=" + liczba_miejsc_strefa_c +
                ", id_filharmonii=" + id_filharmonii +
                '}';
    }
}
