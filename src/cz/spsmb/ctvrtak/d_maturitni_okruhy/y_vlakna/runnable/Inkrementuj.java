package cz.spsmb.ctvrtak.d_maturitni_okruhy.y_vlakna.runnable;

import cz.spsmb.ctvrtak.d_maturitni_okruhy.y_vlakna.APriklad;

public class Inkrementuj implements Runnable {
    public static  int tmp;
    // pokud použiji Runnable namísto Thread, musím implementovat metodu
    // start následujícím způsobem:
    private Thread zobrazVl = null;
    public void start() {
        this.zobrazVl = new Thread(this);
        this.zobrazVl.start();
    }

    @Override
    public void run() {
        while(true) {
            Inkrementuj.tmp += 3;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
