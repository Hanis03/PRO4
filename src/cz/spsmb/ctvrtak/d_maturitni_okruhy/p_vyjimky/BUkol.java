package cz.spsmb.ctvrtak.d_maturitni_okruhy.p_vyjimky;
// 1. Vytvořte vlastní synchronní (kontrolovanou) výjimku dle vašeho uvážení
// 2. Použijte tuto výjimku tak, že jí v jedné metodě vyhodíte a v jiné metodě pomocí
//    try/catch/finally ošetříte
// 3. zároveň s ošetřením výjimky jí předejte i výše.

class MojeVyjimka extends Exception{
    public MojeVyjimka(String vyjimka){
        super(String.format("Moje Vyjimka: %s", vyjimka));
    }
}

public class BUkol{
    public static int faktorial(int f) throws MojeVyjimka{
        if(f < 0 || f > 31){
            throw new MojeVyjimka("Číslo musí být pouze v intervalu <0,31>.");
        }
        int fakt = 1;
        for(int i = 2; i <= f; i++){
            fakt *= i;
        }
        return fakt;
    }
    public static void main(String[] args) throws MojeVyjimka {
        int faktorial = 4;
        try {
            int n = faktorial(faktorial);
            System.out.printf("%d! = %d\n", faktorial, n);
        } catch (MojeVyjimka m){
            System.out.println("Chyba: " + m);
            throw new MojeVyjimka("Číslo musí být pouze v intervalu <0,31>.");
        }
    }
}

