package file;

import model.Contact;
import model.PhoneBook;
import model.PhoneBookException;

import java.io.*;
import java.util.*;

public class CSVFileManager implements FileOperations{
    @Override
    public void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException, PhoneBookException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (Contact contact : phoneBook.getContacts()) {
                writer.write(contact.getName() + "," + contact.getPhoneNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new PhoneBookException("Error saving to CSV file", e);
        }
    }

    @Override
    public PhoneBook loadPhoneBook(String filename) throws IOException, PhoneBookException {
        PhoneBook phoneBook = new PhoneBook();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    phoneBook.addContact(new Contact(fields[0], fields[1]));
                }
            }
        } catch (IOException e) {
            throw new PhoneBookException("Error loading from CSV file", e);
        }
        return phoneBook;
    }
}
