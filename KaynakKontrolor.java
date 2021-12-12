import java.util.*;
import java.time.LocalDate;
import Utils.*;

/**
 * Write a description of class KaynakKontrolor here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class KaynakKontrolor
{
    private ArrayList<DijitalKaynak> dijitalKaynaklar;
    private ArrayList<YaziliKaynak> yaziliKaynaklar;
    private YaziliKaynakRepository yaziliKaynakRepository;
    
    public KaynakKontrolor()
    {
        dijitalKaynaklar = new ArrayList<DijitalKaynak>();
        yaziliKaynaklar = new ArrayList<YaziliKaynak>();
        yaziliKaynakRepository = new YaziliKaynakRepository();
        
        DataResult<List<YaziliKaynak>> res = yaziliKaynakRepository.getAll();
        yaziliKaynaklar = new ArrayList<>(res.getData());
    }
    
    public void kaynakOlustur(DijitalKaynak olusturulacakKaynak){
        dijitalKaynaklar.add(olusturulacakKaynak);
    }
    
    public void kaynakOlustur(YaziliKaynak olusturulacakKaynak){
        yaziliKaynaklar.add(olusturulacakKaynak);
    }
    
    public void kaynakSil(DijitalKaynak silinecekKaynak){
        dijitalKaynaklar.remove(silinecekKaynak);
    }
    
    public void kaynakSil(YaziliKaynak silinecekKaynak){
        yaziliKaynaklar.remove(silinecekKaynak);
    }
    
    public int kaynakSayisi(){
        return yaziliKaynaklar.size() + dijitalKaynaklar.size();
    }

    public DataResult<ArrayList<DijitalKaynak>> getDijitalKaynaklar(){
        return new SuccessDataResult<ArrayList<DijitalKaynak>>(this.dijitalKaynaklar);
    }
    
    public DataResult<ArrayList<YaziliKaynak>> getYaziliKaynaklar(){
        DataResult<List<YaziliKaynak>> res = yaziliKaynakRepository.getAll();
        yaziliKaynaklar = new ArrayList<>(res.getData());
        return new SuccessDataResult<ArrayList<YaziliKaynak>>(yaziliKaynaklar);
    }
    
    public DataResult<YaziliKaynak> getYaziliKaynakByName(String name){
        DataResult<YaziliKaynak> res = yaziliKaynakRepository.getByName(name);
        YaziliKaynak kaynak = res.getData();
        return new SuccessDataResult<YaziliKaynak>(kaynak);
    }
    
    public Result yaziliKaynakGuncelle(YaziliKaynak oldKaynak, YaziliKaynak newKaynak){
        String msg = "'" + newKaynak.getAdi() + "' adli kaynak güncellendi! ";
        Result res = yaziliKaynakRepository.updateYaziliKaynak(oldKaynak, newKaynak);
        if (res.isSuccess()) {
            return new SuccessResult(msg);
        }
         return new ErrorResult(res.getMessage());
    }
    
    
}
