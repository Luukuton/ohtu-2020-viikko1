package ohtu.ohtuvarasto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VarastoTest {

    Varasto varasto, varastoEmpty, varastoBalance, varastoBalanceMore, varastoBalanceEmpty, varastoBalanceNeg;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoEmpty = new Varasto(0);
        varastoBalance = new Varasto(10, 1);
        varastoBalanceMore = new Varasto(10, 11);
        varastoBalanceEmpty = new Varasto(0, 10);
        varastoBalanceNeg = new Varasto(10, -1);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonNegatiivinen() {
        double previousBalance = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);

        // Ei muutosta saldossa.
        assertEquals(previousBalance, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaNegatiivinen() {
        double previousBalance = varasto.getSaldo();
        varasto.otaVarastosta(-1);

        // Ei muutosta saldossa.
        assertEquals(previousBalance, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaEnemmanKuinSaldo() {
        double previousBalance = varasto.getSaldo();
        double withdrawnBalance = varasto.otaVarastosta(varasto.getSaldo() + 1);

        // Ei muutosta saldossa.
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(withdrawnBalance, previousBalance, vertailuTarkkuus);
    }

    @Test
    public void lisaaSaldoonEnemmanKuinTilavuus() {
        varasto.lisaaVarastoon(varasto.getTilavuus() + 1);

        // Saldo enintään tilavuus.
        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsitysOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}