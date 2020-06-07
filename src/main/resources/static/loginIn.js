$(document).ready(function (){
    $('body').on('click', '#submit', function(){
        let username = $("#username").val();
        let password = $("#password").val();


        if (username === "" || password === "") {
            alert("A field is empty");
            return;
        }

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/login");
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");


        xhr.onload = (e) => {
            switch (xhr.status) {
                case 200:
                    alert("Successful login.");
                    $(location).attr('href', 'http://localhost:8080/Transactions.html');
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
        }
        let data = JSON .stringify({
            "username": username,
            "password": password,
        });

        xhr.send(data);
    });
});