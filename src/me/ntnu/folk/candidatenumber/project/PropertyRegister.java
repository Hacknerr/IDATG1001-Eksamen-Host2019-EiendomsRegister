package me.ntnu.folk.candidatenumber.project;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class is used to manage and run a property-registry application.
 * @Author: André Gärtner
 */
public class PropertyRegister {

    private HashSet<Property> propertyRegister;

    /**
     * This is a constructor.
     * Using HashSet because a property is uniq and a hashset doesn't allow duplicate values.
     */
    public PropertyRegister() {
        propertyRegister = new HashSet<>();
    }

    /**
     * Helper method for initializing objects of class Property.
     */
    public void initializeProperties(){
        propertyRegister.add(new Property(1445, "Gloppen", 77, 631, 1017.6F, "Jens Olsen"));
        propertyRegister.add(new Property(1445, "Gloppen", 77, 131, "Syningom", 661.3F, "Nicolay Madsen"));
        propertyRegister.add(new Property(1445, "Gloppen", 75, 19, "Fugletun", 650.6F, "Evilyn Jensen"));
        propertyRegister.add(new Property(1445, "Gloppen", 74, 188, 1457.2F, "Karl Ove Bråten"));
        propertyRegister.add(new Property(1445, "Gloppen", 69, 47, "Høiberg", 1339.4F, "Elsa Indregård"));
    }

    /**
     * Adds a Property-object to the HashSet propertyRegister.
     * @param propertyToBeAdded
     * @return boolean with info if object was added or not.
     */
    public boolean regNewProperty(Property propertyToBeAdded){
        for(Property list : propertyRegister){
            if(list.equals(propertyToBeAdded)){
                    return false;
            }
        }
        propertyRegister.add(propertyToBeAdded);
        return true;
    }

    /**
     * Creates a list with all the properties within the registry.
     * @return ArrayList with all Property-Objects.
     */
    public ArrayList listAllProperties(){

        ArrayList<Property> propertyArrayList = new ArrayList<Property>();

        for(Property list : propertyRegister){
            propertyArrayList.add(list);
        }

        return propertyArrayList;

    }

    /**
     * Converts the contents of an ArrayList to a string.
     * @param listToPrint ArrayList with results from a search.
     */
    public void printResult(ArrayList<Property> listToPrint){

        if(listToPrint.isEmpty()){
            System.out.println("No properties were found");
        }else{
            for(Property list : listToPrint){
                System.out.println(list.toString());
            }
        }

    }

    /**
     * Deletes a property from the HashSet propertyRegister.
     * @param propertyToBeDeleted Property-object with municipality-number,Lot and Section number.
     * @return boolean with info if property deleted or not.
     */
    public boolean deleteProperty(Property propertyToBeDeleted){
        for(Property list : propertyRegister){
            if(list.comparePropertyByMunicipalityNumberLotAndSection(propertyToBeDeleted)){
                propertyRegister.remove(list);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the total amount of properties from the HashSet propertyRegister.
     * @return int
     */
    public int amountOfProperties(){
        return propertyRegister.size();
    }

    /**
     * Creates a list with all the properties of same identification as input from user.
     * @param property objects of Property
     * @return ArrayList with Property-objects.
     */
    public ArrayList listByMunicipalityNumberLotAndSection(Property property){

        ArrayList<Property> propertyArrayList = new ArrayList<>();

        for(Property list : propertyRegister){
            if(list.comparePropertyByMunicipalityNumberLotAndSection(property)){
                propertyArrayList.add(property);
            }
        }
        return propertyArrayList;
    }

    /**
     * Calculates the average area based on all area-sizes in the HashSet.
     * @return int
     */
    public float calculateAverageArea(){

        float total = 0;
        float average;

        for(Property list : propertyRegister){
            total += list.getArea();
        }

        average = total / propertyRegister.size();

        return average;
    }

    /**
     * Creates a list with all the properties of same identification as lot-number from user.
     * @param property objects of Property
     * @return ArrayList with Property-objects.
     */
    public ArrayList listAllPropertiesByLotNumber(Property property){

        ArrayList<Property> propertyArrayList = new ArrayList<Property>();

        for(Property list : propertyRegister){
            if(property.getLotNumber() == list.getLotNumber()){
                propertyArrayList.add(list);
            }
        }
        return propertyArrayList;
    }

}
