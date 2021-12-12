import java.time.LocalDate;
/**
 * Abstract class Personel - write a description of the class here
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class OgretimGorevlisi extends KutuphaneUyesi
{
    private String departman;
    private String unvan;
    private LocalDate baslangicTarihi;

    public OgretimGorevlisi(String id) {
        super(id);
    }

}
