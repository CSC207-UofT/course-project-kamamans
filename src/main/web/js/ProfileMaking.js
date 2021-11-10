function cancelSignUp() {
    window.location.href = "LoginPage.html";
}

function signUp(id, psw, psw_repeat, email, phone) {
    // Add user account information here and verify the format of inputs.
    // If successful, return null. If unsuccessful, return error string.

    let signUpResult;

    // TODO: To be replaced with java
    if(id==="login"){
        signUpResult = null;
    } else {
        signUpResult = "There was an error signing up.";
    }

    if(signUpResult === null) {
        window.location.href = "LoginPage.html";
    } else {
        window.alert(signUpResult);
    }
}