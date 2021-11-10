function displayFlights(){

    // TODO replace placeholderData with data received from the backend
    const placeholderData = [
        {
            departureAirport: {city: 'toronto', iataCode: '235ge'},
            destinationAirport: {city: 'montreal', iataCode: 'iger23'},
            departureDate: new Date(),
            flights: [
                {
                departureDate: new Date(),
                plane: {brandName: 'Boeing', seatCount: 250, firstClassSeats: 50, economySeats: 200, hasVacantSeats: true},
                price: 200,
                duration: 8,
                sourceAirport: {city: 'toronto', iataCode: '235ge'},
                destinationAirport: {city: 'montreal', iataCode: 'iger23'},
                }
            ],
            price: 200,
            duration: 8,
            id: '264654'
        },
        {
            departureAirport: {city: 'vancouver', iataCode: 'dfgio'},
            destinationAirport: {city: 'calgary', iataCode: 'apple'},
            departureDate: new Date(),
            flights: [
                {
                    departureDate: new Date(),
                    plane: {brandName: 'Bombardier', seatCount: 400, firstClassSeats: 50, economySeats: 350, hasVacantSeats: true},
                    price: 150,
                    duration: 6,
                    sourceAirport: {city: 'vancouver', iataCode: 'dfgio'},
                    destinationAirport: {city: 'calgary', iataCode: 'apple'},
                }
            ],
            price: 150,
            duration: 6,
            id: '39802'
        }
    ]

    // Parsing through the placeholder data and turning into html content

    let searchResults = `<div class="container" id="div-container">`
    let flightDetails;

    for (let i = 0; i < placeholderData.length; i++){
        searchResults += `<div class="col-xs-12 col-md-6">
            <div class="prod-info-main prod-wrap clearfix">
                <div class="row">
                    <div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="product-image">
                            <img src="images/plane.jpg" alt="194x228" class="img-responsive"/>
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-12 col-xs-12">
                        <div class="product-detail">
                            <h5 class="name">
                                <a>
                                    Route #`+placeholderData[i]['id']+`<span>Estimated Flight Duration: `+
                                    placeholderData[i]['duration']+` hours</span>
                                </a>
                            </h5>
                            <p class="price-container">
                                <span>$`+placeholderData[i]['price']+`</span>
                            </p>
                            <span class="tag1"></span>
                        </div>
                        <div class="description">
                            <p>This route has `+placeholderData[i]['flights'].length+` total flight(s). The first flight
                            departs on `+placeholderData[i]['departureDate']+` and lands in `+
                            placeholderData[i]['flights'][0]['destinationAirport']['city']+`.</p>
                        </div>
                        <div class="product-info smart-form">
                            <div class="row">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-danger" onClick="selectFlight(`+placeholderData[i]['id']+`)">Select Flight</button>
                                    <a type="button" href="#popup`+i+`" class="btn btn-info">More info</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
`
    }

    for (let i = 0; i < placeholderData.length; i++) {
        flightDetails = ``;
        for (let j = 0; j < placeholderData[i]['flights'].length; j++){
            flightDetails += `
                $`+placeholderData[i]['flights'][j]['price']+`, `+placeholderData[i]['flights'][j]['duration']+` hours, 
                `+placeholderData[i]['flights'][j]['departureDate'].toLocaleString()+`: `+
                placeholderData[i]['flights'][j]['sourceAirport']['city']+` (`+
                placeholderData[i]['flights'][j]['sourceAirport']['iataCode']+`) &rarr; `+
                placeholderData[i]['flights'][j]['destinationAirport']['city']+` (`+
                placeholderData[i]['flights'][j]['destinationAirport']['iataCode']+`)
            `;
        }

        searchResults += `
            <div id="popup`+i+`" class="overlay">
                <div class="popup">
                    <h2>Route #`+placeholderData[i]['id']+`</h2>
                    <a class="close" href="#">&times;</a>
                    <div class="content">
                        <div class="product-detail">
                            <h5 class="name">
                                <a>
                                    <span>Estimated Flight Duration: `+
                                    placeholderData[i]['duration']+` hours</span>
                                </a>
                            </h5>
                            <p class="price-container">
                                <span>Estimated Price: $`+placeholderData[i]['price']+`</span>
                            </p>
                            <p style="font-size: 16px; padding-top: 10px;">
                                Departure Airport: `+placeholderData[i]['departureAirport']['city']+` (`+
                                placeholderData[i]['departureAirport']['iataCode']+`)
                            </p>
                            <p style="font-size: 16px;">
                                Destination Airport: `+placeholderData[i]['destinationAirport']['city']+` (`+
                                placeholderData[i]['destinationAirport']['iataCode']+`)
                            </p>
                            `+flightDetails+`
                        </div>
                    </div>
                </div>
            </div>
        `;
    }




    let htmlDom = new DOMParser().parseFromString(searchResults, "text/html");

    const stack = document.body;

    stack.appendChild(htmlDom.documentElement);
}

displayFlights();

function sortByPrice() {
    // TODO Get the price sorted data here
    console.log("Sorting by price.");
}

function sortByDuration() {
    // TODO Get the duration sorted data here
    console.log("Sorting by duration.");
}

function returnToSearch() {
    window.location.href = "SearchFlight.html";
}

function selectFlight(routeId) {
    // TODO Add the flight to the user's flight history
    console.log("Selected flight: "+routeId);


    // Create a popup to let the user know their selection was processed
    let flightSelectedPopup = `
            <div id="flightSelected" class="overlay">
                <div class="popup">
                    <h2>Route #`+routeId+`</h2>
                    <a class="close" href="#">&times;</a>
                    <div class="content">
                        <div class="">
                            <h1>Flight successfully added to your search history.</h1>
                        </div>
                    </div>
                </div>
            </div>
        `;

    let htmlDom = new DOMParser().parseFromString(flightSelectedPopup, "text/html");

    const stack = document.body;

    stack.appendChild(htmlDom.documentElement);

}