
$(document).ready(function (){
    loadUsers();

    function loadUsers() {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(result) {
            if (xhr.readyState === 4){

                let responsData = JSON.parse(xhr.responseText);
                let table =  document.getElementById('result');
                let row, arrayData, editUrlStr, i, j;

                //Iterate through every row off the response data
                for (i = 0; i < responsData.length; i++) {
                    //Make associative array off the object with data
                    arrayData = Object.entries(responsData[i]);
                    row = table.insertRow(i+1);
                    editUrlStr = "";
                    //Iterate through the fields of the response data && Add new Cell in table with data
                    for (j = 0; j < arrayData.length; j++) {

                        if (arrayData[j][0] !== "password") {
                            row.insertCell(j).innerHTML = arrayData[j][1];
                            //create urlParameters for the edit url
                            if (j !== 0) {
                                editUrlStr += "&";
                            }
                            editUrlStr += arrayData[j][0] + "=" + arrayData[j][1];
                        }
                        else{
                            row.insertCell(j).innerHTML = "****";
                        }
                    }

                    row.insertCell(j).innerHTML = "<a class='edit' href='UpdateUsers.html?" + editUrlStr + "'>edit</a>";
                    row.insertCell(j + 1).innerHTML = "<a class='delete' rel='" + responsData[i].id + "' href=''>delete</a>";
                }
            }
        };
        xhr.open('GET', 'http://localhost:8080/users');
        xhr.send();
    }

    $('body').on('click', '.delete', function(){
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(result){
            console.log(xhr);
        };
        let userId = $(this).attr('rel');
        xhr.open('DELETE', 'http://localhost:8080/users/' + userId);
        xhr.send();
    });

    $('body').on('click', '.edit', function(){
        console.log(1);
        $("#result-success").append("<p>test0</p>");

        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(result){
            if (xhr.readyState === 4){

                let responsData = JSON.parse(xhr.responseText);
                console.log(responsData);

                $("#result-success").append("<p>test</p>");

                $("#result-success").append('<form><input type="text" name="userId" id="userId" value="'+ responsData[i].id +'" hidden>' +
                    '<label for="firstname">Firstname: </label>' +
                    '<input type="text" name="firstname" id="firstname" value="'+ responsData[i].firstname +'">' +
                    '<label for="lastname">Lastname: </label>' +
                    '<input type="text" name="lastname" id="lastname" value="'+ responsData[i].lastname +'">' +
                    '<label for="email">Email: </label>' +
                    '<input type="email" name="email" id="email" value="'+ responsData[i].email +'">' +
                    '<label for="phone">Phone: </label>' +
                    '<input type="text" name="phone" id="phone" value="'+ responsData[i].phone +'">' +
                    '<label for="birthdate">Birthdate: </label>' +
                    '<input type="date" name="birthdate" id="birthdate" value="'+ responsData[i].birthdate +'">' +
                    '<label for="registrationdate">Registrationdate: </label>' +
                    '<input type="date" name="registrationdate" id="registrationdate" value="'+ responsData[i].registrationdate +'">' +
                    '<input type="submit" name="update" id="update" value="update">' +
                    '</form>');
            }
        };

        let userId = $(this).attr('rel');
        xhr.open('GET', 'http://localhost:8080/users/' + userId);
        xhr.send();
    });

    
});



