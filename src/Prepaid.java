public class Prepaid extends Korisnik{

    private static final double RAZGOVOR = 7.5;
    private static final double FF = 4.5;
    private static final double USPOSTAVA = 2.8;
    private final double SMS = 0.9;

    private double kredit;
    private String[] ff_brojevi = new String[3];

    public Prepaid(String broj, double kredit, String[] ff) {
        super(broj);
        this.kredit = kredit;
        for (int i = 0; i < ff_brojevi.length; i++)
            ff_brojevi[i] = ff[i];
    }

    public Prepaid(final Prepaid p){
        super(p.getBroj());
        kredit = p.kredit;
        for (int i = 0; i < ff_brojevi.length; i++)
            ff_brojevi[i] = p.ff_brojevi[i];
    }

    public void dopuni_kredit(int dopuna){
        kredit += dopuna;
    }

    //prvi minut se zaokruzuje a nakon toga poziv se tarifira na 30 sekundi
    public void azurirajRacunRazgovor(Razgovor r) {
        //treba razlikovati ff brojeve od ostalih
        double cena = RAZGOVOR;
        Vreme vreme = null;

        if(r.getVreme().getSat() == 0 && r.getVreme().getMinut() == 0)
            vreme = new Vreme(0,1,0);
        else if (r.getVreme().getSekunda() > 30)
            vreme = r.getVreme().sledeciMinut();
        else
            vreme = new Vreme(r.getVreme().getSat(), r.getVreme().getMinut(), 0);

        for (int i = 0; i < ff_brojevi.length; i++)
            if(r.getBroj().equals(ff_brojevi[i])){
                cena = FF;
                break;
            }

        double cena_razgovora = (USPOSTAVA + (vreme.getSat() * 60 + vreme.getMinut()) * cena)
                * (100 + PDV) / 100.0;
            if(kredit < cena_razgovora)
                kredit = 0;
            else
                kredit -= cena_razgovora;
    }

    @Override
    public void azurirajRacunSMS() {
        kredit -= SMS * (100+PDV) /100.0;
        if (kredit < 0)
            kredit = 0;
    }

    public String toString(){
        String ffString = "";
        for (int i = 0; i < ff_brojevi.length; i++)
            if(ff_brojevi[i] != null)
                ffString += ff_brojevi[i] + " ";

        return super.toString() + " kredit: " + kredit + "\n ff-brojevi";
    }
}
