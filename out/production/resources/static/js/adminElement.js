$(document).ready(function () {
    var dataProduct = {};

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-product-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#input-select-img-product").change(function() {
        readURL(this);
        var formData = new FormData();

        NProgress.start();
        formData.append('file', $("#input-select-img-product")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){

            NProgress.done();
            console.log(dataProduct.id);
            if(res.data.success) {
                $('#preview-product-img').attr('src', res.data.link);
                dataProduct.image = res.data.link;

            }
        }, function(err){
            NProgress.done();
        });
        console.log(dataProduct.id);
    });

    $('#datepicker-created-date-product').datetimepicker();

    $("#btn-new-product").on("click",function () {
        dataProduct ={};
        $('#preview-product-img').attr('src','/img/default-img.png');
        $('#input-product-name').val("");
        $('#input-product-desc').val("");
        $("#product-category").val("1");
        $('#input-product-price').val("");
        $('#input-product-quantity').val("");
        $("#modal-create-product").modal();
    });



    $(".btn-edit-product").on("click", function () { //Ham de edit san pham
        var productInfo = $(this).data("product"); //tao 1 bien lay data cua product
        NProgress.start();
        axios.get("/api/product/detail/" + productInfo).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                dataProduct.id = res.data.data.id;
                dataProduct.image = res.data.data.image;
                $("#input-product-name").val(res.data.data.name);
                $("#input-product-desc").val(res.data.data.shortDesc);
                $('#preview-product-img').attr('src', dataProduct.image);
                $('#input-product-price').val(res.data.data.price);
                $('#input-product-quantity').val(res.data.data.quantity);
                $("#product-category").val(res.data.data.category.id);
                console.log(dataProduct.image);
                var createdDate = moment(res.data.data.createdDate, "YYYY-MM-DD HH:mm:ss");
                $('#datepicker-created-date-product').data("DateTimePicker").date(createdDate);
                $("#modal-create-product").modal();
                console.log(dataProduct.categoryId);
            }
        }, function (err) {
            NProgress.done();
            swal(
                'Error',
                'Some error when updating product',
                'error'
            );
        })

    });

    $(".btn-save-product").on("click",function () {
        var productInfo = $(this).data("product");
        if($("#input-product-name").val() === "" || $("#input-product-desc").val() === "" || dataProduct.image === undefined) {
            swal(
                'Abort',
                'Please fill all values',
                'error'
            );
            return;
        }
        console.log(dataProduct.id);
        var createdDate = null;
        if($("#datepicker-created-date-product").data("DateTimePicker").date()) {
            createdDate = $("#datepicker-created-date-product").data("DateTimePicker").date().format("YYYY-MM-DD HH:mm:ss")
        }
        dataProduct.name = $('#input-product-name').val();
        dataProduct.shortDesc = $('#input-product-desc').val();
        dataProduct.price = $('#input-product-price').val();
        dataProduct.quantity = $('#input-product-quantity').val();
        dataProduct.createdDate = createdDate;
        dataProduct.categoryId = $('#product-category').val();

        NProgress.start();

        var linkPost = "/api/product/create-product";
        if(dataProduct.id) {
            linkPost = "/api/product/update-product/" + dataProduct.id;
        }
        axios.post(linkPost, dataProduct).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
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
                'Some error when saving product',
                'error'
            );
        })
    });

    $(".btn-delete-product").on('click',function (res) {
        var productInfo = $(this).data("product");
        swal({
                title: 'Are you sure?',
                text: 'You wont be able to revert this',
                type: 'warning',
                showCancelButton: true
            }
        ).then(function (result) {
            if(result.value) {
                NProgress.start();
                axios.post("/api/product/delete-product",{
                    productId: productInfo
                }).then(function (res) {
                    NProgress.done();
                    if(res.data.success) {
                        swal(
                            'Well done',
                            res.data.message,
                            'success'
                        ).then(function () {
                            location.reload();
                        });
                    }else {
                        swal(
                            'Error',
                            res.data.message,
                            'error'
                        );
                    }
                },function (err) {
                    NProgress.done();
                    swal(
                        'Error',
                        'Errors occured while deleting products',
                        'error'
                    )
                })
            }
        })
    });

    $('#table_id').DataTable({
        "pagingType" : "full_numbers"
    });
    $('#table_id_mcb').DataTable({
        "pagingType" : "full_numbers"
    });
    $('#table_id_xdttd').DataTable({
        "pagingType" : "full_numbers"
    });
    $('#table_id_dcth').DataTable({
        "pagingType" : "full_numbers"
    });
    $('#table_id_dcy').DataTable({
        "pagingType" : "full_numbers"
    });
    $('#table_id_dcbl').DataTable({
        "pagingType" : "full_numbers"
    });
    $('#table_id_gtt').DataTable({
        "pagingType" : "full_numbers"
    });
});