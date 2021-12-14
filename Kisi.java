
/**
 * Abstract class Kisi - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Kisi
{
    private String id;
    private String adi;
    private String soyadi;

    public Kisi(String id, String adi, String soyadi) {
        this.id = id;
        this.adi = adi;
        this.soyadi = soyadi;
    }

    public Kisi(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }
    
    public void tanitim(){
        System.out.println("ID: " + id + " Ad: " + adi + " Soyad: " + soyadi);
    }
}
