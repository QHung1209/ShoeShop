function checkSelects() {
    var select1 = document.getElementById('size');
    var select2 = document.getElementById('quantity');

    if (select1.value !== "" && select2.value !== "") {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/inventory/getQuantity",
            data: {
                product_id: $('span.product_id').attr('id'),
                size_id: $('#size').val()
            }
        })
            .done(function (msg) {
                if (msg) {
                    console.log(msg.data)
                    if(msg.data == 0)
                        {
                            alert(`Sản phẩm đã hết size này`)
                            select1.value = "";
                            select2.value = "";
                        }
                }
            });
    }
}
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
                    
                      <span class="product_id" id="${value.product_id}" >Mã sản phẩm: <strong>${value.product_id}</strong></span>
                     
                    </h6>
                    <h5>
                      <span> ${value.price.toLocaleString('vi-VN')} VND</span>
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

        if (!token) {
            window.location.href = "./index.html";
        }
        else {
            console.log($('span.product_id').attr('id'))
            console.log($('#size').val())
            console.log($('#quantity').val())
            $.ajax({
                method: "GET",
                url: "http://localhost:8080/user/Detail",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            })
                .done(function (msg) {
                    if (msg) {
                        $.ajax({
                            method: "POST",
                            url: "http://localhost:8080/cart/insertCart",
                            data: {
                                user_id: msg.data.user.user_id,
                                product_id: $('span.product_id').attr('id'),
                                size_id: $('#size').val(),
                                quantity: $('#quantity').val()
                            },

                        })
                            .done(function (msg2) {
                                if (msg2)
                                    alert("Thêm thành công");
                            })
                    }
                });

        }

    });

    document.getElementById("cart").addEventListener("click", function () {
        var token = localStorage.getItem("token");
        localStorage.setItem("url_temp", url_temp)
        if (!token) {
          window.location.href = "./index.html"; // Redirect to login page if token is not present
        }
        else{
        window.location.href = "./desktop4.html";
        }
      });
    document.getElementById("account").addEventListener("click", function () {
        var token = localStorage.getItem("token");
        localStorage.setItem("url_temp", url_temp)
        if (!token) {
            window.location.href = "./index.html"; // Redirect to login page if token is not present
        }

    });
    document.getElementById("logout").addEventListener("click", function () {
        localStorage.removeItem("token")
        window.location.href = "./desktop1.html"; // Redirect to login page if token is not present

    });

    document.getElementById('size').addEventListener('change', checkSelects);
    document.getElementById('quantity').addEventListener('change', checkSelects);

})  