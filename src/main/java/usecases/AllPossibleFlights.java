package usecases;

import entities.Airport;
import entities.Flight;
import entities.Plane;

import java.util.*;

public class AllPossibleFlights {
    private Hashtable<String, Flight> flightData;
    private Hashtable<String, Airport> airportData;

    public AllPossibleFlights() {
        this.flightData = new Hashtable<String, Flight>();
        this.airportData = new Hashtable<String, Airport>();


        // Mock Data (Airport) -> the keys are the airports' IATA code:
        this.airportData.put("252", new Airport("Montreal", "252")); //A
        this.airportData.put("76", new Airport("Toronto", "76")); //B

        this.airportData.put("251256342", new Airport("Vancouver", "251256342")); //C
        this.airportData.put("12443", new Airport("Quebec City", "12443")); //D

        this.airportData.put("457", new Airport("Mumbai", "457")); //E
        this.airportData.put("9856", new Airport("Paris", "9856")); //F
        this.airportData.put("96", new Airport("Paris", "96")); //G


        // Mock Data (Flight -> source, destination):
        this.flightData.put("1", new Flight(
                new GregorianCalendar(2021, Calendar.DECEMBER, 30),
                new Plane("Boeing 747", 223, 7, 223 - 7, true),
                300, 8, airportData.get("252"), airportData.get("76")));

        this.flightData.put("2", new Flight(
                new GregorianCalendar(2022, Calendar.JUNE, 5),
                new Plane("Apollo 11", 1738, 12, 1738 - 12, true),
                600, 13, airportData.get("76"), airportData.get("251256342")));

        this.flightData.put("3.5", new Flight(
                new GregorianCalendar(2022, Calendar.APRIL, 4),
                new Plane("Falcon 1", 1337, 15, 1337 - 15, true),
                1200, 5, airportData.get("251256342"), airportData.get("12443")));

        this.flightData.put("3", new Flight(
                new GregorianCalendar(2022, Calendar.APRIL, 4),
                new Plane("Falcon 1", 1337, 15, 1337 - 15, true),
                1200, 5, airportData.get("12443"), airportData.get("457")));

        this.flightData.put("4", new Flight(
                new GregorianCalendar(2022, Calendar.APRIL, 4),
                new Plane("Falcon 1", 1337, 15, 1337 - 15, true),
                1200, 5, airportData.get("252"), airportData.get("9856")));
        this.flightData.put("5", new Flight(
                new GregorianCalendar(2022, Calendar.APRIL, 4),
                new Plane("Falcon 1", 1337, 15, 1337 - 15, true),
                1200, 5, airportData.get("252"), airportData.get("12443")));

        this.flightData.put("6", new Flight(
                new GregorianCalendar(2022, Calendar.APRIL, 4),
                new Plane("Falcon 1", 1337, 15, 1337 - 15, true),
                1200, 5, airportData.get("251256342"), airportData.get("96")));
        this.flightData.put("7", new Flight(
                new GregorianCalendar(2022, Calendar.APRIL, 4),
                new Plane("Falcon 1", 1337, 15, 1337 - 15, true),
                1200, 5, airportData.get("96"), airportData.get("457")));

    }

    public Hashtable <String, Airport> getAirportData() {
        return this.airportData;
    }

    public Hashtable <String, Flight> getFlightData() {
        return this.flightData;
    }

//this is fine
    public static class Graph_dfs {

        private int v; // number of vertices in the graph
        private ArrayList<Integer>[] adj; // Adjacency List:

        public Graph_dfs(int vertices) {
            this.v = vertices;
            initAdj();
        }
        //this is also fine
        // initialise adjacency list:
        @SuppressWarnings("unchecked")
        private void initAdj() {
            adj = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        // add edge from u to v
        public void addEdge(int u, int v) {
            adj[u].add(v);
        }

        // Prints all flights from Airport 'a' to Airport 'b'
    //path list should be adjlist
        //calls dfs -- runs once


        //Actual DFS
        //routes is pathList
        private void printRoute(Integer u, Integer w,
                                       boolean[] isVisited,
                                       List<Integer> routes, List<List<Integer>> allRoutes) {

            if (u.equals(w)) {
                // if match found then no need to traverse more till depth
                allRoutes.add(new ArrayList<>(routes));
                return;
            }

            // Mark the current node
            isVisited[u] = true;

            // Recur for all the vertices
            // adjacent to current vertex
            for (Integer i : adj[u]) {
                if (!isVisited[i]) {
                    // store current node
                    // in path[]
                    routes.add(i);
                    printRoute(i, w, isVisited, routes, allRoutes);
                    // remove current node
                    // in path[]
                    routes.remove(i);
                }
            }

            // Mark the current node
            isVisited[u] = false;
        }
    }


