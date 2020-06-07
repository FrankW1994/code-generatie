var xhr;
window.addEventListener("load", function () {

    document.getElementById('btn_allTransactions').addEventListener('click',function (e) {
        xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4){
                CreateTableFromJSON();
            }
        };
        xhr.open('GET', 'http://localhost:8080/transactions');
        xhr.send();
    });

    document.getElementById('btn_sendTransaction').addEventListener('click', function (e) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/transactions');
        //Send the proper header information along with the request
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json")
        xhr.onload= (e) => {
            //haal transactie op
            alert(xhr.status);
        }
        xhr.send(JSON.stringify({
            "ibanSender": document.getElementById('ibanSender').value,
            "ibanReceiver": document.getElementById('ibanReceiver').value,
            "nameSender": document.getElementById('nameSender').value,
            "transferAmount": document.getElementById('transferAmount').value
        }, ));

        //  xhr.send(document)
        // xhr.send(new Int8Array());
        // xhr.send(document);
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


