$(document).ready(function(){
     $("#login").click(function(){

     var username = $("#username").val();
     var password = $("#password").val();

    // Checking for blank fields.
    if( username =='' || password ==''){
    $('input[type="text"],input[type="password"]').css("border","2px solid red");
    $('input[type="text"],input[type="password"]').css("box-shadow","0 0 3px red");
        alert("Please fill all fields!!!!!!");
    }else {
        $.ajax({
            type: 'post',
            url: "/todolist/rest/users/login",
            data: JSON.stringify({"username": username, "password":password}),
            contentType: 'application/json',
            success: function (result) {
               console.log(result);
               location.href = "todolist.html"+ '#' + result.id
            },
            error: function (e) {
               console.log(e);
            }
        })
     }
     }
     )
     });

$(document).ready(function(){
     $("#register").click(function(){

     var username = $("#username").val();
     var password = $("#password").val();

    // Checking for blank fields.
    if( username =='' || password ==''){
    $('input[type="text"],input[type="password"]').css("border","2px solid red");
    $('input[type="text"],input[type="password"]').css("box-shadow","0 0 3px red");
        alert("Please fill all fields!!!!!!");
    }else {
        $.ajax({
            type: 'post',
            url: "/todolist/rest/users/register",
            data: JSON.stringify({"username": username, "password":password}),
            contentType: 'application/json',
            success: function (result) {
               console.log(result);
               location.href = "todolist.html"+ '#' + result.id
            },
            error: function (e) {
               console.log(e);
            }
        })
     }
     }
     )
     });


