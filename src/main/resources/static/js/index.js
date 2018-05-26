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
})