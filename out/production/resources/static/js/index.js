$(document).ready(function () {
    /*js for profile.html*/
    $("#button-edit-info").click(function () {
        $('#modal-edit-info').modal('show');
    });

    $(".btn-save-info").on('click',function (res) {
        swal({
            title: 'Chỉnh sửa thành công',
            text: "Thông tin của bạn đã được cập nhật",
            type: 'success',
        });
        // alert("Thông tin của bạn đã được cập nhật");
        $('#modal-edit-info').modal('hide');
    })

    $(".btn-feedback").on('click',function () {
        swal({
            title: 'Thành công',
            text: "Phản hồi của bạn đã được ghi lại",
            type: 'success'

        });
    })

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    function getCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for(var i=0;i < ca.length;i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1,c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
        }
        return null;
    }

    function checkCookie() {
        var user = getCookie("username");
        if (user != "") {
            alert("Welcome again " + user);
        } else {
            user = prompt("Please enter your name:", "");
            if (user != "" && user != null) {
                setCookie("username", user, 365);
            }
        }
    }

    var username = getCookie("username");
    var guid = getCookie("guid");
    var cartid = getCookie("cartid")

    var dataCartProduct = {};

    $(".add-to-cart").on("click", function () {
        var pdInfo = $(this).data("product");

        dataCartProduct.productId=pdInfo;
        dataCartProduct.amount = 1;
        dataCartProduct.cartId =cartid;

        NProgress.start();

        var linkPost = "/api/cart-product/create-cart-product";

        axios.post(linkPost,dataCartProduct).then(function (res) {
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Thêm vào giỏ hàng thành công!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Thêm vào giỏ hàng thất bại',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving product',
                'error'
            );
        });
    });
});