const url = "http://localhost:8080/P1/"

document.getElementById("loginButton").addEventListener('click', loginFunc)

async function loginFunc() {
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value
    let user = {
        ers_username: username,
        ers_password: password
    }

    let response = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(user),
        credentials: 'include' 
    })

    if(response.status === 200) {
        let loggedInUser = await response.json()
        console.log(loggedInUser)

        if(loggedInUser.user_role_fk == 2){
            location.href = "./manager.html"
        } else if(loggedInUser.user_role_fk == 1){
            location.href = "./user.html"
        }

    } else {
        document.getElementById("failedLogin").innerText = "Couldn't login, please try again."
    }
}