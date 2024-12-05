package command;

import model.PhoneBook;

public interface  Command {
    void execute(PhoneBook phoneBook);
}
