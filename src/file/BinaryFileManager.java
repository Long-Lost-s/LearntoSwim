package file;

import model.PhoneBook;
import model.PhoneBookException;

import java.io.*;

public class BinaryFileManager implements FileOperations{
    @Override
    public void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException, PhoneBookException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(phoneBook);
        } catch (IOException e) {
            throw new PhoneBookException("Error saving to binary file", e);
        }
    }

    @Override
    public PhoneBook loadPhoneBook(PhoneBook phoneBook, String filename) throws IOException, PhoneBookException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (PhoneBook) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new PhoneBookException("Error loading from binary file", e);
        }
    }
}
