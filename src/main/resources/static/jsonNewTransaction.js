window.addEventListener("load", function () {

    document.getElementById('submit').addEventListener('click',
        function (e) {
            const url = "http://localhost:8080/transactions";
            e.preventDefault();
            const ibanSender = document.getElementById('ibanSender');
            const ibanReceiver = document.getElementById('ibanReceiver');
            const amount = document.getElementById('amount');

            const transaction = {
                "ibanSender": ibanSender.value,
                "ibanReceiver": ibanReceiver.value,
                "amount": amount.value
            };

            fetch(url, {
                method: 'POST',
                body: JSON.stringify(transaction),
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
                        result.innerText = 'Transaction sucessful';
                        document.getElementById('createTransaction').reset();
                    }
                }
            )
        });
});