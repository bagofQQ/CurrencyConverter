package conversion.service;

import conversion.model.Conversion;
import conversion.model.ConversionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;

@Service
public class ConService {

    private String KEY_TARGET_AMOUNT = "Целевая сумма";

    private static final String RUR = "rur";
    private static final String USD = "usd";
    private static final String EUR = "eur";
    private static final String GBP = "gbp";

    private double GBPEUR = 1.39;
    private double GBPUSD = 1.17;
    private double GBPRUR = 102.5;

    private double EURUSD = 1.19;
    private double EURRUR = 87.25;

    private double USDRUR = 73.2;

    private final ConversionRepository conversionRepository;
    private HashMap<String, String> conMap = new HashMap<>();


    public ConService(ConversionRepository conversionRepository) {
        this.conversionRepository = conversionRepository;
    }

    public void conversionMethod(Conversion conversion){
        conversionOperation(conversion);
        conversionRepository.save(conversion);
    }

    private void conversionOperation(Conversion conversion){
        switch (conversion.getOriginalAmountName()){
            case RUR:
                switch (conversion.getTargetAmountName()){
                    case RUR:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), 1, RUR));
                        break;
                    case USD:
                        conversion.setTargetAmount(downСonverter(conversion.getOriginalAmount(), USDRUR, USD));
                        break;
                    case EUR:
                        conversion.setTargetAmount(downСonverter(conversion.getOriginalAmount(), EURRUR, EUR));
                        break;
                    case GBP:
                        conversion.setTargetAmount(downСonverter(conversion.getOriginalAmount(), GBPRUR, GBP));
                        break;
                }
                break;
            case EUR:
                switch (conversion.getTargetAmountName()){
                    case RUR:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), EURRUR, RUR));
                        break;
                    case USD:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), EURUSD, USD));
                        break;
                    case EUR:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), 1, EUR));
                        break;
                    case GBP:
                        conversion.setTargetAmount(downСonverter(conversion.getOriginalAmount(), GBPEUR, GBP));
                        break;
                }
                break;
            case USD:
                switch (conversion.getTargetAmountName()){
                    case RUR:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), USDRUR, RUR));
                        break;
                    case USD:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), 1, USD));
                        break;
                    case EUR:
                        conversion.setTargetAmount(downСonverter(conversion.getOriginalAmount(), EURUSD, EUR));
                        break;
                    case GBP:
                        conversion.setTargetAmount(downСonverter(conversion.getOriginalAmount(), GBPUSD, GBP));
                        break;
                }
                break;
            case GBP:
                switch (conversion.getTargetAmountName()){
                    case RUR:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), GBPRUR, RUR));
                        break;
                    case USD:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), GBPUSD, USD));
                        break;
                    case EUR:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), GBPEUR, EUR));
                        break;
                    case GBP:
                        conversion.setTargetAmount(upСonverter(conversion.getOriginalAmount(), 1, GBP));
                        break;
                }
                break;
        }
    }

    private String upСonverter(String originalAmount, double cof, String targetAmountName){
        double originalDoubleAmount = Double.parseDouble(originalAmount);
        DecimalFormat dF = new DecimalFormat( "0.00" );
        double targetDoubleAmount = originalDoubleAmount*cof;
        String targetAmount = dF.format(targetDoubleAmount);
        conMap.put(KEY_TARGET_AMOUNT, targetAmount + " " + targetAmountName);
        return targetAmount;
    }

    private String downСonverter (String originalAmount, double cof, String targetAmountName){
        double originalDoubleAmount = Double.parseDouble(originalAmount);
        DecimalFormat dF = new DecimalFormat( "0.00" );
        double targetDoubleAmount = originalDoubleAmount/cof;
        String targetAmount = dF.format(targetDoubleAmount);
        conMap.put(KEY_TARGET_AMOUNT, targetAmount + " " + targetAmountName);
        return targetAmount;
    }

    public ResponseEntity<String> getTargetAmount(){
        String t = conMap.get(KEY_TARGET_AMOUNT);
        conMap.clear();
        return new ResponseEntity(t, HttpStatus.OK);
    }
}
