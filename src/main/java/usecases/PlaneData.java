package usecases;

import entities.Plane;
import gateway.InteractDatabase;

import java.io.*;
import java.util.ArrayList;

public class PlaneData {
    public static ArrayList<Plane> getPlaneList() {
        // Returns list of Planes
        ArrayList<Plane> output = InteractDatabase.getObjectList(Plane.class);
        return output;
    }

    public static void postPlane(Plane toStore) {
        // Serializes <toStore>
        InteractDatabase.post(toStore, Plane.class);
    }

    public static Plane getPlane(String brandName) throws IOException, ClassNotFoundException {
        ArrayList<Plane> planeList = getPlaneList();
        for (Plane plane : planeList) {
            if (plane.getBrandName().equals(brandName)) {
                return plane;
            }
        }
        return null;
    }

    public static void printPlanes() {
        System.out.println("Plane List:");
        for (Plane plane : getPlaneList()) {
            System.out.println("-----");
            System.out.println("brand name: " + plane.getBrandName());
            System.out.println("total seats: " + plane.getSeatCount());
            System.out.println("vacant: " + plane.getHasVacantSeats());
        }
    }
}
