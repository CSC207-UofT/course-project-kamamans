package usecases;

import entities.User;

import java.io.*;

public class UserReadWriter {

    /**
     * Writes the users to file at filePath.
     *
     * @param filePath the file to write the records to
     * @param allUsers    contains list of user managers to be serialized
     * @throws IOException if allUsers was not saved
     */
    public void saveToFile(String filePath, UserList allUsers) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(allUsers);
        output.close();
        buffer.close();
        file.close();
    }

    /**
     * Reads users from file at filePath.
     *
     * @param filePath the file to read records from
     * @return list of user managers
     * @throws IOException if unable to read user list
     * @throws ClassNotFoundException if file is not on class path
     */
    public UserList readFromFile(String filePath) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        UserList users = (UserList) input.readObject();

        input.close();
        buffer.close();
        file.close();
        return users;
    }

    /**
     * Resets all user data.
     * This is irreversible and idempotent.
     *
     * Generates two users, "user1" and "user2"
     */
    private void resetUsers() {
        // Create fresh user data
        UserList userData = new UserList();
        userData.addUser(new User("user1", "111", "test1@email.ca", "(416)-000-0001"));
        userData.addUser(new User("user2", "222", "test2@email.com", "(416)-000-0002"));

        // Upload
        try {
            OutputStream file = new FileOutputStream("src/main/java/backend/database/users.ser");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(userData);

            output.close();
            buffer.close();
            file.close();
        } catch (IOException e) {
            System.out.println("IOException: Failed to reset users.");
        }

    }

    // this function is for development purposes only
    private void printUsers() {
        try {
            UserList toRead = readFromFile("src/main/java/backend/database/users.ser");
            toRead.printAllUsers();
        } catch (IOException e) {
            System.out.println("IOException: Failed to print users.");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: Failed to print users.");
        }


    }

    // this main is for development purposes only
    public static void main(String[] args) {

        UserReadWriter urw = new UserReadWriter();

        // reset user data
         urw.resetUsers();

//        UserList userData = new UserList();
//        userData.addUser(new User("user1", "111", "test@email.com", "(416)-111-2222"));
//        userData.addUser(new User("user2", "112", "test2@email.com", "(647)-112-2222"));

//        try {
//            urw.saveToFile("src/main/java/backend/database/users.ser", userData);
//        } catch (IOException e) {
//            System.out.println("ioexception in main");
//        }


        // print user data
//        urw.printUsers();
    }
}
