var xhr;
window.addEventListener("load", function (name, value) {

    function RedirectPage() {
        var URL = "/Transactions.html";
        window.open(URL);
    }

    document.getElementById('button_login').addEventListener('click', function (e) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/login');
        //Send the proper header information along with the request
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.setRequestHeader("X-AUTHENTICATION", "");
        xhr.onload = function () {
            if (xhr.status == 200) {
                alert(xhr.status + ":" + xhr.responseText);
                let response = JSON.parse(xhr.response);
                console.log(response);
                sessionStorage.setItem("X-AUTHENTICATION", response.apiKey);
                RedirectPage();
            }
        }
        xhr.send(JSON.stringify({
            "username": document.getElementById('username').value,
            "password": document.getElementById('password').value,
        },));
    });


    function DELETELogout() {
        var xhr = new XMLHttpRequest();
        xhr.open('DELETE', 'http://localhost:8080/logout');
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("X-AUTHENTICATION", sessionStorage.getItem("ApiKey"));
        xhr.onload = (e) => {
            alert(xhr.status);
            sessionStorage.setItem("ApiKey", "");
            sessionStorage.setItem("userId", "");
            location.reload();
        }
        xhr.send();
    }
});