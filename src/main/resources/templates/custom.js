$(document).ready(function () {
    $("#btn-login").click(function () {

        var user = $("#username").val();
        var pass = $("#password").val();
        console.log(user)
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/login/signin",
            data: {
                username: user,
                password: pass
            }
        })
            .done(function (msg) {
                if (msg.data) {
                    window.location.href ="./desktop1.html"
                }
                else {
                    alert("Sai thông tin đăng nhập");
                }

            });
    })
})
