import FakeDB.FileOperations;
import Utils.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class YaziliKaynak here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class YaziliKaynakRepository
{
    FileOperations fileOperations;
    KisiKontrolor kisiKontrolor;

    public YaziliKaynakRepository(){
        fileOperations = new FileOperations("db_yazili_kaynak.txt");
        fileOperations.openFile();
        kisiKontrolor = new KisiKontrolor();
    }

    public DataResult<List<YaziliKaynak>> getAll(){
        List<YaziliKaynak> kaynaklar = new ArrayList<YaziliKaynak>();
        List<String> lines = fileOperations.readFile().getData();
        for (String line : lines) {
            DataResult<YaziliKaynak> result = lineToYaziliKaynak(line);
            if(!result.isSuccess()){
                continue;
            }
            kaynaklar.add(result.getData());
        }
        return new SuccessDataResult<List<YaziliKaynak>>(kaynaklar);
    }

    public DataResult<YaziliKaynak> getById(String id){
        List<String> lines = fileOperations.readFile().getData();
        for (String line : lines) {
            String[] parsedLine = line.split("~");
            if (parsedLine[0].equals(id)) {
                DataResult<YaziliKaynak> result = lineToYaziliKaynak(line);
                if(!result.isSuccess()){
                    return new ErrorDataResult(result.getMessage());
                }
                return new SuccessDataResult<>(result.getData());
            }
        }
        return new ErrorDataResult<>("YaziliKaynak can't be found!");
    }

    public DataResult<YaziliKaynak> getByName(String kaynakAdi){
        List<String> lines = fileOperations.readFile().getData();
        for (String line : lines) {
            String[] parsedLine = line.split("~");
            if (parsedLine[1].equals(kaynakAdi)) {
                DataResult<YaziliKaynak> result = lineToYaziliKaynak(line);
                if(!result.isSuccess()){
                    return new ErrorDataResult(result.getMessage());
                }
                return new SuccessDataResult<>(result.getData());
            }
        }
        return new ErrorDataResult<>("YaziliKaynak can't be found!");
    }

    public DataResult<List<YaziliKaynak>> getByAuthor(String yazarId){
        ArrayList<YaziliKaynak> kaynaklar = new ArrayList<>();
        List<String> lines = fileOperations.readFile().getData();
        for (String line : lines) {
            String[] parsedLine = line.split("~");
            if (parsedLine[2].equals(yazarId)) {
                DataResult<YaziliKaynak> result = lineToYaziliKaynak(line);
                if(!result.isSuccess()){
                    return new ErrorDataResult(result.getMessage());
                }
                kaynaklar.add(result.getData());
            }
        }
        return new ErrorDataResult<>("YaziliKaynak can't be found!");
    }

    public Result addYaziliKaynak(YaziliKaynak eklenecekYaziliKaynak){
       String kaynak = yaziliKaynakToLine(eklenecekYaziliKaynak);
       return fileOperations.writeToFile(kaynak);
    }
    
    public Result deleteYaziliKaynak(YaziliKaynak silinecekYaziliKaynak){
        String content = yaziliKaynakToLine(silinecekYaziliKaynak);
        return fileOperations.deleteFromFile(content);
    }
    
    public Result updateYaziliKaynak(YaziliKaynak guncellenecekYaziliKaynak, YaziliKaynak guncellenmisYaziliKaynak){
        Result deleteResult = deleteYaziliKaynak(guncellenecekYaziliKaynak);
        if(!deleteResult.isSuccess()) return deleteResult;
        Result addResult = addYaziliKaynak(guncellenmisYaziliKaynak);
        if(!addResult.isSuccess()) return addResult;
        
        return new SuccessResult("Kaynak basariyla guncellendi!");
    }
    
    private String yaziliKaynakToLine(YaziliKaynak k) {
        String kaynak = "";
        kaynak += k.getId() + "~";
        kaynak += k.getAdi() + "~";
        kaynak += k.getYazar().getId() + "~";
        kaynak += k.getOduncTarihi() + "~";
        KutuphaneUyesi og = k.getOduncAlan();
        if(og.getId() != null){
            kaynak += og.getId() + "~";
        } else {kaynak += "null~";}
        kaynak += k.getDurum().toString();
        
        return kaynak;
    }
    
    private DataResult<YaziliKaynak> lineToYaziliKaynak(String line) {
        String[] parsedLine = line.split("~");
        int id = Integer.parseInt(parsedLine[0]);
        String adi = parsedLine[1];
        Yazar yazar = new Yazar(parsedLine[2]);
        LocalDate oduncTarihi;
        if (parsedLine[3].equals("null")) {
            oduncTarihi = null;
        } else {
            oduncTarihi = LocalDate.parse(parsedLine[3]);
        }
        DataResult<KutuphaneUyesi> result = kisiKontrolor.getById(parsedLine[4]);
        if(!result.isSuccess()){
            return new ErrorDataResult<YaziliKaynak>("Hatayla karsilasildi");
        }
        KutuphaneUyesi kutuphaneUyesi = result.getData();
        YaziliKaynakDurum yaziliKaynakDurum = YaziliKaynakDurum.valueOf(parsedLine[5]);
        return new SuccessDataResult<YaziliKaynak>(new YaziliKaynak(id,adi,yazar,oduncTarihi,kutuphaneUyesi,yaziliKaynakDurum));
    }

}
