const url = 'http://localhost:8080/P1/manager/'
document.getElementById("logoutBtn").addEventListener('click', logoutFunc)
document.getElementById("resolveBtn").addEventListener('click', resolveFunc)
document.addEventListener('DOMContentLoaded', showPendingFunc)
document.addEventListener('DOMContentLoaded', showAllFunc)

function logoutFunc(){
    location.href = "./login.html"
}

//NOTE TO SELF: the old approve/reject functions won't work
//make one function for resolve rather than approve/reject.
//make a form on html to take input to change status of reimb request

async function resolveFunc(){
    let idInput = document.getElementById("id").value
    let statusIdInput = document.getElementById("status").value

    let userInput = {
        reimb_id: idInput,
        reimb_status_id_fk: statusIdInput
    }

    let response = await fetch(url + "resolvereimbursements", { 
        method:"POST",
        body: JSON.stringify(userInput),
        credentials: "include"
        })

    if(response.status == 200){
        console.log("good")
    }

    location.reload(true) //reload page to reflect changes in tables
} 

async function showPendingFunc(){
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

            document.getElementById("pendingTableBody").appendChild(tr1)
        }
    }
}

async function showAllFunc(){
    let response = await fetch(url + "allreimbursements", {credentials: 'include'})
    
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

            document.getElementById("allTableBody").appendChild(tr1)
        }
    }
}