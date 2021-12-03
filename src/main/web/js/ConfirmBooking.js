function confirmBooking(first_name, last_name, phone_num, email, date_of_birth, address){

    // TODO: Add Java code --> Return false if unsuccessful

    let successfullyBooked = true;

    // TODO: Error text to tell the user the invalid input if any

    let error = ''

    console.log(first_name);
    console.log(last_name);
    console.log(phone_num);
    console.log(email);
    console.log(date_of_birth);
    console.log(address);

    if (!successfullyBooked) {
        alert(error);
    }

    return successfullyBooked;
}

function getBookingInformation() {
    let flightDetails;

    // TODO Put the raw route data in here
    let rawData = [];

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

    const content = `<h2>Route #`+rawData[i]['id']+`</h2>
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
    </div>`

    return content;
}