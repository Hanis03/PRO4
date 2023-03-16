package cz.spsmb.ctvrtak.d_maturitni_okruhy.y_vlakna;

import cz.spsmb.ctvrtak.d_maturitni_okruhy.y_vlakna.runnable.Inkrementuj;

// Vytvořte vlákno, které bude periodicky zvyšovat statickou proměnnou tmp
// o vámi zvolenou hodnotu každých 200ms

// Vytvořte a spusťte nové vlákno, které bude čekat na vstup od uživatele a po
// každém zadání celočíselného čísla vypíše toto číslo a stav tmp.



public class APriklad {
    public static  int tmp;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                while(true) {
                    APriklad.tmp += 3;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        for (int i=0; i<100; i++) {
            System.out.println(APriklad.tmp);
            Thread.sleep(100);
        }
        t.join(30);
    }

}
