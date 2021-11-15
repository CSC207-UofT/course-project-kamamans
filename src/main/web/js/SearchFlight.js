function searchForFlights(departure, destination, date){
    // TODO get a list of flights given the search parameters (connected to Java)

    console.log(departure);
    console.log(destination);
    console.log(date);

    let validSearch;

    // TODO To be replaced with java
    if(departure==="login"){
        validSearch = null;
    } else {
        validSearch = "There was an error searching.";
    }

    if(validSearch === null) {
        window.location.href = "SelectFlight.html";
    } else {
        window.alert(validSearch);
    }

}