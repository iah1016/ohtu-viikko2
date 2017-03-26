
package ohtu.verkkokauppa;

/**
 *
 * @author Ilja Häkkinen
 */
public interface PankkiInterface {

    boolean tilisiirto(
            String nimi, int viitenumero, String tililta, String tilille,
            int summa);

}
