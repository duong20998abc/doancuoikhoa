$(document).ready(function () {
    $('.dropbtn a').hover(function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
    }, function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
    });
    
    $('.logout').on('click',function () {
        document.cookie = "username=; exprires=Thu, 01 Jan 1970 00:00:00 UTC";
    })
    
})