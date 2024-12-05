package command;

import factory.FileManagerFactory;
import file.FileOperations;
import model.PhoneBook;

public class SaveCommand implements Command {
    private String filename;
    private String fileType;

    public SaveCommand(String filename, String fileType) {
        this.filename = filename;
        this.fileType = fileType;
    }

    @Override
    public void execute(PhoneBook phoneBook) {
        FileOperations fileManager = FileManagerFactory.getFileManager(fileType);
        try {
            fileManager.savePhoneBook(phoneBook, filename);
            System.out.println("Phonebook saved to " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
