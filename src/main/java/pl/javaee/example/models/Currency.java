package pl.javaee.example.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlRootElement(name = "pozycja")
public class Currency {

    private String name;
    
    private int count;
    
    private String code;
    
    private String course;

    public String getName() {
        return name;
    }
    
    @XmlElement(name = "nazwa_waluty")
    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    @XmlElement(name = "przelicznik")
    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }
    
    @XmlElement(name = "kod_waluty")
    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse() {
        return course;
    }

    @XmlElement(name = "kurs_sredni")
    //@XmlSchemaType(name = "float")
    public void setCourse(String course) {
        this.course = course;
    }
}

