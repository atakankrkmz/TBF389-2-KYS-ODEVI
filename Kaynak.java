/**
 * Abstract class Kaynak - write a description of the class here
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public abstract class Kaynak
{
    private int id;
    private String adi;
    private Yazar yazar;

    public Kaynak(int id, String adi, Yazar yazar) {
        this.id = id;
        this.adi = adi;
        this.yazar = yazar;
    }
    
    
    
    public String bilgileriGetir(){
        String bilgi = "ID : " + id;
        bilgi = bilgi + "\nAdi : " + adi;
        bilgi = bilgi + "\nYazar : " + yazar;
        return bilgi;
    }
    
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getAdi(){
        return this.adi;
    }

    public void setAdi(String adi){
        this.adi = adi;
    }

    public Yazar getYazar(){
        return this.yazar;
    }

    public void setYazar(Yazar yazar){
        this.yazar = yazar;
    }
}
