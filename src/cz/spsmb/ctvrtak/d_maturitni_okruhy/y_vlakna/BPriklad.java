package cz.spsmb.ctvrtak.d_maturitni_okruhy.y_vlakna;
//vytvořte 2 vlákna, které budou pravidelně zobrazovat lichá (první vlákno) a sudá (druhé vlákno) číslo v posloupnosti.
// využijte předávání řízení metodou yeld


public class BPriklad {
    public static void main(String[] args) {
        Thread vlakno1 = new Thread() {
            private int v = 1;
            @Override
            public void run() {
                while(true) {
                    System.out.println("Vlákno1: "+v);
                    v+=2;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Thread.yield();
                }
            }
        };
        Thread vlakno2 = new Thread() {
            private int v = 2;
            @Override
            public void run() {
                while(true) {
                    System.out.println("Vlákno2: "+v);
                    v+=2;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Thread.yield();
                }
            }
        };
        vlakno1.start();
        vlakno2.start();


    }

}
