function httpGet(theUrl){
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}




function displayFlights(){
    let url = 'http://localhost:8080/getPotentialFlights'
    let jsondata = httpGet(url);


    const rawData = JSON.parse(jsondata);
    console.log(rawData)
    // Parsing through the placeholder data and turning into html content

    let searchResults = `<div class="container" id="div-container">`
    let flightDetails;

    for (let i = 0; i < rawData.length; i++){
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
                                    Route #`+rawData[i]['id']+`<span>Estimated Flight Duration: `+
                                    rawData[i]['duration']+` hours</span>
                                </a>
                            </h5>
                            <p class="price-container">
                                <span>$`+rawData[i]['price']+`</span>
                            </p>
                            <span class="tag1"></span>
                        </div>
                        <div class="description">
                            <p>This route has `+rawData[i]['flights'].length+` total flight(s). The first flight
                            departs on `+rawData[i]['departureDate']+` and lands in `+
                            rawData[i]['flights'][0]['destinationAirport']['city']+`.</p>
                        </div>
                        <div class="product-info smart-form">
                            <div class="row">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-danger" onClick="selectFlight(`+rawData[i]['id']+`)">Select Flight</button>
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

    for (let i = 0; i < rawData.length; i++) {
        flightDetails = ``;
        for (let j = 0; j < rawData[i]['flights'].length; j++){
            flightDetails += `
                $`+rawData[i]['flights'][j]['price']+`, `+rawData[i]['flights'][j]['duration']+` hours, 
                `+rawData[i]['flights'][j]['departureDate'].toLocaleString()+`: `+
                rawData[i]['flights'][j]['sourceAirport']['city']+` (`+
                rawData[i]['flights'][j]['sourceAirport']['iataCode']+`) &rarr; `+
                rawData[i]['flights'][j]['destinationAirport']['city']+` (`+
                rawData[i]['flights'][j]['destinationAirport']['iataCode']+`)
            `;
        }

        searchResults += `
            <div id="popup`+i+`" class="overlay">
                <div class="popup">
                    <h2>Route #`+rawData[i]['id']+`</h2>
                    <a class="close" href="#">&times;</a>
                    <div class="content">
                        <div class="product-detail">
                            <h5 class="name">
                                <a>
                                    <span>Estimated Flight Duration: `+
                                    rawData[i]['duration']+` hours</span>
                                </a>
                            </h5>
                            <p class="price-container">
                                <span>Estimated Price: $`+rawData[i]['price']+`</span>
                            </p>
                            <p style="font-size: 16px; padding-top: 10px;">
                                Departure Airport: `+rawData[i]['departureAirport']['city']+` (`+
                                rawData[i]['departureAirport']['iataCode']+`)
                            </p>
                            <p style="font-size: 16px;">
                                Destination Airport: `+rawData[i]['destinationAirport']['city']+` (`+
                                rawData[i]['destinationAirport']['iataCode']+`)
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
    let url = 'http://localhost:8080/selectFlight?id='+routeId
    let jsondata = httpGet(url);


    const rawData = JSON.parse(jsondata);
    console.log(rawData)
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

    window.location.href = "SelectFlight.html#flightSelected";
}