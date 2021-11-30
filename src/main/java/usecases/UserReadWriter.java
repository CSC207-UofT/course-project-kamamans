package usecases;

import java.io.*;

public class UserReadWriter {

    /**
     * Writes the users to file at filePath.
     *
     * @param filePath the file to write the records to
     * @param allUsers    contains list of user managers to be serialized
     * @throws IOException if allUsers was not saved
     */
    public void saveToFile(String filePath, Object allUsers) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(allUsers);
        output.close();
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
        return users;
    }
}
