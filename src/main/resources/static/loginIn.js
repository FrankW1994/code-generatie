var xhr;
window.addEventListener("load", function (name, value) {

    function RedirectPage() {
        window.location.replace("http://localhost:8080/transactions.html");
    //    var URL = "http://localhost:8080/transactions.html";
     //   var win = window.open(URL);
    }

    document.getElementById('button_login').addEventListener('click', function (e) {

        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/login');
        //Send the proper header information along with the request
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json")
        xhr.setRequestHeader("X-AUTHENTICATION", "")

        xhr.onload = function () {
            switch (xhr.status) {
                case 200:
                    alert(xhr.status + ":" + xhr.responseText);
                    let response = JSON.parse(xhr.response);
                    console.log(response);
                    sessionStorage.setItem("X-AUTHENTICATION", response.apiKey);
                    RedirectPage();
                    break;
                case 400:
                    alert(xhr.status + ":" + xhr.responseText);
                    break;
                case 422:
                    alert(xhr.status + ":" + xhr.responseText);
                    break;
                case 500:
                    alert(xhr.status + ":" + xhr.responseText);
                    break;
                default:
                    alert(xhr.status + ":" + xhr.responseText);
                    break;
            }
        }

        xhr.onreadystatechange = function() { // listen for state changes
            if (xhr.readyState == 4 && xhr.status == 200) { // when completed we can move away
                window.location.href = "http://localhost:8080/transactions.html";
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
        xhr.setRequestHeader("ApiKeyAuth", sessionStorage.getItem("AuthToken"));
        xhr.onload = (e) => {
            alert(xhr.status);
            sessionStorage.setItem("ApiKey", "");
            sessionStorage.setItem("userId", "");
            location.reload();
        }
        xhr.send();
    }
});