import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Unesi broj korisnika");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Korisnik korisnici[] = new Korisnik[n];
        for (int i = 0; i < n; i++) {
            String broj = null;
            for(;;){
                System.out.println("Unesite telefonski broj korisnika");
                broj = sc.next();
                if(!Korisnik.validan(broj))
                    break;
                System.out.println("Nekorektan unos, pokusajte ponovo");
            }

            System.out.println("Unesite 1 za PRIPAID korsinika");
            System.out.println("Unesite 2 za POSTPAID korisnika");
            int izbor = sc.nextInt();

            switch (izbor){
                case 1:
                    System.out.println("Unesite kredit ");
                    int kredit = sc.nextInt();
                    String ff[] = new String[3];
                    for (int j = 0; j < ff.length; j++) {
                        System.out.println("Unesite " + (j + 1) + ". f&f broj: ");
                        ff[j] = sc.next();
                        if (Korisnik.validan(ff[j])){
                            System.out.println("Broj nije validan ili ne pripada mrezi");
                            j--;
                        }
                    }
                    korisnici[i] = new Prepaid(broj, kredit, ff);
                    break;

                case 2:
                    korisnici[i] = new Postpaid(broj);
                    break;
                default:
                    System.out.println("Neispravna opcija");
            }
        }
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Korisnik izabrani = korisnici[random.nextInt(korisnici.length)];
            System.out.println(izabrani);

            if(izabrani instanceof Prepaid){
                System.out.println("Unesite vrstu usluge: \"dopuna\", \"sms\", \"razgovor\"");
                String izbor = sc.next();

                if(izbor.equalsIgnoreCase("dopuna")){
                    System.out.println("Unesite sumu: ");
                    ((Prepaid) izabrani).dopuni_kredit(sc.nextInt());
                    System.out.println("Nakon usluge: " + izabrani);

                }
                else if(izbor.equalsIgnoreCase("sms")){
                    izabrani.azurirajRacunSMS();
                    System.out.println("Nakon usluge: " + izabrani);

                }
                else if(izbor.equalsIgnoreCase("razgovor")){
                    System.out.println("Unesite broj koji se poziva ");
                    String broj = sc.next();
                    System.out.println("Unesite trajanje razgovora: sat minut sekunda");
                    int sat = sc.nextInt();
                    int minut = sc.nextInt();
                    int sekunda = sc.nextInt();
                    if(!Vreme.validan(sat, minut, sekunda)){
                        System.out.println("Neispravno trajanje razgovora");
                        i--;
                        continue;
                    }
                    izabrani.azurirajRacunRazgovor(new Razgovor(broj, new Vreme(sat
                    , minut, sekunda)));
                    System.out.println("Nakon usluge: " + izabrani);

                }
                else{
                    System.out.println("Pogresna opcija ");
                    i--;
                    continue;
                }

            }else{
                System.out.println("Unesite sms ili razgovor: ");
                String izbor = sc.next();

                if(izbor.equalsIgnoreCase("sms")){
                    izabrani.azurirajRacunSMS();
                    System.out.println("Nakon usluge " + izabrani);
                }
                else if(izbor.equalsIgnoreCase("razgovor")){
                    System.out.println("Unesite broj koji se poziva");
                    String broj = sc.next();
                    System.out.println("Unesite trajanje razgovora: sat, minut, sekunde");
                    int sat = sc.nextInt();
                    int minut = sc.nextInt();
                    int sekunda = sc.nextInt();

                    if (!Vreme.validan(sat, minut, sekunda))
                    {
                        System.out.println("Neispravno trajanje razgovora");
                        i--;
                        continue;
                    }
                    izabrani.azurirajRacunRazgovor(new Razgovor(broj,
                            new Vreme(sat, minut, sekunda)));
                    System.out.println("Nakon usluge: " + izabrani);

                }
                else {
                    System.out.println("Pogresna opcija");
                    i--;
                }
            }
        }
    }
}
