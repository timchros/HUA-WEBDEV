/* JavaScript Code */

let global_number =  0;

document.addEventListener("DOMContentLoaded", function(){

    // Helper Function
    function addInHiddenData(v1, v2) {
        let ja = JSON.parse(document.querySelector("#hiddendata").value);
        ja.push({value: v1, text: v2});
        document.querySelector("#hiddendata").value = JSON.stringify(ja);
    }

    // Add LI Element Button Event Handler
    document.querySelector("#addbutton").addEventListener("click", function(e) {
        console.log("Add button Event handler");
        global_number++;
        // Find Selected
        let selected = document.querySelector("#selectid");
        if (selected.value === "") return;
        let option = selected.querySelector("option[value=" + selected.value +  "]");
        let litext = selected.value + " - " + option.innerText;
        // Create LI element
        let li = document.createElement("li")
        // Add LI Text
        li.append(global_number + " :: " + litext);
        // Add LI IMG
        let img = document.createElement("img");
        img.src = "imgs/x.png";
        img.className ="ximg";
        img.addEventListener("click", function(e) {
            console.log("Remove LI  Event handler");
             this.parentElement.remove(); 
        });
        li.append(img);
        // Append LI to unordered list
        let ul = document.querySelector("#mylist");
        ul.append(li);
        // Add in the Hidden Area
        addInHiddenData(selected.value, option.innerText);
    });

    // Show Hidden Data Button
    document.querySelector("#hiddendatabutton").addEventListener("click", function(e) {
        console.log("Show Hidden Data  Event handler");
        // Show Hidden Data in a pop-up window
        alert("Hidden data: " + document.querySelector("#hiddendata").value);
        // Show LI elements 
        let lis = document.querySelectorAll("li");
        lis.forEach(function(elem, index){
            console.log(index + " -- " + elem.innerText);
        });
        // Stop probagation
        e.stopPropagation();
    });

    document.querySelector("#maindiv").addEventListener("click", function(e){
        console.log("Div Event Handler.");
    } /*, true */);


    // Pup up with Title (custom attribute)
    let mymyeList =  document.querySelectorAll("*[mymy]");
    for (let index = 0; index < mymyeList.length; index++) {
        const element = mymyeList[index];
        element.addEventListener("mouseenter", function(e){
            let msg = this.getAttribute("mymy");
            let cursorX = e.pageX;
            let cursorY = e.pageY;
            // console.log("mouse:: " + msg + " :: x=" + cursorX + " , y= " + cursorY);
            let popup = document.querySelector("#popup");
            popup.innerText = msg;
            popup.style.display = "block";
            popup.style.left = cursorX + "px";
            popup.style.top = cursorY + "px";
        });
        element.addEventListener("mouseleave", function(e){
            // console.log("mouse leave");
            document.querySelector("#popup").style.display = "none";
        });
    }

});
