package main;

import command.LoadCommand;
import command.SaveCommand;
import controller.Menu;
import model.PhoneBook;
import singleton.PhoneBookManager;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        // Lấy PhoneBook từ singleton PhoneBookManager
        PhoneBookManager phoneBookManager = PhoneBookManager.getInstance();
        PhoneBook phoneBook = phoneBookManager.getPhoneBook();
        LoadCommand loadCommand = new LoadCommand("phonebook.csv", "csv");
        loadCommand.execute(phoneBook);

        // Khởi tạo Menu và hiển thị
        Menu menu = new Menu(phoneBook);
        menu.show();

    }
}
