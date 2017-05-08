package pl.javaee.example.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pl.javaee.example.models.Currency;
import pl.javaee.example.models.CurrencyTable;

@Singleton
public class SchedulerService {

	private final Logger log = Logger.getLogger(getClass().getName());

	
    public CurrencyTable currencyTable;
    
    private List<Currency> currencyList;
    
    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
	
	public void getCurreny(String urlString) {
		try {

			URL url = new URL(urlString);

			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.addRequestProperty("User-Agent", "Mozilla/4.76");
			InputStream is = http.getInputStream();

			JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyTable.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CurrencyTable currencyTable = (CurrencyTable) jaxbUnmarshaller.unmarshal(is);
			this.currencyList = currencyTable.getCurencyList();
			for (Currency c : currencyList)
				log.info("c:" + c.getCode() + " " + c.getCount() + " " + c.getCourse() + " " + c.getName());

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
