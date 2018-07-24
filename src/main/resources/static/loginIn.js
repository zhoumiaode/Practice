function login(){
    $.ajax({
        type: "GET",
        url: "/api/role",
        data: null,
        contentType: "application/json",
        dataType: "json",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", "Bearer 1eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWNrIiwicm9sZXMiOiJqYWNrIiwiaWF0IjoxNTMxMzgyMjY1fQ.xUtmEjxI_U-CEUv2XtJMZplg5KMlaV0tRUM0X9FyrPM");
        },
        success: function(data) {
             if(data.code==0){
                 window.location.href="hello.html";
             }
        },
        error: function(data) {
            console.log(data)
            /*if(data.responseJSON.status=='500'){
                window.location.href="login.html";
            }*/
        }
    });
}