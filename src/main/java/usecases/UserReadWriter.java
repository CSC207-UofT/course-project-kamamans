package usecases;

import entities.UserList;

import java.io.*;

public class UserReadWriter {

    /**
     * Writes the users to file at filePath.
     *
     * @param filePath the file to write the records to
     * @param allUsers    contains list of user managers to be serialized
     * @throws IOException
     */
    public void saveToFile(String filePath, Object allUsers) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        System.out.println("user was cereal");
        // serialize the Map
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
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public UserList readFromFile(String filePath) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        System.out.println("error 1");
        Object readout = input.readObject(); // <--- problem line
        System.out.println("error 2");
        UserList users = (UserList) readout;
        System.out.println("error 3");

        input.close();
        buffer.close();
        file.close();
        return users;
    }
}
