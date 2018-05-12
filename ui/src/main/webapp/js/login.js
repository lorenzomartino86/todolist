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
            url: "http://127.0.0.1:8080/ui-1.0/rest/users/login",
            data: JSON.stringify({"username": username, "password":password}),
            contentType: 'application/json',
            success: function (result) {
               console.log(result);
               location.href = "todolist.html"
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
            url: "http://127.0.0.1:8080/ui-1.0/rest/users/register",
            data: JSON.stringify({"username": username, "password":password}),
            contentType: 'application/json',
            success: function (result) {
               console.log(result);
            },
            error: function (e) {
               console.log(e);
            }
        })
     }
     }
     )
     });
/*
    function(data) {
        if(data=='Invalid Username.......') {
            $('input[type="text"]').css({"border":"2px solid red","box-shadow":"0 0 3px red"});
            $('input[type="password"]').css({"border":"2px solid #00F5FF","box-shadow":"0 0 5px #00F5FF"});
            alert(data);
    }else if(data=='Username or Password is wrong...!!!!'){
            $('input[type="text"],input[type="password"]').css({"border":"2px solid red","box-shadow":"0 0 3px red"});
            alert(data);
    } else if(data=='Successfully Logged in...'){
            $("form")[0].reset();
            $('input[type="text"],input[type="password"]').css({"border":"2px solid #00F5FF","box-shadow":"0 0 5px #00F5FF"});
            alert(data);
    } else{
            alert(data);
    }
    }); */

