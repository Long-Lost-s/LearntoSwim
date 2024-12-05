package singleton;

import model.PhoneBook;

public class PhoneBookManager {
    private static PhoneBookManager instance;
    private PhoneBook phoneBook;

    private PhoneBookManager() {
        phoneBook = new PhoneBook();
    }

    public static synchronized PhoneBookManager getInstance() {
        if (instance == null) {
            instance = new PhoneBookManager();
        }
        return instance;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }
}
