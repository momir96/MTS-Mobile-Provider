public abstract class Korisnik {

    private String broj;
    static final int PDV = 18;

    public Korisnik(String broj){
        this.broj = broj;
    }

    public Korisnik(final Korisnik k){
        this(k.broj);
    }

    public String getBroj() {
        return broj;
    }

    public abstract void azurirajRacunRazgovor(Razgovor r);

    public abstract void azurirajRacunSMS();

    public static boolean validan(String broj){
        //ako nisu sve cifre broj nije validan
        for (int i = 0; i < broj.length(); i++)
            if (!Character.isDigit(broj.charAt(i)))
                return false;

        //treba da pocinje sa 064 065 ili 066, duzine 9 ili 10 cifara
        return broj.startsWith("064") || broj.startsWith("065") ||
                broj.startsWith("066") && (broj.length() == 9 || broj.length() == 10);
    }

    public String toString(){
        return "Korisnik: " + broj;
    }

}
