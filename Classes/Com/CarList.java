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
import java.util.Collections;

/**
 *
 * @author kan3v
 */
public class CarList {

    private BrandList brandList;
    Validation validation = new Validation();
    private ArrayList<Car> cars = new ArrayList<>();

    public CarList(BrandList bList) {
        this.brandList = bList; // Gán danh sách thương hiệu vào thuộc tính
    }

    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return false; // Nếu file không tồn tại, trả về false
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Tách dòng thành các phần

                if (parts.length >= 5) { // Kiểm tra xem có đủ phần
                    String carID = parts[0].trim();
                    String brandID = parts[1].trim();
                    String color = parts[2].trim();
                    String frameID = parts[3].trim();
                    String engineID = parts[4].trim();

                    Brand brand = brandList.searchID_2(brandID); // Tìm kiếm vị trí của thương hiệu
                    if (brand == null) {
                        System.out.println("Brand not found for ID: " + brandID);
                        continue; // Bỏ qua dòng này nếu không tìm thấy thương hiệu
                    }

                    Car car = new Car(carID, brand, color, frameID, engineID); // Tạo xe mới
                    cars.add(car);
                }
            }
            // Trả về true khi hoàn thành
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
        return true;
    }

    // Phương thức lưu dữ liệu vào file
    public boolean saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Car car : cars) {
                writer.write(car.getCarID() + ","
                        + car.getBrand().getBrandID() + ","
                        + car.getColor() + ","
                        + car.getFrameID() + ","
                        + car.getEngineID() + "\n"); // Ghi xe vào file
            }
            return true; // Trả về true khi hoàn thành
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
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

    public int searchFrame(String fID) {
        int n = cars.size();
        for (int i = 0; i < n - 1; i++) {
            if (cars.get(i).getFrameID().equalsIgnoreCase(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int n = cars.size();
        for (int i = 0; i < n - 1; i++) {
            if (cars.get(i).getEngineID().equalsIgnoreCase(eID)) {
                return i;
            }
        }
        return -1;
    }

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

    public void printBasedBrandName() {
        String input = validation.inputString("Enter Brand Name:", "^[A-Za-z0-9]+$");
        int count = 0;
        for (Car car : cars) {
            if (validation.normalizeString(car.getBrand().getBrandName()).contains(validation.normalizeString(input))) {
                System.out.println(car.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No Car is detected!");
        }
    }

    public boolean removeCar() {
        String removeID = validation.inputString("Enter ID:", "^[A-Za-z0-9]+$");
        int pos = searchID(removeID);
        if (pos < 0) {
            System.out.println("Not found");
            return false;
        } else {
            cars.remove(pos);
        }
        return true;
    }

    public boolean updateCar() {
        String updateID = validation.inputString("Enter ID:", "^[A-Za-z0-9]+$");
        int pos = searchID(updateID);
        if (pos < 0) {
            System.out.println("Not found");
            return false;
        } else {
            Car car = cars.get(pos);
            Brand brand = brandList.getUserChoice();
            String color = validation.inputString("Enter color:", "^[A-Za-z0-9]+$");
            String frameID = validation.inputString("Enter frame ID:", "^[A-Z]\\d{4,}$");
            String engineID = validation.inputString("Enter engine ID:", "^[A-Z]\\d{4,}$");
            car.setBrand(brand);
            car.setColor(color);
            car.setFrameID(frameID);
            car.setEngineID(engineID);
        }
        return true;
    }

    public void listCars() {
        Collections.sort(cars);
        if (cars.isEmpty()) {
            System.out.println("No Cars");
        }
        for (Car car : cars) {
            System.out.println(car.screenString());
        }
    }

}
