$(document).ready(function () {
    var dataUser = {};

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-avatar').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#input-select-img-avatar").change(function() {
        readURL(this);
        var formData = new FormData();
        console.log(dataUser.id);
        NProgress.start();
        formData.append('file', $("#input-select-img-avatar")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            console.log(dataUser.id);
            if(res.data.success) {
                $('#preview-avatar').attr('src', res.data.link);
                dataUser.image = res.data.link;
            }
        }, function(err){
            NProgress.done();
        });
        console.log(dataUser.id);
    });

    // $('#datepicker-created-date-user').datetimepicker();

    $("#button-edit-info").on("click", function () {
        var userInfo = $(this).data("user"); //tao 1 bien lay data cua user
        NProgress.start();
        console.log(userInfo);
        axios.get("/api/user/" + userInfo).then(function (res) {
            NProgress.done();
            console.log(userInfo);
            if (res.data.success) {
                dataUser.id = res.data.data.id;
                dataUser.image = res.data.data.image;
                $("#input-name").val(res.data.data.realname);
                $('#input-username').val(res.data.data.username);
                $("#input-gender").val(res.data.data.gender);
                $('#preview-avatar').attr('src', dataUser.image);
                $('#input-address').val(res.data.data.address);
                $('#input-phone').val(res.data.data.phone);
                $('#input-email').val(res.data.data.email);
                console.log(dataUser.image);
                $("#modal-edit-info").modal();
                console.log(dataUser.id);
            }
        }, function (err) {
            NProgress.done();
            swal(
                'Error',
                'Some error when updating user',
                'error'
            );
        })

    });

    $(".btn-save-info").on("click",function () {
        var userInfo = $(this).data("user");
        if($("#input-name").val() === "" || $("#input-email").val() === "" || dataUser.image === undefined) {
            swal(
                'Lỗi',
                'Hãy điền đầy đủ giá trị',
                'error'
            );
            return;
        }
        console.log(dataUser.id);
        // var updatedDate = null;
        // if($("#datepicker-created-date-user").data("DateTimePicker").date()) {
        //     updatedDate = $("#datepicker-created-date-user").data("DateTimePicker").date().format("YYYY-MM-DD HH:mm:ss")
        // }
        dataUser.username= $('#input-username').val();
        dataUser.realname = $('#input-name').val();
        dataUser.gender = $('#input-gender').val();
        dataUser.phone = $('#input-phone').val();
        dataUser.address = $('#input-address').val();
        // dataUser.updatedDate= updatedDate;
        dataUser.email = $('#input-email').val();

        NProgress.start();

        var linkPost = "/api/user/update-user/" + dataUser.id;

        axios.post(linkPost, dataUser).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    window.location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving user',
                'error'
            );
        })
    });

})