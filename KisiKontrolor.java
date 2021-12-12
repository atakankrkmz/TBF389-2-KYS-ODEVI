import java.util.ArrayList;
import Utils.*;
/**
 * Write a description of class KisiKontroller here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class KisiKontrolor
{
    private ArrayList<KutuphaneGorevlisi> kutuphaneGorevlileri;
    private ArrayList<Ogrenci> ogrenciler;
    private ArrayList<OgretimGorevlisi> ogretimGorevlileri;
    private ArrayList<Yazar> yazarlar;

    public KisiKontrolor()
    {
        kutuphaneGorevlileri = new ArrayList<KutuphaneGorevlisi>();
        ogrenciler = new ArrayList<Ogrenci>();
        ogretimGorevlileri = new ArrayList<OgretimGorevlisi>();
        yazarlar = new ArrayList<Yazar>();

        // Dummy Data for ogrenciler
        kisiOlustur(new Ogrenci("og0001", "Atakan", "Korkmaz"));
        kisiOlustur(new Ogrenci("og0002", "Bugra", "Yilmaz"));
        kisiOlustur(new Ogrenci("og0003", "Anil", "Unalan"));
        kisiOlustur(new Ogrenci("og0004", "Dorukhan", "Bozkurt"));
        kisiOlustur(new Ogrenci("og0005", "Canberk", "Ortgen"));
        
        // Dummy Data for ogrenciler
        kisiOlustur(new Yazar("yaz0001", "Victor", "Hugo"));
        kisiOlustur(new Yazar("yaz0002", "Lev", "Tolstoy"));
        kisiOlustur(new Yazar("yaz0003", "Fyodor", "Dostoyevski"));
        kisiOlustur(new Yazar("yaz0004", "William", "Shakespeare"));
    }

    /** Kisi olusturma methodlari **/
    public void kisiOlustur(KutuphaneGorevlisi olusturulacakGorevli){
        kutuphaneGorevlileri.add(olusturulacakGorevli);
    }

    public void kisiOlustur(OgretimGorevlisi olusturulacakGorevli){
        ogretimGorevlileri.add(olusturulacakGorevli);
    }

    public void kisiOlustur(Ogrenci olusturulacakGorevli){
        ogrenciler.add(olusturulacakGorevli);
    }
    
    public void kisiOlustur(Yazar olusturulacakYazar){
        yazarlar.add(olusturulacakYazar);
    }

    /** Kisi silme methodlari **/
    public void kisiSil(KutuphaneGorevlisi silinecekGorevli){
        kutuphaneGorevlileri.remove(silinecekGorevli);
    }

    public void kisiSil(OgretimGorevlisi silinecekGorevli){
        ogretimGorevlileri.remove(silinecekGorevli);
    }

    public void kisiSil(Ogrenci silinecekGorevli){
        ogrenciler.remove(silinecekGorevli);
    }

    /** Kisi sayisi methodlari **/
    public int kutuphaneGorevlisiSayisi(){
        return kutuphaneGorevlileri.size();
    }

    public int ogretimGorevlisiSayisi(){
        return ogretimGorevlileri.size();
    }

    public int ogrenciSayisi(){
        return ogrenciler.size();
    }

    public int kisiSayisi(){
        return ogrenciler.size() + ogretimGorevlileri.size() + kutuphaneGorevlileri.size();
    }

    /** Id'ye gore getirme methodlari **/
    public DataResult<KutuphaneUyesi> getById(String id){
        KutuphaneUyesi kutuphaneUyesi;

        if (id.startsWith("og")) {
            for(Ogrenci ogrenci : ogrenciler){
                if(ogrenci.getId().equals(id)){
                    return new SuccessDataResult<KutuphaneUyesi>(ogrenci);
                }
            }
        }else{
            for(OgretimGorevlisi gorevli : ogretimGorevlileri){
                if(gorevli.getId() == id){
                    return new SuccessDataResult<KutuphaneUyesi>(gorevli);
                }
            }
        }

        return new ErrorDataResult<KutuphaneUyesi>("Kutuphane uyesi bulunamadi!");
    }
    
    /** Isime'ye gore getirme methodlari **/
    public DataResult<Ogrenci> getOgrenciByName(String name){
        KutuphaneUyesi kutuphaneUyesi;
        for(Ogrenci ogrenci : ogrenciler){
            if(ogrenci.getAdi().equals(name)){
                return new SuccessDataResult<Ogrenci>(ogrenci);
            }
        }
        return new ErrorDataResult<Ogrenci>("Kutuphane uyesi bulunamadi!");
    }

    /** Butun kisileri getirme methodlari **/
    public ArrayList<KutuphaneGorevlisi> getKutuphaneGorevlileri(){
        return this.kutuphaneGorevlileri;
    }

    public DataResult<ArrayList<Ogrenci>> getOgrenciler(){
        return new SuccessDataResult<ArrayList<Ogrenci>>(this.ogrenciler);
    }

    public DataResult<ArrayList<OgretimGorevlisi>> getOgretimGorevlisi(){
        return new SuccessDataResult<ArrayList<OgretimGorevlisi>>(this.ogretimGorevlileri);
    }
    
    public DataResult<ArrayList<Yazar>> getYazarlar(){
        return new SuccessDataResult<ArrayList<Yazar>>(this.yazarlar);
    }
}
