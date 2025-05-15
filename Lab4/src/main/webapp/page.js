document.addEventListener("DOMContentLoaded", function(){
	
    // Insert Button - Event Listener
    let register_button = document.querySelector("#register-button");
    register_button.addEventListener("click", function() {
        
		// Get Data from Input Fields
		let username_value = document.querySelector("#username").value;
		let password_value = document.querySelector("#password").value;
		
		// Get Output Area Div Element
		let outDiv1 = document.querySelector("#output-div-1");
		outDiv1.innerHTML = "Register button pressed ... Data given: " + username_value + " , " + password_value;
		
		// Call REST service and update Div Element
		fetch("SiteUsers", {
				method: "PUT", 
				body: JSON.stringify({
					"username": username_value,
					"password": password_value
				})
			}).then(response => {
				if (response.ok) {
					return response.json();
				} else {
					throw new Error("Unexpected problem: " + response.status);	
				}
			}).then(json_data => {
				// Clear From Input Fields
				document.getElementById("form-id").reset();
				// Update Area					
				if (json_data.insert_flag) {
					outDiv1.innerHTML = "<span class='greentext'>Response: " + JSON.stringify(json_data) + "</span>";
				} else {
					outDiv1.innerHTML = "<span class='redtext'>Response: " + JSON.stringify(json_data) + "</span>";
				}		
			}).catch(error => {
				console.log("Fetching problem: " + error);
			});			
	});
    
	// View Button - Event Listener
	let view_button = document.querySelector("#view-button");
	view_button.addEventListener("click", function(){
		
		// Get Output Area Div Element
		let outDiv2 = document.querySelector("#output-div-2");
		outDiv2.innerHTML = "View button pressed ...";
		
		// Call REST service and update Div Element
		fetch("SiteUsers")
			.then(response => {
				if (response.ok) {
					return response.json();
				} else {
					throw new Error("Unexpected problem: " + response.status);	
				}
			}).then(json_data => {
					// Clear
				    outDiv2.innerHTML = "";
					// Create Paragraph
					let pElement = document.createElement("p");
					pElement.append("Number of records: " + json_data.recordsNumber);
					// Append Paragraph to Div
					outDiv2.append(pElement);
					// Create Table
					let tableElement = document.createElement("table");
					tableElement.className = "users-table";
					// Append Rows to Table
					json_data.userList.forEach(user => {
						let trElement = document.createElement("tr");
						// id
						let td1Element  = document.createElement("td");
						td1Element.append(user.id);
						trElement.append(td1Element);
						// username
						let td2Element  = document.createElement("td");
						td2Element.append(user.username);
						trElement.append(td2Element);		
						// password_hash
						let td3Element  = document.createElement("td");
						td3Element.append(user.password_hash);
						trElement.append(td3Element);
						// Action
						let td4Element  = document.createElement("td");
						let aElement = document.createElement("a");
						aElement.href = "SiteUsers/" + user.id;
						aElement.addEventListener("click", function(e) {
							e.preventDefault();
							// Call REST service and Remove HTML Row from Table
							fetch(this.href, { method: "DELETE" })
								.then(response => {
									if (response.ok) {
										// On success remove HTML Row from Table
										this.parentElement.parentElement.remove(); 
										// The paragraph element should change !
										pElement.className = "redtext";
									} else {
										throw new Error("Unexpected problem: " + response.status);	
									}
								});
						});
						aElement.append("Remove");
						td4Element.append(aElement);
						trElement.append(td4Element);
						// Append Row to Table
						tableElement.append(trElement);
					});
					// Append Table to Div
					outDiv2.append(tableElement);
			}).catch(error => {
				console.log("Fetching problem: " + error);
			});	
	});
	
});
