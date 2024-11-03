/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Classes.Com;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * 
 */
public class Menu {

    public <E> int int_getChoice(ArrayList<E> options) {
        Scanner scanner = new Scanner(System.in);
        Validation validation = new Validation();
        int response = -1;
        int N = options.size(); // Kích thước của danh sách

        // Hiển thị các tùy chọn
        for (int i = 0; i < N ; i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        do {
            System.out.print("Please choose an option (1.." + N + "): ");

            // Kiểm tra xem người dùng nhập số nguyên hợp lệ hay không
            if (scanner.hasNextInt()) {
                response = scanner.nextInt(); // Đọc số nguyên nếu hợp lệ

                // Kiểm tra xem giá trị nhập vào có trong phạm vi hợp lệ không
                if (response >= 1 && response <= N) {
                    break; // Thoát vòng lặp nếu lựa chọn hợp lệ
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Bỏ qua đầu vào không phải số
            }
        } while (true);
        return response; // Trả về lựa chọn của người dùng
    }

    public <E> E ref_getChoice(ArrayList<E> options) {
        int response;
        int N = options.size();

        do {
            // Nhận lựa chọn của người dùng bằng cách gọi int_getChoice
            response = int_getChoice(options);
        } while (response < 1 || response > N);  // Đảm bảo giá trị trả về nằm trong phạm vi hợp lệ

        // Trả về đối tượng đã chọn từ danh sách (chỉ số mảng là từ 0, nên trả về options.get(response-1))
        return options.get(response - 1);
    }

}
