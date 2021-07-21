const url = 'http://localhost:8080/P1/user/'
document.getElementById("logoutBtn").addEventListener('click', logoutFunc)
document.getElementById("submitBtn").addEventListener('click', submitFunc)
document.addEventListener('DOMContentLoaded', pendingFunc)
document.addEventListener('DOMContentLoaded', pastFunc)

function logoutFunc(){
    location.href = "./login.html"
}

async function submitFunc(){
    let typeInput = document.getElementById("typeID").value
    let amntInput = document.getElementById("amnt").value
    let descInput = document.getElementById("desc").value
    console.log(typeInput)
    console.log(amntInput)
    console.log(descInput)

    let input = {
        reimb_type_id_fk: typeInput,
        reimb_amount: amntInput,
        reimb_description: descInput
    }

    let response = await fetch(url + "addreimbursements", { 
        method: "POST",
        body: JSON.stringify(input),
        credentials: "include"
    })

    if(response.status == 200){
        console.log("good")
    }

    location.reload(true)
}

async function pendingFunc(){
    let response = await fetch(url + "pendingreimbursements", {credentials: 'include'})
    
    //reimb_id serial PRIMARY KEY,
	//reimb_amount INT,
	//reimb_submitted TIMESTAMP,
	//reimb_resolved TIMESTAMP,
	//reimb_description VARCHAR(250),
	//reimb_author_fk INT,
	//reimb_resolver_fk INT,
	//reimb_status_id_fk INT,
	//reimb_type_id_fk INT

    if(response.status == 200){
        let data = await response.json()
        console.log(data)

        for(let reimbursements of data){
            let tr1 = document.createElement("tr")
            let td1 = document.createElement("td")
            td1.innerHTML = reimbursements.reimb_id
            tr1.appendChild(td1)

            let td2 = document.createElement("td")
            
            if(reimbursements.reimb_type_id_fk == 1){
                td2.innerHTML = "Hotel"
                tr1.appendChild(td2)
            } else if(reimbursements.reimb_type_id_fk == 2){
                td2.innerHTML = "Travel"
                tr1.appendChild(td2)
            } else if(reimbursements.reimb_type_id_fk == 3){
                td2.innerHTML = "Food"
                tr1.appendChild(td2)
            } else {
                td2.innerHTML = "Other"
                tr1.appendChild(td2)
            }

            let td3 = document.createElement("td")
            td3.innerHTML = "$" + reimbursements.reimb_amount 
            tr1.appendChild(td3)
    
            let td4 = document.createElement("td")
            td4.innerHTML = reimbursements.reimb_description
            tr1.appendChild(td4)
    
            let td5 = document.createElement("td")
            td5.innerHTML = reimbursements.reimb_submitted
            tr1.appendChild(td5)
    
            let td6 = document.createElement("td")
            td6.innerHTML = reimbursements.reimb_author_fk
            tr1.appendChild(td6)
    
            let td7 = document.createElement("td")
            td7.innerHTML = reimbursements.reimb_resolver_fk
            tr1.appendChild(td7)
            document.getElementById("pendingTableBody").appendChild(tr1)

            let td8 = document.createElement("td")
            if(reimbursements.reimb_status_id_fk == 0){
                td8.innerHTML = "Pending"
                tr1.appendChild(td8)
            } else if(reimbursements.reimb_status_id_fk == 1){
                td8.innerHTML = "Approved"
                tr1.appendChild(td8)
            } else if(reimbursements.reimb_status_id_fk == 2){
                td8.innerHTML = "Denied"
                tr1.appendChild(td8)
            }
        }
    }
}    

//how do i get this to only show reimbursements for the user that's currently logged in?
async function pastFunc(){
    let response = await fetch( url + "pastreimbursements", {credentials: 'include'})
    
    //reimb_id serial PRIMARY KEY,
	//reimb_amount INT,
	//reimb_submitted TIMESTAMP,
	//reimb_resolved TIMESTAMP,
	//reimb_description VARCHAR(250),
	//reimb_author_fk INT,
	//reimb_resolver_fk INT,
	//reimb_status_id_fk INT,
	//reimb_type_id_fk INT

    if(response.status == 200){
        let data = await response.json()
    
        console.log(data)
        for(let reimbursements of data){
            let tr1 = document.createElement("tr")
            let td1 = document.createElement("td")
            td1.innerHTML = reimbursements.reimb_id
            tr1.appendChild(td1)

            let td2 = document.createElement("td")

            if(reimbursements.reimb_type_id_fk == 1){
                td2.innerHTML = "Hotel"
                tr1.appendChild(td2)
            } else if(reimbursements.reimb_type_id_fk == 2){
                td2.innerHTML = "Travel"
                tr1.appendChild(td2)
            } else if(reimbursements.reimb_type_id_fk == 3){
                td2.innerHTML = "Food"
                tr1.appendChild(td2)
            } else {
                td2.innerHTML = "Other"
                tr1.appendChild(td2)
            }

            let td3 = document.createElement("td")
            td3.innerHTML = "$" + reimbursements.reimb_amount 
            tr1.appendChild(td3)
    
            let td4 = document.createElement("td")
            td4.innerHTML = reimbursements.reimb_description
            tr1.appendChild(td4)
    
            let td5 = document.createElement("td")
            td5.innerHTML = reimbursements.reimb_submitted
            tr1.appendChild(td5)
    
            let td6 = document.createElement("td")
            td6.innerHTML = reimbursements.reimb_author_fk
            tr1.appendChild(td6)
    
            let td7 = document.createElement("td")
            td7.innerHTML = reimbursements.reimb_resolver_fk
            tr1.appendChild(td7)
            document.getElementById("pastTableBody").appendChild(tr1)

            let td8 = document.createElement("td")
            if(reimbursements.reimb_status_id_fk == 0){
                td8.innerHTML = "Pending"
                tr1.appendChild(td8)
            } else if(reimbursements.reimb_status_id_fk == 1){
                td8.innerHTML = "Approved"
                tr1.appendChild(td8)
            } else if(reimbursements.reimb_status_id_fk == 2){
                td8.innerHTML = "Denied"
                tr1.appendChild(td8)
            }
        }
    }
}