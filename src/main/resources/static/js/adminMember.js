$(document).ready(function () {
    axios.get("/api/user/all-users").then(function (res){
        if (res.data.success) {
            for (var users of res.data.data) {
                $('#Mytable > tbody').append(`
                <tr>
                            
                            <td>${users.user.id}</td>
                            <td><img src="${users.user.image}" alt="không hiểu" style="width:250px; height:250px;"></td>
                            <td>${users.user.username}</td>
                            <td><p>${users.user.realname}</p></td>
                            <td>${users.user.createdDate}</td>
                            <td>${users.role.desc}</td>
                            <td><button class="btn btn-default btn-edit-news" data-id="${users.user.id}">Ban thành viên</button> 
                            </tr>
                `)
            }



            $('#Mytable').DataTable();
            // console.log($(".btn-remove-news"))
        }
    })

})