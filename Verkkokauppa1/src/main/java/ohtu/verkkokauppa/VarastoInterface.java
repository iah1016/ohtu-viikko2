
package ohtu.verkkokauppa;

/**
 *
 * @author Ilja Häkkinen
 */
public interface VarastoInterface {

    Tuote haeTuote(int id);

    void otaVarastosta(Tuote t);

    void palautaVarastoon(Tuote t);

    int saldo(int id);
    
}
