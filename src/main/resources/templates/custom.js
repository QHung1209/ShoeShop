$(document).ready(function () {
    $(".btn-login").click(function () {

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
                console.log(msg)
                if (msg.success) {
                    localStorage.setItem("token", msg.data)
                    if (localStorage.getItem("url_temp")) {
                        window.location.href = localStorage.getItem("url_temp")
                    }
                    else
                        window.location.href = "./desktop1.html"

                }
                else {
                    $(".warning").empty()
                    $(".warning").append("Kiểm tra lại tài khoản và mật khẩu")
                }

            });
    })
})
