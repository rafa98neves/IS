package org;

import org.CarXML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso(CarXML.class)
public class OwnershipXML extends ArrayList<CarXML> {

    @XmlElement()
    public ArrayList<CarXML> getCars(){return this; }

}
