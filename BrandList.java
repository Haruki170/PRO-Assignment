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
import java.util.Scanner;


public class BrandList {
      Validation validation = new Validation();
    ArrayList<Brand> brands = new ArrayList<>();




    public BrandList() {
    }
        public void updateBrand() {
        while (true) {
            String bID = validation.inputString("Enter Brand ID:", "^[A-Za-z0-9\\W]+$");
            int pos = searchID(bID);
            if (pos < 0) {
                System.out.println("Not Found");
            } else {
                Brand brand = brands.get(pos);
                String brandName = validation.inputString("Enter Brand Name:", "^[A-Za-z0-9\\W ]+$");
                String soundBrand = validation.inputString("Enter Sound Brand:", "^[A-Za-z0-9 ]+$");
                double price = validation.inputDouble("Enter Price:");
                brand.setBrandName(brandName);
                brand.setSoundBrand(soundBrand);
                brand.setPrice(price);
                return;
            }
        }
    }
            public int searchID(String bID) {
        int n = brands.size();
        for (int i = 0; i < n-1; i++) {
            if (brands.get(i).getBrandID().equalsIgnoreCase(bID)) {
                return i;
            }
        }
        return -1;
    }
}
