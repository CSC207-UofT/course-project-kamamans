package usecases;

import gateway.InteractDatabase;

import java.io.*;

/**
 * Class a part of Application Business Rules
 * As name implies, reads and writes users into server and file allowing for continuing state of program
 */

public class UserReadWriter {

    /**
     * Writes the users to file at filePath.
     *
     * @param allUsers contains list of user managers to be serialized
     */
    public void saveToFile(UserList allUsers) {
        InteractDatabase.overwrite(allUsers, 0, UserList.class);
    }

    /**
     * Reads users from file at filePath.
     *
     * @return list of user managers
     */
    public UserList readFromFile() {
        return InteractDatabase.getObjectList(UserList.class).get(0);
    }

    // this function is for development purposes only
    private void printUsers() {
        UserList toRead = readFromFile();
        toRead.printAllUsers();
    }
}
