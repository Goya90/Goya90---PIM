$(document).ready(function() {
    window.myFunction = myFunction;
    
    function myFunction() {
        console.log("yes");
        
        var main = document.getElementById("maincategories").style;
        var minor = document.getElementById("minorcategories").style;
        var text = document.getElementById("normalinput").style;
        
        if (document.getElementById("chosenAttribute").value == "Main Category") {
            main.display = "";
            minor.display = "none";
            text.display = "none";
        } else if (document.getElementById("chosenAttribute").value == "Minor Category") {
            minor.display = "";
            main.display = "none";
            text.display = "none";
        } else {
            main.display = "none";
            minor.display = "none";
            text.display = "";
        }
    }
    
});

