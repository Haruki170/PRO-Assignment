/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes.Com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kan3v
 */
public class BrandList {

    Validation validation = new Validation();
    ArrayList<Brand> brands = new ArrayList<>();

    public BrandList() {
    }

    public boolean loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3); // Tách 3 phần đầu tiên bằng dấu phẩy

                if (parts.length == 3) {
                    String brandID = parts[0].trim();
                    String brandName = parts[1].trim();
                    String[] soundPriceParts = parts[2].split(":"); // Tách soundBrand và price bằng dấu hai chấm

                    if (soundPriceParts.length == 2) {
                        String soundBrand = soundPriceParts[0].trim();
                        double price = Double.parseDouble(soundPriceParts[1].trim());

                        Brand brand = new Brand(brandID, brandName, soundBrand, price);
                        brands.add(brand);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Phương thức lưu dữ liệu vào file
    public boolean saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Brand brand : brands) {
                writer.write(brand.getBrandID() + "," + brand.getBrandName() + ","
                        + brand.getSoundBrand() + ":" + brand.getPrice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Brand searchID_2(String bID) {
        for (Brand brand : brands) {
            if (brand.getBrandID().equalsIgnoreCase(bID)) {
                return brand;
            }
        }
        return null;
    }

    public void searchById() {
        String input = validation.inputString("Enter Brand ID:", "^[A-Za-z0-9-]+$");
        int count = 0;
        for (Brand brand : brands) {
            if (validation.normalizeString(brand.getBrandID()).contains(validation.normalizeString(input))) {
                System.out.println(brand.toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No Brand is detected!");
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

    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand) mnu.ref_getChoice(brands);
    }

    public void addBrand() {
        while (true) {
            String bID = validation.inputString("Enter Brand ID:", "^[A-Za-z0-9\\W]+$");
            if (searchID(bID) >= 0) {
                System.out.println("ID Existed");
                continue;
            }
            String brandName = validation.inputString("Enter Brand Name:", "^[A-Za-z0-9\\W ]+$");
            String soundBrand = validation.inputString("Enter Sound Brand:", "^[A-Za-z0-9 ]+$");
            double price = validation.inputDouble("Enter Price:");
            brands.add(new Brand(bID, brandName, soundBrand, price));
            return;
        }

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

    public void listBrands() {
        for (Brand brand : brands) {
            System.out.println(brand.toString());
        }
    }

}
