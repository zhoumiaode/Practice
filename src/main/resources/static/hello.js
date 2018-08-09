function login() {
    var str='{"name":"cpf","age":"23"}';
    $.ajax({
        type: "GET",
        url: "/GG",
        data: JSON.parse(str),
        contentType: "application/json",
        dataType: "json",
        before:function (request) {
            request.setRequestHeader("hello","hello");
        },
        success:function (data) {
            console.log(data);
        }

    });
}