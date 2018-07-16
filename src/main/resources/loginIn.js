function login(){
    $.ajax({
        type: "POST",
        url: "/index",
        data: JSON.stringify(mytable.params),
        contentType: "application/json",
        dataType: "json",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function(data) {

        },
        error: function(data) {

        }
    });
}