window.addEventListener("load", function () {

    document.getElementById('submit').addEventListener('click',
        function (e) {

            $.ajax('http://localhost:8080/transactions', // request url
                {
                    contentType: "application/json; charset=utf-8",
                    async: true,
                    dataType: 'json',
                    type: "POST",
                    succes: function (response) { // success callback function
                        alert("OK");
                    },
                    error: function(data) {
                        alert("Dynamic content load failed.");
                    }
                });


            const url = "http://localhost:8080/transactions";
            e.preventDefault();
            var list;
            fetch(url, {
                method: 'GET',
                body: JSON.stringify(list),
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(response => {
                    const result = document.getElementById('result');
                    if (response.status >= 400) {
                        result.innerText =
                            response.status === 403 ? 'Unauthorized' : 'Unexpected exception';
                        result.style.color = 'red';
                    } else {
                        result.innerText = response.headers.get('content/type');
                    }
                }
            )
        });
});