$(async function () {
    await allUsers();
    await newUser();
    deleteUser();
});
async function allUsers() {
    const table = $('#data1');
    table.empty()
    fetch("http://localhost:8088/api/admin")
        .then(r => r.json())
        .then(data => {
            data.forEach(user => {
                let users = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>                            
                            <td>${user.age}</td>
                            <td>${user.roles.map(role => " " + role.name)}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit" data-action="edit" data-id="${user.id}" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete" data-action="delete" data-id="${user.id}" data-target="#delete">Delete</button>
                            </td>
                        </tr>)`;
                table.append(users);
            })
        })
        .catch((error) => {
            alert(error);
        })
}

async function newUser() {
    await fetch("http://localhost:8088/api/roles")
        .then(r => r.json())
        .then(roles => {
            roles.forEach(role => {
                let element = document.createElement("option");
                element.text = role.name;
                element.value = role.id;
                $('#roles')[0].appendChild(element);
            })
        })

    const formAddNewUser = document.forms["formAddNewUser"];

    formAddNewUser.addEventListener('submit', function (event) {
        event.preventDefault();
        let roles = [];
        for (let i = 0; i < formAddNewUser.roles.options.length; i++) {
            if (formAddNewUser.roles.options[i].selected) roles.push({
                id: formAddNewUser.roles.options[i].value,
                name: formAddNewUser.roles.options[i].name
            })
        }

        fetch("http://localhost:8088/api/admin", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: formAddNewUser.username.value,
                name: formAddNewUser.name.value,
                surname: formAddNewUser.surname.value,
                age: formAddNewUser.age.value,
                password: formAddNewUser.password.value,
                roles: roles
            })
        }).then(() => {
            formAddNewUser.reset();
            allUsers();
            $('#allUsersTable').click();
        })
            .catch((error) => {
                alert(error);
            })
    })

}

/*
Скрипт заполняет модальное окно
 */

$(document).ready(function () {
    $('#delete').on("show.bs.modal", function (event) {  //#delete - id модального окна
        const button = $(event.relatedTarget);  //кнопка, которая вызывает модальное окно
        const id = button.data("id"); // Извлечение информации из аттрибута data-id="${user.id}" кнопки, это будет id удаляемого пользователя
        viewDeleteModal(id); //запускаем функцию для заполнения модального окна данными по id пользователя
    })
})

async function viewDeleteModal(id) {
    //Получаем пользователя по id
    let userDelete = await getUser(id);
    let formDelete = document.forms["formDeleteUser"];
    //Заполняем форму полученными данными
    formDelete.id.value = userDelete.id;
    formDelete.username.value = userDelete.username;
    formDelete.name.value = userDelete.name;
    formDelete.surname.value = userDelete.surname;
    formDelete.age.value = userDelete.age;


    $('#deleteRolesUser').empty();

    await fetch("http://localhost:8088/api/roles")
        .then(r => r.json())
        .then(roles => {
            roles.forEach(role => {
                let selectedRole = false;
                for (let i = 0; i < userDelete.roles.length; i++) {
                    if (userDelete.roles[i].name === role.name) {
                        selectedRole = true;
                        break;
                    }
                }
                let element = document.createElement("option");
                element.text = role.name;
                element.value = role.id;
                if (selectedRole) element.selected = true;
                $('#deleteRolesUser')[0].appendChild(element);
            })
        })
        .catch((error) => {
            alert(error);
        })
}

async function getUser(id) {

    let url = "http://localhost:8088/api/admin/" + id;
    let response = await fetch(url);
    return await response.json();
}

function deleteUser() {
    const deleteForm = document.forms["formDeleteUser"];
    deleteForm.addEventListener("submit", function (event) {
        event.preventDefault();
        fetch("http://localhost:8088/api/admin/" + deleteForm.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => {
                $('#deleteFormCloseButton').click();
                allUsers();
            })
            .catch((error) => {
                alert(error);
            });
    })
}