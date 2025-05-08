document.addEventListener("DOMContentLoaded", function(){

    // Username Element
    let password = document.querySelector("#password");
    password.addEventListener("keyup", function(e) {
        let mydiv = document.querySelector("#mydiv");
        console.log("password: " + password.value + " , length: " + password.value.length);
        if (password.value.length < 5) {
            mydiv.innerHTML  = "Password has less than 5 chars !";
        } else {
            mydiv.innerHTML  = "";
        }
    });

});