    // NOTE TO READER: Run main and the output for all different paths from 5 to 3 should be:
//            [5, 6, 3]
//            [5, 0, 2, 1, 3]
//            [5, 0, 2, 6, 3]

    public static void main(String[] args) {
        // Create a sample graph
        Graph_dfs g = new Graph_dfs(7); // specify the number of verticies
        // In our case the number of vertices will be: SearchRoutePOC.len = obj.airportData.size();


        AllPossibleFlights obj = new AllPossibleFlights();
        Enumeration<String> enu = obj.getAirportData().keys();

        ArrayList<String> keys = new ArrayList<String>();

        // Creating an Array List of airport names
        while (enu.hasMoreElements()) {keys.add(enu.nextElement());}

        HashMap<String, Integer> NodeID = new HashMap<String, Integer>();

        Hashtable<String, Airport> airports = obj.getAirportData();

        // In Graph, Airports are Nodes/Vertices:
        //NodeID is used for changing iatacode to int (for use in graph)
        int node = 0;
        for (String key : keys) {
            NodeID.put(key, node);
            node++;
        }

        Hashtable <String, Flight> flights = obj.getFlightData();

        // In Graph, Flights are Paths:
        //gets names of all airports to use for NodeID
        Enumeration<String> enuflight = flights.keys();
        ArrayList<String> flight_keys = new ArrayList<String>();

        while (enuflight.hasMoreElements()) {
            flight_keys.add(enuflight.nextElement());
        }

        for(String key2: flight_keys){
            Flight flight_object = (Flight) flights.get(key2);
            // Arrival and Destination Airports for each flight
            String departure = flight_object.getSourceAirport().getIataCode();
            String arrival = flight_object.getDestinationAirport().getIataCode();

            // let u, v be NodeID.get(source, dest)
            int u = NodeID.get(arrival);
            int v = NodeID.get(departure);
            g.addEdge(v, u);
            // call dfs with v and u
        }
        // arbitrary source
        int s = 5;

        // arbitrary destination
        int d = 3;

        System.out.println(
                "Following are all different paths from "
                        + s + " to " + d);

        boolean[] isVisited = new boolean[7];
        List<Integer> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(s);
        // Call recursive utility
        List<List<Integer>> allPaths = new ArrayList<>();
        List<List<String>> pathsIata = new ArrayList<>();
        List<List<Airport>> newAirports = new ArrayList<>();

        g.printRoute(s, d, isVisited, pathList, allPaths);

        // For Debugging Purposes:
        for (List<Integer> allPath : allPaths) {
            String majda = allPath.toString();
            System.out.println(majda);
        }

        // Reverse Hashmap:
        HashMap<Integer, String> reverseNodeID = new HashMap<>();
        for (String key: NodeID.keySet()){
            reverseNodeID.put(NodeID.get(key), key);
        }

        for (int a = 0; a < allPaths.size(); a++){
            pathsIata.add(new ArrayList<>());
        }

        for (int a = 0; a < allPaths.size(); a++){
            newAirports.add(new ArrayList<>());
        }

        // from paths get corresponding iatacodes
        for(int t = 0; t < allPaths.size(); t ++){
            for (int w = 0; w < allPaths.get(t).size(); w ++){
                pathsIata.get(t).add(reverseNodeID.get(allPaths.get(t).get(w)));
            }
        }

        //loop through iata arraylist to get airport obj
        for(int t = 0; t < allPaths.size(); t ++){
            for (int w = 0; w < allPaths.get(t).size(); w ++){
                String code = pathsIata.get(t).get(w);
                Airport airportObj = airports.get(code);
                newAirports.get(t).add(airportObj);
            }
        }

        //debugging
        for(int t = 0; t < allPaths.size(); t ++){
            for (int w = 0; w < allPaths.get(t).size(); w ++){
                System.out.println(newAirports.get(t).get(w));
            }
        }
// final function
        List<List<Flight>> finalRoutes = new ArrayList<>();

            for (int i = 0; i < newAirports.size(); i++) {
                ArrayList<Flight> temp = new ArrayList<>();
                for (int j = 0; j < newAirports.get(i).size() - 1; j++) {
                    String destination = newAirports.get(i).get(j + 1).getIataCode();
                    String source = newAirports.get(i).get(j).getIataCode();
                    for (String f : flight_keys) {
                        Flight flight = flights.get(f);
                        if ((flight.getSourceAirport().getIataCode() == source) &&
                                (flight.getDestinationAirport().getIataCode() == destination)) {
                            temp.add(flight);
                        }
                    }
                }
                finalRoutes.add(temp);
        }

// prints out all flights given source and destination (Used for debugging purposes) -> make as a test?
        for(int t = 0; t < finalRoutes.size(); t ++){
            for (int w = 0; w < finalRoutes.get(t).size(); w ++){
                System.out.println(finalRoutes.get(t).get(w));
            }
        }
    }
}


