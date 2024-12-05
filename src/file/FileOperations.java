package file;

import model.PhoneBook;
import model.PhoneBookException;

import java.io.IOException;

public interface FileOperations {
    void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException, PhoneBookException;
    PhoneBook loadPhoneBook(String filename) throws IOException, PhoneBookException;
}
