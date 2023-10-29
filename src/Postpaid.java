public class Postpaid extends Korisnik{

    private static final double PRETPLATA = 150;
    private static final double RAZGOVOR_U_MREZI = 2.8;
    private static final double RAZGOVOR_NACIONALNI = 5.8;
    private static final double USPOSTAVA = 2.5;
    private static final double SMS = 2.2;
    private double racun;

    public Postpaid(String broj) {
        super(broj);
        racun = PRETPLATA;
    }

    public Postpaid(final Postpaid p){
        super(p.getBroj());
        racun = p.racun;
    }

    @Override
    public void azurirajRacunRazgovor(Razgovor r) {
        double cena = RAZGOVOR_NACIONALNI;
        if(r.getBroj().startsWith("064") || r.getBroj().startsWith("065") ||
        r.getBroj().startsWith("066"))
            cena = RAZGOVOR_U_MREZI;

        double cena_razgovora = (USPOSTAVA + (r.getVreme().getSat() * 3600 + r.getVreme().getMinut() * 60 + r.getVreme().getSekunda()) * cena / 60) * (100 + PDV) / 100.0;

        racun += cena_razgovora;
    }

    @Override
    public void azurirajRacunSMS() {
        racun += SMS * (100 + PDV) / 100.0;
    }

    public String toString(){
        return super.toString() + " racun: " + racun;
    }
}