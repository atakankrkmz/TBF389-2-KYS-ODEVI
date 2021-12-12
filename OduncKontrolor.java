import java.util.ArrayList;
import java.time.LocalDate;
import Utils.*;
/**
 * Write a description of class OduncKontrolor here.
 *
 * @author Atakan Korkmaz
 * @version ()
 */
public class OduncKontrolor
{
    private KaynakKontrolor kontrolor;
    private YaziliKaynakRepository yaziliKaynakRepository;

    public OduncKontrolor()
    {
        kontrolor = new KaynakKontrolor();
        yaziliKaynakRepository = new YaziliKaynakRepository();
    }
    
    public Result yaziliKaynakOduncVer(YaziliKaynak k, KutuphaneUyesi alacak){
        YaziliKaynakDurum durum = k.getDurum();
        if(durum == YaziliKaynakDurum.RAFTA){
            
            YaziliKaynak updatedKaynak = new YaziliKaynak(k.getId(), k.getAdi(),k.getYazar());
            updatedKaynak.setDurum(YaziliKaynakDurum.OGRENCIDE);
            updatedKaynak.setOduncAlan(alacak);
            updatedKaynak.setOduncTarihi(LocalDate.now());
            String msg = k.getAdi() + " adlı kaynak, " + alacak.getAdi() + " " +
                    alacak.getSoyadi() + " kişisine '" + updatedKaynak.getOduncTarihi().plusWeeks(2) +
                    "' tarihine kadar odunc verilmistir.";
            Result res = yaziliKaynakRepository.updateYaziliKaynak(k, updatedKaynak);
            if (res.isSuccess()) {
                return new SuccessResult(msg);
            }
             return new ErrorResult(res.getMessage());
        } else if(durum == YaziliKaynakDurum.OGRENCIDE){
            return new ErrorResult("Kaynak ogrencide, ancak rezerve edilebilir.");
        } else if(durum == YaziliKaynakDurum.REZERVE){
            return new ErrorResult("Kaynak rezerve, bir sure sonra tekrar deneyiniz.");
        } else if(durum == YaziliKaynakDurum.ODUNCALINAMAZ){
            return new ErrorResult("Bu kaynak odunc alinamaz!");
        } else{
            return new ErrorResult("Hatali islem turu secildi");
        }
    }
    
    public Result yaziliKaynakRezerveEt(YaziliKaynak k, KutuphaneUyesi alacak){
        YaziliKaynakDurum durum = k.getDurum();
        if(durum == YaziliKaynakDurum.RAFTA){
            return new ErrorResult("Kaynak rafta, odunc alabilirsiniz.");
        } else if(durum == YaziliKaynakDurum.OGRENCIDE){
            YaziliKaynak updatedKaynak = new YaziliKaynak(k.getId(), k.getAdi(), k.getYazar(), k.getOduncTarihi(), k.getOduncAlan(), YaziliKaynakDurum.REZERVE);
            KutuphaneUyesi owner = k.getOduncAlan();
            String msg = "Kaynak '" + owner.getAdi() + " " + owner.getSoyadi() 
                + "' adli kişinin tesliminden sonra" + " ödünç verilmek üzere "+
                alacak.getAdi() + " " + alacak.getSoyadi() + "' kişiye rezerve edildi";
             
            Result res = yaziliKaynakRepository.updateYaziliKaynak(k, updatedKaynak);
            if (res.isSuccess()) {
                return new SuccessResult(msg);
            }
             return new ErrorResult(res.getMessage());
        } else if(durum == YaziliKaynakDurum.REZERVE){
            return new ErrorResult("Kaynak rezerve, bir sure sonra tekrar deneyiniz.");
        } else if(durum == YaziliKaynakDurum.ODUNCALINAMAZ){
            return new ErrorResult("Bu kaynak odunc alinamaz!");
        } else{
            return new ErrorResult("Hatali islem turu secildi");
        }
    }
}
