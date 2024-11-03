/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import BrandList;
import CarList;
import Menu;
import ArrayList;

/**
 *

 */
public class CarManager {

    public static void main(String[] args) {
        
        BrandList brandList = new BrandList();
        CarList carList = new CarList(brandList);
        
        ArrayList<String> ops = new ArrayList<>();
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");
        ops.add("Exit");
        
        int choice;
        Menu menu = new Menu(); // Giả sử bạn có lớp Menu để xử lý menu

        if (!brandList.loadFromFile("brands.txt")) {
            System.out.println("Failed to load brands from file.");
        }
        
        if (!carList.loadFromFile("cars.txt")) {
            System.out.println("Failed to load cars from file.");
        }
        
        do {
            choice = menu.int_getChoice(ops); // Gọi phương thức int_getChoice để nhận lựa chọn
            switch (choice) {
                case 1:
                    brandList.listBrands(); // Hiển thị tất cả thương hiệu
                    break;
                case 2:
                    // Logic để thêm thương hiệu mới
                    brandList.addBrand();
                    break;
                case 3:
                    // Logic để tìm thương hiệu dựa trên ID
                    brandList.searchById();
                    break;
                case 4:
                    // Logic để cập nhật thương hiệu
                    brandList.updateBrand();
                    break;
                case 5:
                    // Logic để lưu thương hiệu vào tệp brands.txt
                    if (brandList.saveToFile("brands.txt")) {
                        System.out.println("Brands saved successfully.");
                    } else {
                        System.out.println("Failed to save brands.");
                    }
                    break;
                case 6:
                    carList.listCars(); // Hiển thị danh sách xe theo thứ tự tên thương hiệu
                    break;
                case 7:
                    // Logic để tìm xe dựa trên một phần của tên thương hiệu
                    carList.printBasedBrandName();
                    break;
                case 8:
                    // Logic để thêm xe mới
                    carList.addCar();
                    break;
                case 9:
                    // Logic để xóa xe dựa trên ID
                    carList.removeCar();
                    break;
                case 10:
                    // Logic để cập nhật xe dựa trên ID
                    carList.updateCar();
                    break;
                case 11:
                    // Logic để lưu xe vào tệp cars.txt
                    if (carList.saveToFile("cars.txt")) {
                        System.out.println("Cars saved successfully.");
                    } else {
                        System.out.println("Failed to save cars.");
                    }
                    break;
                case 12:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice > 0 && choice <= ops.size()-1);
    }
}
