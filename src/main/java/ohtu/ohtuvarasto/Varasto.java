package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private final double tilavuus; // paljonko varastoon mahtuu,  > 0
    private double saldo; // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(double tilavuus) {
        this.tilavuus = Math.max(tilavuus, 0.0);
        saldo = 0;
    }

    public Varasto(double tilavuus, double alkuSaldo) {
        this.tilavuus = Math.max(tilavuus, 0.0);

        if (alkuSaldo < 0.0) {
            this.saldo = 0.0;
        } else {
            this.saldo = Math.min(alkuSaldo, tilavuus);
        }
    }

    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {
        return tilavuus - saldo;
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        if (maara < 0) return;

        if (maara <= paljonkoMahtuu()) {
            saldo = saldo + maara;
        } else {
            saldo = tilavuus; // täyteen ja ylimäärä hukkaan!
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) return 0.0;

        if (maara > saldo) {
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;
            return kaikkiMitaVoidaan;
        }

        saldo = saldo - maara;  // vähennetään annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}
