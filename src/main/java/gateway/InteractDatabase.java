package gateway;

import java.io.*;
import java.util.*;

public class InteractDatabase {
    private static final String databasePath = "src/main/java/backend/database";

    private static <T extends Serializable> String filePath(Class<T> objectType) {
        // Given an object type, return the file path for the corresponding database
        return databasePath + "/" + objectType.getSimpleName().toLowerCase() + ".ser";
    }

    public static <T extends Serializable> ArrayList<T> getObjectList(Class<T> objectType) {
        // Return list of object from specified objectType
        try {
            // Every database file name is the same as the Object type it stores
            FileInputStream fis = new FileInputStream(filePath(objectType));
            ObjectInputStream ois = new ObjectInputStream(fis);

            @SuppressWarnings("unchecked")
            // Only postList can write to the files, and postList type-checks ArrayList<T>
            ArrayList<T> outputList = (ArrayList<T>) ois.readObject();

            ois.close();
            fis.close();
            return outputList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T extends Serializable> void postList(ArrayList<T> saveList, Class<T> objectType) {
        // Serialize an ArrayList
        try {
            FileOutputStream fos = new FileOutputStream(filePath(objectType));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(saveList);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Serializable> void post(T toStore, Class<T> objectType) {
        // Serializes <toStore>
        ArrayList<T> databaseList = getObjectList(objectType);
        assert databaseList != null;
        databaseList.add(toStore);
        postList(databaseList, objectType);
    }

    public static <T extends Serializable> void initialize(Class <T> objectType) {
        // Initialize empty database
        ArrayList<T> databaseList = new ArrayList<T>();
        postList(databaseList, objectType);
    }
}