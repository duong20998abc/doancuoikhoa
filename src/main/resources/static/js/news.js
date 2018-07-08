$(document).ready(function () {
    var dataNews = {};
    axios.get("/api/new/getall").then(function (res) {

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#preview-news-img').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#input-select-img-news").change(function () {
            readURL(this);
            var formData = new FormData();
            NProgress.start();
            formData.append('file', $("#input-select-img-news")[0].files[0]);
            axios.post("/api/upload/upload-image", formData).then(function (res) {
                NProgress.done();
                if (res.data.success) {
                    $('#preview-news-img').attr('src', res.data.link);
                    dataNews.img = res.data.link

                }
            }, function (err) {
                NProgress.done();
            })
        });

        $('#datepicker-created-date-news').datetimepicker();

        if (res.data.success) {
            for (var news of res.data.data) {
                $('#Mytable > tbody').append(`
                <tr>
                            <td>${news.newId}</td>
                            <td>${news.title}</td>
                            <td><p>${news.content}</p></td>
                            <td><img src="${news.img}" alt="không hiểu" style="width:250px; height:250px;"></td>
                            <td>${news.createdDate}</td>
                            <td><button class="btn btn-default btn-edit-news" data-id="${news.newId}">Sửa</button> <button class="btn btn-danger btn-remove-news" data-id="${news.newId}">Xóa</button></td>
                        </tr>
                `)
            }


            $('p').readmore({speed: 500});
            $('#Mytable').DataTable();
            // console.log($(".btn-remove-news"))
        }


        $(".btn-create-news").on("click",function () {
            dataNews ={};
            $('#preview-news-img').attr('src','/img/default-img.png');
            $('#input-news-title').val("");
            $('#input-news-content').val("");
            $("#modal-create-news").modal();
        });

        $(".btn-remove-news").on('click', function (res) {
            var newsInfo = $(this).data("id");
            swal({
                    title: 'Are you sure?',
                    text: 'You wont be able to revert this',
                    type: 'warning',
                    showCancelButton: true
                }
            ).then(function (result) {
                if (result.value) {
                    axios.post("/api/new/delete-new/", {newId: newsInfo}).then(function (res) {
                        if (res.data.success) {
                            swal(
                                'Well done',
                                res.data.message,
                                'success'
                            ).then(function () {
                                location.reload();
                            });
                        } else {
                            swal(
                                'Error',
                                res.data.message,
                                'error'
                            );
                        }
                    }, function (err) {
                        swal(
                            'Error',
                            'Errors occured while deleting news',
                            'error'
                        )
                    })

                }
            })
        })
        $(".btn-edit-news").on('click', function () {
            var newsInfo = $(this).data("id");
            NProgress.start();
            axios.get("/api/new/detail/" + newsInfo).then(function (res) {
                NProgress.done();
                if (res.data.success) {
                    dataNews.newId = res.data.data.newId;
                    dataNews.img = res.data.data.img;
                    $("#input-news-title").val(res.data.data.title);
                    $("#input-news-content").val(res.data.data.content);
                    $('#preview-news-img').attr('src', dataNews.img);
                    var createdDate = moment(res.data.data.createdDate, "YYYY-MM-DD HH:mm:ss");
                    $('#datepicker-created-date-news').data("DateTimePicker").date(createdDate);
                    $("#modal-create-news").modal()

                }

            }, function (err) {
                NProgress.done();
                swal(
                    'Error',
                    'Some error when updating news',
                    'error'
                );
            })

        })

        $(".btn-save-news").on("click",function () {

            if($("#input-news-title").val() === "" || $("#input-news-content").val() === "" || dataNews.img=== undefined) {
                swal(
                    'Abort',
                    'Please fill all values',
                    'error'
                );
                return;
            }
            var createdDate = null;
            if($("#datepicker-created-date-news").data("DateTimePicker").date()) {
                createdDate = $("#datepicker-created-date-news").data("DateTimePicker").date().format("YYYY-MM-DD HH:mm:ss")
            }
            dataNews.title= $('#input-news-title').val();
            dataNews.content = $('#input-news-content').val();
            dataNews.createdDate = createdDate;
            NProgress.start();

            var linkPost = "/api/new/create-new";
            if(dataNews.newId) {
                linkPost = "/api/new/update-new/" + dataNews.newId;
            }

            axios.post(linkPost, dataNews).then(function(res){
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
                    'Some error when saving news',
                    'error'
                );
            })
        });


    })
})