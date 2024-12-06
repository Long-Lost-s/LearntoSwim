package controller;

import command.LoadCommand;
import command.SaveCommand;
import model.Contact;
import model.PhoneBook;

import java.io.File;
import java.util.Scanner;

public class Menu {
    private final PhoneBook phoneBook;
    private final Scanner scanner;

    public Menu(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
    }

    public void show() {
                while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Đọc dòng mới để bỏ qua ký tự Enter

            switch (choice) {
                case 1:
                    // Thêm liên hệ mới
                    addContact();
                    break;
                case 2:
                    // Lưu danh bạ vào file CSV
                    savePhoneBookToCSV();
                    break;
                case 3:
                    // Lưu danh bạ vào file nhị phân
                    savePhoneBookToBinary();
                    break;
                case 4:
                    // Tải danh bạ từ file CSV
                    loadPhoneBookFromCSV();
                    break;
                case 5:
                    // Tải danh bạ từ file nhị phân
                    loadPhoneBookFromBinary();
                    break;
                case 6:
                    // Hiển thị danh bạ
                    displayPhoneBook();
                    break;
                case 7:
                    // Xóa liên hệ
                    deleteContact();
                    break;
                case 8:
                    // Lấy thông tin liên hệ
                    getContactInfo();
                    break;
                case 9:
                    // Thoát ứng dụng
                    System.out.println("Cảm ơn bạn đã sử dụng ứng dụng.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. Thêm liên hệ mới");
        System.out.println("2. Lưu danh bạ vào file CSV");
        System.out.println("3. Lưu danh bạ vào file nhị phân");
        System.out.println("4. Tải danh bạ từ file CSV");
        System.out.println("5. Tải danh bạ từ file nhị phân");
        System.out.println("6. Hiển thị danh bạ");
        System.out.println("7. Xóa liên hệ");
        System.out.println("8. Lấy thông tin liên hệ");
        System.out.println("9. Thoát");
        System.out.print("Vui lòng chọn một tùy chọn: ");
    }

    private void addContact() {
        System.out.print("Nhập tên liên hệ: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();

        // Kiểm tra nếu số điện thoại hợp lệ
        try {
            Contact contact = new Contact(name, phoneNumber);
            phoneBook.addContact(contact);
            System.out.println("Liên hệ đã được thêm.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private void savePhoneBookToCSV() {
        SaveCommand saveCSVCommand = new SaveCommand("phonebook.csv", "csv");
        saveCSVCommand.execute(phoneBook);
        System.out.println("Danh bạ đã được lưu vào file CSV.");
    }

    private void savePhoneBookToBinary() {
        SaveCommand saveBinaryCommand = new SaveCommand("phonebook.dat", "binary");
        saveBinaryCommand.execute(phoneBook);
        System.out.println("Danh bạ đã được lưu vào file nhị phân.");
    }

    private void loadPhoneBookFromCSV() {
        File file = new File("phonebook.csv");
        if (!file.exists()) {
            System.out.println("Tệp CSV không tồn tại.");
            return;
        }
        LoadCommand loadCSVCommand = new LoadCommand("phonebook.csv", "csv");
        loadCSVCommand.execute(phoneBook);
        System.out.println("Danh bạ đã được tải từ file CSV.");
        displayPhoneBook();
    }

    private void loadPhoneBookFromBinary() {
        File file = new File("phonebook.dat");
        if (!file.exists()) {
            System.out.println("Tệp nhị phân không tồn tại.");
            return;
        }
        LoadCommand loadBinaryCommand = new LoadCommand("phonebook.dat", "binary");
        loadBinaryCommand.execute(phoneBook);
        System.out.println("Danh bạ đã được tải từ file nhị phân.");
        displayPhoneBook();
    }

    private void displayPhoneBook() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("Danh bạ hiện tại rỗng.");
        } else {
            System.out.println("Danh bạ hiện tại:");
            for (Contact contact : phoneBook.getContacts()) {
                System.out.println(contact);
            }
        }
    }

    private void deleteContact() {
        System.out.print("Nhập tên liên hệ cần xóa: ");
        String name = scanner.nextLine();
        boolean removed = phoneBook.getContacts().removeIf(contact -> contact.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println("Liên hệ '" + name + "' đã được xóa.");
        } else {
            System.out.println("Không tìm thấy liên hệ với tên: " + name);
        }
    }

    private void getContactInfo() {
        System.out.print("Nhập tên liên hệ cần xem thông tin: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (Contact contact : phoneBook.getContacts()) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println("Thông tin liên hệ:");
                System.out.println(contact);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy liên hệ với tên: " + name);
        }
    }
}
