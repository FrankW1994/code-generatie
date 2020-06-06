-$(document).ready(function (){

    $('body').on('click', '#submit', function(){

        let firstname = $("#firstname").value;
        let lastname = $("#lastname").value;
        let email = $("#email").value;
        let password = $("#password").value;
        let rPassword = $("#rPassword").value;
        let phone = $("#phone").value;
        let birthdate = $("#birthdate").value;
        let registrationdate = $("#registrationdate").value;
        let rank = $("#rank").value;
        let status = $("#status").value;

        if ($("#password").value !== $("rPassword").value){
            alert("Passwords don't match!");
            return;
        }
        if (firstname === "" || lastname === "" || email === "" || password === "" || rPassword === "" || phone === "" || birthdate === "" || registrationdate === "" || rank === "" || status === "") {
            alert("A field is empty");
            return;
        }

       let xhr = new XMLHttpRequest();
       xhr.open("POST", "http://localhost:8080/users");
       xhr.setRequestHeader("Accept", "application/json");
       xhr.setRequestHeader("Content-type", "application/json");
       xhr.onload = (e) => {
           alert(xhr.status);
       }

       console.log($("#birthdate").value);

       let data = JSON .stringify({
           "id": null,
           "firstname": firstname,
           "lastname": lastname,
           "email": email,
           "password": password,
           "phone": phone,
           "birthdate": birthdate,
           "registrationdate": null,
           "rank": rank,
           "status": status
        });

        xhr.send(data);
    });
});
