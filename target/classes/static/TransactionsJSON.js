var xhr;
window.addEventListener("load", function (name, value) {

    document.getElementById('btn_allTransactions').addEventListener('click',function (e) {
        xhr = new XMLHttpRequest();
        var url = 'http://localhost:8080/transactions'
        console.log(url);

        var nameSender = document.getElementById('nameSender_search').value;
        var transactionId = document.getElementById('transactionId_search').value;
        var IBAN = document.getElementById('IBAN_search').value;
        var transferAmount = document.getElementById('transferAmount_search').value;
        var MaxNumberOfResults = document.getElementById('MaxNumberOfResults_search').value;
        url = url + '?transactionId='+transactionId+
            '&IBAN='+IBAN+
            '&nameSender='+nameSender+
            '&transferAmount='+transferAmount+
            '&MaxNumberOfResults='+MaxNumberOfResults;

        console.log(url);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4){
                CreateTableFromJSON();
            }
        };
        xhr.open('GET', url);
        const session = sessionStorage.getItem("X-AUTHENTICATION");
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.setRequestHeader("X-AUTHENTICATION", session);
        xhr.send();
    });


    document.getElementById('btn_sendTransaction').addEventListener('click', function (e) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/transactions');
        //Send the proper header information along with the request
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        const session = sessionStorage.getItem("X-AUTHENTICATION");
        xhr.setRequestHeader("X-AUTHENTICATION", session)
        xhr.onload = function () {
                switch (xhr.status) {
                    case 201:
                        alert(xhr.status + ":" + xhr.responseText);
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
            console.log(xhr.responseText);
        }
        xhr.send(JSON.stringify({
            "ibanSender": document.getElementById('ibanSender').value,
            "ibanReceiver": document.getElementById('ibanReceiver').value,
            "nameSender": document.getElementById('nameSender').value,
            "transferAmount": document.getElementById('transferAmount').value
        }, ));

    });
});

function CreateTableFromJSON() {
    var myItems = JSON.parse(xhr.responseText);

    // EXTRACT VALUE FOR HTML HEADER.
    // ('Book ID', 'Book Name', 'Category' and 'Price')
    var col = [];
    for (var i = 0; i < myItems.length; i++) {
        for (var key in myItems[i]) {
            if (col.indexOf(key) === -1) {
                col.push(key);
            }
        }
    }
    // CREATE DYNAMIC TABLE.
    var table = document.createElement("table");

    // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

    var tr = table.insertRow(-1);                   // TABLE ROW.

    for (var i = 0; i < col.length; i++) {
        var th = document.createElement("th");      // TABLE HEADER.
        th.innerHTML = col[i];
        tr.appendChild(th);
    }

    // ADD JSON DATA TO THE TABLE AS ROWS.
    for (var i = 0; i < myItems.length; i++) {

        tr = table.insertRow(-1);

        for (var j = 0; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.innerHTML = myItems[i][col[j]];
        }
    }

    // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
    var divContainer = document.getElementById('showData');
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
}


