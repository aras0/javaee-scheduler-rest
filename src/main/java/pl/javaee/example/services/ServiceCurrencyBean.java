package pl.javaee.example.services;

import java.util.List;

import javax.inject.Inject;

import pl.javaee.example.models.Currency;

public class ServiceCurrencyBean implements ServiceCurrency {

    private List<Currency> currencies;

    @Inject
    SchedulerService service;

    @Override
    public float changeCurrency(String amount, String in, String out) {
        
        float amountOut = 0;
        
        try {
            float amountIn =  new Float(amount.replace(',', '.'));
            
            currencies = service.getCurrencyList();

            if("PLN".contains(out)) {
            	// kwota * kurs / prelicznik 
                for (Currency currencyIn : currencies) {
                	if (in.equals(currencyIn.getCode())){
                        float curseIn = Float.parseFloat(currencyIn.getCourse().replace(',', '.'));
                		amountOut = (amountIn * curseIn) / currencyIn.getCount();
                	}
                }

            } else if ("PLN".contains(in)){
            	//kwota/kurs * przelicznik
                for (Currency currencyOut : currencies) {
                	if (out.equals(currencyOut.getCode())){
                        float curseOut = Float.parseFloat(currencyOut.getCourse().replace(',', '.'));
                		amountOut = (amountIn * currencyOut.getCount()) / curseOut;
                	}
                }
            } else {
	            for (Currency currencyIn : currencies) {
	                for (Currency currencyOut : currencies) {
	
	                    if (in.equals(currencyIn.getCode()) && out.equals(currencyOut.getCode())) {
	                        float curseIn = Float.parseFloat(currencyIn.getCourse().replace(',', '.'));
	                        float curseOut = Float.parseFloat(currencyOut.getCourse().replace(',', '.'));
	
	                        amountOut = (amountIn * curseIn) / currencyIn.getCount();
	                		amountOut = (amountOut * currencyOut.getCount()) / curseOut;
	                    }
	                }
	            }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return amountOut;

    }

}
