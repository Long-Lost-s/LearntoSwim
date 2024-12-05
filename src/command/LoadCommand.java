package command;

import factory.FileManagerFactory;
import file.FileOperations;
import model.Contact;
import model.PhoneBook;

public class LoadCommand implements Command {
    private String filename;
    private String fileType;

    public LoadCommand(String filename, String fileType) {
        this.filename = filename;
        this.fileType = fileType;
    }

    @Override
    public void execute(PhoneBook phoneBook) {
        FileOperations fileManager = FileManagerFactory.getFileManager(fileType);
        try {
            PhoneBook loadedPhoneBook = fileManager.loadPhoneBook(filename);
            for (Contact contact : loadedPhoneBook.getContacts()) {
                System.out.println(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}