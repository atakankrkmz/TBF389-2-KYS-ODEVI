
/**
 * Write a description of class Ogrenci here.
 *
 * @author Atakan Korkmaz
 * @version ()
 */
public class Ogrenci extends KutuphaneUyesi
{
    private String fakulte;
    private String program;
    private String baslangicTarihi;

    public Ogrenci(String id, String adi, String soyadi) {
        super(id, adi, soyadi);
    }
    
    // Overriding
    public void tanitim(){
        System.out.println("ID: " + getAdi() + " Ad: " + this.getAdi() + " Soyad: " + this.getSoyadi() +
                            " Fakulte: " + fakulte + " Program: " + program + " Baslangic Tarihi : " + baslangicTarihi);
    }
}
