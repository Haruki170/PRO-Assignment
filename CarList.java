/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upgit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
public class CarList {
        private BrandList brandList;
    Validation validation = new Validation();
    private ArrayList<Car> cars = new ArrayList<>();
        public void addCar() {
        while (true) {
            String cID = validation.inputString("Enter Car ID:", "^[A-Za-z0-9]+$");
            if (searchID(cID) != -1) {
                System.out.println("ID Existed");
                continue;
            }
            cID = cID.toUpperCase();
            Brand brand = brandList.getUserChoice();
            //^[FE]\d{4}$
            String color = validation.inputString("Enter color:", "^[A-Za-z0-9]+$");
            String frameID = validation.inputString("Enter frame ID:", "^[A-Z]\\d{4,}$");
            String engineID = validation.inputString("Enter engine ID:", "^[A-Z]\\d{4,}$");
            cars.add(new Car(cID, brand, color, frameID, engineID));
            return;
        }

    }
            public int searchID(String carID) {
        int n = cars.size();
        for (int i = 0; i < n ; i++) {
            if (cars.get(i).getCarID().equalsIgnoreCase(carID)) {
                return i;
            }
        }
        return -1;
    }
}
