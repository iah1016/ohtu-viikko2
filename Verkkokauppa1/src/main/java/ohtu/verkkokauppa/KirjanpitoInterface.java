
package ohtu.verkkokauppa;

import java.util.ArrayList;

/**
 *
 * @author Ilja Häkkinen
 */
public interface KirjanpitoInterface {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
