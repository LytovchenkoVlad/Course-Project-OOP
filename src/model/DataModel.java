package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dao.DataManager;
import entity.Auto—ompany;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.IShared;

/**
 * 
 * @author Litovchenko Vlad
 */
public class DataModel {

	private static DataModel instance;
	private static ObservableList<Auto—ompany> cache;

	private DataModel() {
		cache = FXCollections.observableArrayList();
	}

	public static DataModel GetInstance() {
		if (instance == null) {
			instance = new DataModel();
		}
		return instance;
	}

	public ObservableList<Auto—ompany> getCache() {
		return cache;
	}

	public void clear() {
		cache.clear();
	}

	public void add(Auto—ompany s) {
		cache.add(s);
	}

	public void addAll(List<Auto—ompany> s) {
		cache.addAll(s);
	}

	public void delete(int i) {
		cache.remove(i);
	}

	public void edit(int i) {
		Auto—ompany tmp = cache.get(i);
		cache.set(i, tmp);
	}

	public void load(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<String> tmp = DataManager.readTextData(file.getAbsolutePath());
		String[] tokens;
		for (String line : tmp) {
			Auto—ompany p = new Auto—ompany();
			tokens = line.split(IShared.delimeter);
			p.setAutoCompanyName(tokens[0]);
			p.setTariff(Double.parseDouble(tokens[2]));
			p.setElectricity(Double.parseDouble(tokens[2]));

			add(p);
		}
	}

	public void persist(File file) throws IOException {
		DataManager.saveTextData(cache, file.getAbsolutePath());
	}
	
	public double calcElectricityValue() {    
		double sum = 0;		
			for (Auto—ompany st : cache) 			
				sum += st.getElectricity();				
		return sum / cache.size();
	}
	
	public double calcPaymentValue() {    
		double sum = 0;		
			for (Auto—ompany st : cache) 			
				sum += st.calcAutoCompanyElecricity().get();				
		return sum / cache.size();
	}
}