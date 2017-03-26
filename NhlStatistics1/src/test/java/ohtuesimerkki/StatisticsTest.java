package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilja Häkkinen
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Gretzky", "EDM", 52, 163));
            players.add(new Player("Lafleur", "MTL", 56, 80));
            players.add(new Player("Lemieux", "PIT", 85, 114));
            players.add(new Player("Yzerman", "DET", 65, 90));
            players.add(new Player("Esposito", "CHI", 76, 76));
            players.add(new Player("Orr", "BOS", 37, 102));
            players.add(new Player("Béliveau", "MTL", 61, 63));
            players.add(new Player("H. Richard", "MTL", 56, 53));
            players.add(new Player("M. Richard", "MTL", 38, 36));

            return players;
        }

    };

    Statistics stats;

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void searchPalauttaaNullinJosPelaajaaEiLoydy() {
        assertNull(stats.search("diipadaapa"));
    }

    @Test
    public void searchPalauttaaHaetunPelaajanOikein() {
        Player pelaaja = stats.search("Lemieux");

        assertNotNull(pelaaja);
        assertEquals("Lemieux", pelaaja.getName());
        assertEquals("PIT", pelaaja.getTeam());
        assertEquals(85, pelaaja.getGoals());
        assertEquals(114, pelaaja.getAssists());
        assertEquals(199, pelaaja.getPoints());
    }

    @Test
    public void teamPalauttaaTyhjanListanJosJoukkuettaEiLoydy() {
        List<Player> joukkue = stats.team("HIFK");

        assertEquals(0, joukkue.size());
    }

    @Test
    public void teamPalauttaaHaetunJoukkueenPelaajatOikein() {
        List<Player> joukkue = stats.team("MTL");
        assertEquals(4, joukkue.size());

        String[] tulos = {"Lafleur", "Béliveau", "H. Richard", "M. Richard"};
        for (int i = 0; i < tulos.length; i++) {
            assertEquals(tulos[i], joukkue.get(i).getName());
        }
    }

    @Test
    public void topScorersPalauttaaTyhjanListanJosHowManyNegatiivinen() {
        List<Player> pelaajat = stats.topScorers(-1);
        assertEquals(0, pelaajat.size());
    }

    @Test
    public void topScorersPalauttaaYhdenPelaajanJosHowManyNolla() {
        // hmm, lienee bugi?
        List<Player> pelaajat = stats.topScorers(0);
        assertEquals(1, pelaajat.size());

        String rivi = pelaajat.get(0).toString();
        assertEquals("Gretzky              EDM 52 + 163 = 215", rivi);
    }

    @Test
    public void topScorersPalauttaaOikeanMaaranPelaajia() {
        // metodin ehdossa pitäisi varmaan olla "howMany>0" eikä ">=" ...
        List<Player> pelaajat = stats.topScorers(4);
        assertEquals(5, pelaajat.size());

        int[] pisteet = {215, 199, 155, 152, 139};
        for (int i = 0; i < pisteet.length; i++) {
            assertEquals(pisteet[i], pelaajat.get(i).getPoints());
        }
    }
}
