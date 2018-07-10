$(document).ready(function () {
    var ajax = new XMLHttpRequest();
    var list = []
    ajax.open("GET", "http://" + window.location.host + "/api/product/getall", true);
    ajax.onload = function () {
        list = JSON.parse(ajax.responseText).data
        var suggest = []
        for (var label of list) {
            suggest.push(reName(label))
        }

        function reName(label) {
            var obj = {}
            obj.label = "<img style='height: 50px;width: 50px' src='" + label.image + "'/> " + "<span style='font-size: 90%'>" + label.name + "</span>"
            obj.value = label.name

            return obj
        }

        new Awesomplete(document.querySelector("#ajax-example input"), {list: suggest});
        // get img sp


    };
    ajax.send();
})