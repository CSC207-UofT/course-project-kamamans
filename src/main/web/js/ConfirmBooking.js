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
    if (successfullyBooked) {
        alert('Successfully booked flight!')
        window.location.href = "SearchFlight.html";
    }

    return successfullyBooked;
}

function getBookingInformation() {
    let flightDetails;

    // TODO Put the raw route data in here
    let rawData = {
        "departureAirport": {"city": "Toronto", "iataCode": "001"},
        "destinationAirport": {"city": "Vancouver", "iataCode": "003"},
        "departureDate": "12/15/2021",
        "flights": [
            {"departureDate": "12/18/2021",
                "plane": {"brandName": "boeing", "seatCount": 100, "firstClassSeats": 10, "economySeats": 80, "hasVacantSeats": true},
                "price": 2.0,
                "duration": 5.0,
                "sourceAirport": {"city": "Toronto", "iataCode": "001"},
                "destinationAirport": {"city": "Vancouver", "iataCode": "003"} }],
        "price": 2.0,
        "duration": 5.0,
        "id": "0"};

    flightDetails = ``;
    for (let j = 0; j < rawData['flights'].length; j++){
        flightDetails += `
                $`+rawData['flights'][j]['price']+`, `+rawData['flights'][j]['duration']+` hours, 
                `+rawData['flights'][j]['departureDate'].toLocaleString()+`: `+
            rawData['flights'][j]['sourceAirport']['city']+` (`+
            rawData['flights'][j]['sourceAirport']['iataCode']+`) &rarr; `+
            rawData['flights'][j]['destinationAirport']['city']+` (`+
            rawData['flights'][j]['destinationAirport']['iataCode']+`)
            `;
    }

    return `<h2>Route #` + rawData['id'] + `</h2>
    <div class="content">
        <div class="product-detail">
            <h5 class="name" style=" color: white;">
                <a>
                    <span>Estimated Flight Duration: ` +
        rawData['duration'] + ` hours</span>
                </a>
            </h5>
            <p class="price-container" style=" color: white;">
                <span>Estimated Price: $` + rawData['price'] + `</span>
            </p>
            <p style="font-size: 16px; padding-top: 10px; color: white;">
                Departure Airport: ` + rawData['departureAirport']['city'] + ` (` +
        rawData['departureAirport']['iataCode'] + `)
            </p>
            <p style="font-size: 16px; color: white;">
                Destination Airport: ` + rawData['destinationAirport']['city'] + ` (` +
        rawData['destinationAirport']['iataCode'] + `)
            </p>
            <p style=" color: white;">
            ` + flightDetails + `
            </p>
        </div>
    </div>`;
}

function getDetails() {

    // TODO Get the route details here too and fill in the required values below

    let route_id = '5'
    let destination = 'toronto'
    let departure = 'vancouver'
    let departure_date = '05/05/21'

    const details = `<table class="table">
        <tbody>
        <tr class="space-row">
            <th>Full Name:</th>
            <td id="fullname-val">`+(document.getElementById('first_name').value+' '+document.
    getElementById('last_name').value+`</td>
        </tr>
        <tr class="space-row">
            <th>Phone Number:</th>
            <td id="phone-val">`+document.getElementById('phone').value+`</td>
        </tr>
        <tr class="space-row">
            <th>Email:</th>
            <td id="email-val">`+document.getElementById('your_email_1').value+`</td>
        </tr>
        <tr class="space-row">
            <th>Date of Birth:</th>
            <td id="dob-val">`+document.getElementById('date').value+'/'+document.getElementById('month').value+'/'+document.
    getElementById('year').value+`</td>
        </tr>
        <tr class="space-row">
            <th>Address:</th>
            <td id="address-val">`+document.getElementById('address').value)+`</td>
        </tr>
        <tr class="space-row">
            <th>Route ID:</th>
            <td id="route-id-val">`+route_id+`</td>
        </tr>
        <tr class="space-row">
            <th>Destination:</th>
            <td id="destination-val">`+destination+`</td>
        </tr>
        <tr class="space-row">
            <th>Departure:</th>
            <td id="departure-val">`+departure+`</td>
        </tr>
        <tr class="space-row">
            <th>Departure Date:</th>
            <td id="dep-date-val">`+departure_date+`</td>
        </tr>
        </tbody>
    </table>`

    return details
}