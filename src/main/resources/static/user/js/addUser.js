-$(document).ready(function (){

    $('body').on('click', '#submit', function(){

        let firstname = $("#firstname").val();
        let lastname = $("#lastname").val();
        let email = $("#email").val();
        let password = $("#password").val();
        let rPassword = $("#rPassword").val();
        let phone = $("#phone").val();
        let birthdate = $("#birthdate").val();
        let registrationdate = $("#registrationdate").val();
        let rank = $("#rank").val();
        let status = $("#status").val();

        if (password !== rPassword){
            alert("Passwords don't match!");
            return;
        }
        if (firstname === "" || lastname === "" || email === "" || password === "" || rPassword === "" || phone === "" || birthdate === "" || registrationdate === "" || rank === "" || status === "") {
            alert("A field is empty");
            return;
        }

       let xhr = new XMLHttpRequest();
       xhr.open("POST", "http://localhost:8080/users");
        const session = sessionStorage.getItem("X-AUTHENTICATION");
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.setRequestHeader("X-AUTHENTICATION", session);
       xhr.onload = (e) => {
           alert("Successful added User.");
           $(location).attr('href', 'http://localhost:8080/user/AllUsers.html');
       }

       let data = JSON .stringify({
           "firstname": firstname,
           "lastname": lastname,
           "email": email,
           "password": password,
           "phone": phone,
           "birthdate": birthdate,
           "registrationdate": new Date().toLocaleDateString(),
           "rank": rank,
           "status": status
        });

        xhr.send(data);
    });
});
