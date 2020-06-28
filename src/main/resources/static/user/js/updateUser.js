-$(document).ready(function (){

    let urlParam = function(name){

        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        return results[1] || 0;
    };

    loadUser();

    function loadUser(){

        $('#firstname').val(urlParam('firstname'));
        $('#lastname').val(urlParam('lastname'));
        $('#email').val(urlParam('email'));
        $('#phone').val(urlParam('phone'));

        $('#birthdate').val(urlParam('birthdate'));

        $('#rank').val(urlParam('rank'));
        $('#status').val(urlParam('status'));
    }



    $('body').on('click', '#submit', function(){

        let firstname = $("#firstname").val();
        let lastname = $("#lastname").val();
        let email = $("#email").val();
        let phone = $("#phone").val();
        let birthdate = $("#birthdate").val();
        let rank = $("#rank").val();
        let status = $("#status").val();

        if (firstname === "" || lastname === "" || email === "" || phone === "" || birthdate === "" || rank === "" || status === "") {
            alert("A field is empty");
            return;
        }

        let xhr = new XMLHttpRequest();
        xhr.open("PUT", "http://localhost:8080/users/"+ urlParam("id"));
        const session = sessionStorage.getItem("X-AUTHENTICATION");
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.setRequestHeader("X-AUTHENTICATION", session);
        xhr.onload = (e) => {
            switch (xhr.status) {
                case 201:
                    alert("Successful added User.");
                    $(location).attr('href', 'http://localhost:8080/user/AllUsers.html');
                    break;
                case 401:
                    alert("Unauthorized action.");
                    break;
                case 500:
                case 501:
                    alert("Internal server error!");
                    break;
                default:
                    alert(xhr.status);
                    break;
            }
        };

       let data = JSON .stringify({
           "firstname": firstname,
           "lastname": lastname,
           "email": email,
           "phone": phone,
           "birthdate": birthdate,
           "rank": rank,
           "status": status
        });

        xhr.send(data);
    });
});
