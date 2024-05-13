$(document).ready(function () {
    var url_temp = window.location.href

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/cart/getAllCarts"

    })
        .done(function (msg) {
            if (msg) {
                $.each(msg.data, function (index, value) {
                    console.log(value);
                    var html = `<div class="col">
                    <img src="${value.image_url}" alt="">
                    <div class="detail">
                      <p class="name">${value.shoe_name}</p>
                      <p class="price">Giá: ${value.price} <span>VND</span></p>
                      <div class="row">
                        <div class="size">
                          <p class="name">Size</p>
                          <select name="size" id="size">
              <option value="${value.size_id}" id="${value.size_name}" hidden disabled selected value> ${value.size_name} </option>
              <option value="100" selected">38</option>
              <option value="101">39</option>
              <option value="102">40</option>
              <option value="103">41</option>
              <option value="104">42</option>
              <option value="105">43</option>
              <option value="106">44</option>
              <option value="107">45</option>
     
            </select>
                        </div>
                        <div class="quantity">
                          <p class="name">Số lượng</p>
                          <select name="quantity" id="quantity">
              <option value="${value.quantity}" id="${value.quantity} hidden disabled selected value> ${value.quantity} </option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
            </select>
                        </div>
                        <button class="boxx"><i class='bx bx-trash-alt' ></i></button>
                      </div>
                    </div>
                  </div>`

                    $(".column1").append(html)
                })
            }
         
        });



    

    document.getElementById("btn-login").addEventListener("click", function () {
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



})  