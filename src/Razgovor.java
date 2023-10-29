public class Razgovor {

    private String broj;
    private Vreme vreme;

    public Razgovor(String broj, Vreme vreme) {
        this.broj = broj;
        this.vreme = new Vreme(vreme);
    }

    public Razgovor(final Razgovor r){
        this(r.broj, r.vreme);
    }

    public String getBroj() {
        return broj;
    }

    public Vreme getVreme() {
        return vreme;
    }

    public String toString(){
        return "Razgovor sa: " + broj + ", trajao je: " + vreme;
    }
}
