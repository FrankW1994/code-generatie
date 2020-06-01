window.addEventListener("load", function () {

    document.getElementById('submit').addEventListener('click',
        function (e) {
            const url = "";
            e.preventDefault();
            const type = document.getElementById('type');
            const currency = document.getElementById('currency');

            const account = {
                "userId": 1,
                "IBAN": "Nl1234567",
                "type": type.value,
                "currency": currency.value
            };

            fetch(url, {
                method: 'POST',
                body: JSON.stringify(account),
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
                        result.innerText = 'Account saved';
                        document.getElementById('createAccount').reset();

                    }
                }
            )
        });
});
