$(document).ready(function() {
    $(".expan").click(function(){
    var tag = $(this);
    if(tag.hasClass("expanded")){
    tag.removeClass("expanded");
    } 
    else {
    tag.addClass("expanded");
    }
    });
});
