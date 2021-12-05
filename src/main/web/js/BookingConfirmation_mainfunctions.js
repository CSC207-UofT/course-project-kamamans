$(function () {
    $("#form-total").steps({
        headerTag: "h2",
        bodyTag: "section",
        transitionEffect: "fade",
        enableAllSteps: true,
        autoFocus: true,
        transitionEffectSpeed: 500,
        titleTemplate: '<div class="title">#title#</div>',
        labels: {
            previous: 'Back',
            next: 'Next',
            finish: 'Confirm',
            current: ''
        },
        onStepChanging: function (event, currentIndex, newIndex) {
            var fullname = $('#first_name').val() + ' ' + $('#last_name').val();
            var room = $('#room').val();
            var day = $('#day').val();
            var time = $('#time').val();

            $('#fullname-val').text(fullname);
            $('#room-val').text(room);
            $('#day-val').text(day);
            $('#time-val').text(time);

            return true;
        }
    });
    $("#day").datepicker({
        dateFormat: "MM - DD - yy",
        showOn: "both",
        buttonText: '<i class="zmdi zmdi-chevron-down"></i>',
    });
});

function confirmBooking(route){
    const url= 'http://localhost:8080/confirmBooking?route='+route
    const done = httpGet(url)
    let updatedRouteHistory;

    if(done==="true"){
        updatedRouteHistory = null;
    } else {
        updatedRouteHistory = "Unable to save route to route history. Please try again.";
    }

    if(updatedRouteHistory === null) {
        window.location.href = "SearchFlight.html";
    } else {
        window.alert(updatedRouteHistory);
    }

}