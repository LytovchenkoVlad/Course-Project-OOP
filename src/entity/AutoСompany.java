package entity;

import javafx.beans.property.*;
import utilities.IShared;

public class Auto—ompany {
	
	private IntegerProperty id;
	private StringProperty autoCompanyName;  // firstName
    private DoubleProperty tariff;    //lastName
    private DoubleProperty electricity; //mark1
//    private DoubleProperty Average;
//    private DoubleProperty toPay;  // mark2  
   
    public Auto—ompany() {
		this.id = new SimpleIntegerProperty(-1000);
		this.autoCompanyName = new SimpleStringProperty();
		this.tariff = new SimpleDoubleProperty();
		this.electricity = new SimpleDoubleProperty();
//		this.Average = new SimpleDoubleProperty();
//		this.toPay = new SimpleDoubleProperty(); 
    }   
    
    public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}
	
	public IntegerProperty idProperty() {
        return id;
    }

    public String getAutoCompanyName() {
        return autoCompanyName.get();
    }

    public void setAutoCompanyName(String AutoCompanyName) {
        this.autoCompanyName.set(AutoCompanyName);
    }

    public StringProperty AutoCompanyNameProperty() {
        return autoCompanyName;
    }

    public double getTariff() {
        return tariff.get();
    }

    public void setTariff(double tariff) {
        this.tariff.set(tariff);
    }

    public DoubleProperty TariffProperty() {
        return tariff;
    }

       public double getElectricity() {
        return electricity.get();
    }

    public void setElectricity(double m1) {
        this.electricity.set(m1);
    }

    public DoubleProperty ElectricityProperty() {
        return electricity;
    }
    
//   public double getAverage() {
//        return Average.get();
//    }
//
//    public void setAverage(double m2) {
//        this.Average.set(m2);
//    }
//
//    public DoubleProperty AverageProperty() {
//        return Average;
//    }
//    
//    public DoubleProperty calcAverageAutoCompany() {    
//        return new SimpleDoubleProperty(Average.doubleValue());
//    }
    
        
    public DoubleProperty calcAutoCompanyElecricity() {    
        return new SimpleDoubleProperty(tariff.doubleValue() * electricity.doubleValue());
    }
    
    public String toString() {
    	StringBuilder str = new StringBuilder();
    	str.append(autoCompanyName.getValue()).append(IShared.delimeter).append(tariff.getValue()).
    	append(IShared.delimeter).append(electricity.intValue());     //.append(IShared.delimeter).append(Average.intValue());    	
        return str.toString();
    }

}