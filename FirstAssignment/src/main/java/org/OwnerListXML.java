package org;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso(OwnerXML.class)
public class OwnerListXML extends ArrayList<OwnerXML> {

    @XmlElement()
    public ArrayList<OwnerXML> getOwners(){ return this; }

}
