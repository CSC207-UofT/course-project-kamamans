function verifyInformation(username, password){

    // Call java function to verify login credentials and check format of inputs
    // If successful, return null. If unsuccessful, return error string.
    let signInResult;

    // TODO: To be replaced with java
    if(username==="login"){
        signInResult = null;
    } else {
        signInResult = "There was an error logging in.";
    }
    
    if(signInResult === null) {
        window.location.href = "SearchFlight.html";
    } else {
        window.alert(signInResult);
    }
}