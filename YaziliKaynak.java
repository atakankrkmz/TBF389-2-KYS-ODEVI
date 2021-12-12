 import java.time.LocalDate;
/**
 * Write a description of class YaziliKaynak here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class YaziliKaynak extends Kaynak
{
    private LocalDate oduncTarihi;
    private KutuphaneUyesi oduncAlan;
    private YaziliKaynakDurum durum;

    public YaziliKaynak(int id, String adi, Yazar yazar, LocalDate oduncTarihi, KutuphaneUyesi oduncAlan, YaziliKaynakDurum durum) {
        super(id, adi, yazar);
        this.oduncTarihi = oduncTarihi;
        this.oduncAlan = oduncAlan;
        this.durum = durum;
    }
    public YaziliKaynak(int id, String adi, Yazar yazar, YaziliKaynakDurum durum) {
        super(id, adi, yazar);
        this.oduncAlan = oduncAlan;
        this.durum = durum;
    }
    
    public YaziliKaynak(int id, String adi, Yazar yazar) {
        super(id, adi, yazar);
        this.oduncAlan = oduncAlan;
    }
    
    public LocalDate getOduncTarihi(){
        return this.oduncTarihi;
    }

    public void setOduncTarihi(LocalDate oduncTarihi){
        this.oduncTarihi = oduncTarihi;
    }

    public KutuphaneUyesi getOduncAlan(){
        return this.oduncAlan;
    }

    public void setOduncAlan(KutuphaneUyesi oduncAlan){
        this.oduncAlan = oduncAlan;
    }

    public YaziliKaynakDurum getDurum(){
        return this.durum;
    }

    public void setDurum(YaziliKaynakDurum durum){
        this.durum = durum;
    }
}
