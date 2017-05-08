package pl.javaee.example.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tabela_kursow")
public class CurrencyTable {

    private String number;
    
    private String data;
    
    private List<Currency> curencyList;

    public String getNumber() {
        return number;
    }
    
    @XmlElement(name="numer_tabeli")
    public void setNumber(String number) {
        this.number = number;
    }

    public String getData() {
        return data;
    }
    
    @XmlElement(name="data_publikacji")
    public void setData(String data) {
        this.data = data;
    }

    public List<Currency> getCurencyList() {
        return curencyList;
    }

    @XmlElement(name="pozycja")
    public void setCurencyList(List<Currency> curencyList) {
        this.curencyList = curencyList;
    }
}

