$(async function () {
    await loadUser();
});
async function loadUser() {
    fetch("http://localhost:8088/api/user")
        .then(r => r.json())
        .then(data => {
            $('#autUser').append(data.username);
            let roles = data.roles.map(role => " " + role.name);
            $('#autRoles').append(roles);
            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.username}</td>
                <td>${data.surname}</td>
                <td>${data.name}</td>
                <td>${data.age}</td>
                <td>${roles}</td>`
            $('#data').append(user);
        })
        .catch((error) => {
            alert(error);
        });
}