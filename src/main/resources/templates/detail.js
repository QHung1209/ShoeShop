$(document).ready(function () {

    let searchParams = new URLSearchParams(window.location.search)
    var url_temp = window.location.href
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/product/detail?id=${searchParams.get("id")}`

    })
        .done(function (msg) {
            if (msg) {
                value = msg.data;
                console.log(value);
                var html = `<ol class="breadcrumb">
                    <li><a href="#">Giày</a></li>
                    <li>|</li>
                    <li><a href="#">${value.category}</a></li>
                    <li>|</li>
                    <li class="active"><a href="#">${value.shoe_name}</a></li>
                    </ol>
                    `
                $(".path-to-product").append(html)
                $(".main-image").append(`<img src="${value.image_url}" alt="">`)

                var html2 = `<h4>${value.shoe_name} - ${value.color_name}</h4>
                    <h6>
                      <span>Mã sản phẩm: <strong>${value.product_id}</strong></span>
                    </h6>
                    <h5>
                      <span> ${value.price} VND</span>
                    </h5>`
                $(".name_id_price").append(html2)

                if (msg.data.related_products) {
                    $.each(msg.data.related_products, function (index, value) {
                        console.log(value);
                        var color = ` <li class="color2" ><a href="desktop3.html?id=${value.product_id}"><i class='bx bxs-square' style="color: ${value.color_code}; "></i></a></li>`

                        $(".related_color").append(color)
                    })
                }

                if (msg.data.inventoryDTOs) {
                    $.each(msg.data.inventoryDTOs, function (index, value) {
                        console.log(value);
                        var size = `<option value="${value.size_id}" id=${value.size_id}>${value.size_name}</option>`

                        $(".size_option").append(size)
                    })
                }
            }

        });

    document.getElementById("add").addEventListener("click", function () {
        var token = localStorage.getItem("token");
        console.log(token)// Assume token is stored in localStorage after login
        if (!token) {
            window.location.href = "./index.html"; // Redirect to login page if token is not present
        }
        else
            alert("them thanh cong");
    });

    document.getElementById("btn-login").addEventListener("click", function () {
        var token = localStorage.getItem("token");
        localStorage.setItem("url_temp",url_temp)
        if (!token) {
            window.location.href = "./index.html"; // Redirect to login page if token is not present
        }
        
    });
    document.getElementById("logout").addEventListener("click", function () {
        localStorage.removeItem("token")
        window.location.href = "./desktop1.html"; // Redirect to login page if token is not present

    });
})  