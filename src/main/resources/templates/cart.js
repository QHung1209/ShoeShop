function getSelectedValues(button) {
  // Lấy phần tử cha của nút (thẻ div chứa size và số lượng)
  var parentDiv = button.parentNode;

  // Tìm phần tử con chứa size và số lượng
  var sizeSelect = parentDiv.querySelector("#size");
  var quantitySelect = parentDiv.querySelector("#quantity");

  // Lấy giá trị của size và số lượng được chọn
  var selectedSize = sizeSelect.options[sizeSelect.selectedIndex].text;
  var selectedQuantity = quantitySelect.options[quantitySelect.selectedIndex].text;

  // In ra giá trị của size và số lượng được chọn
  console.log("Selected size: " + selectedSize);
  console.log("Selected quantity: " + selectedQuantity);
}

$(document).ready(function () {
  var url_temp = window.location.href



  $.ajax({
    method: "GET",
    url: "http://localhost:8080/cart/getAllCarts"

  })
    .done(function (msg) {
      if (msg) {
        var totalPrice = 0;
        $.each(msg.data, function (index, value) {
          totalPrice+=value.price * value.quantity
          console.log(totalPrice);
          var html = `<div class="col">
                    <img src="${value.image_url}" alt="">
                    <div class="detail">
                      <p class="name" id="${value.product_id}">${value.shoe_name}</p>
                      <p class="price">Giá: ${value.price.toLocaleString('vi-VN')} <span>VND</span></p>
                      <div class="row">
                        <div class="size">
                        <p class="name">Size</p>
                          <select name="size" id="size" class="box">
                          <option value="${value.size_id} id="size" " hidden selected>${value.size_name}</option>
                            <option value="100">38</option>
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
                          <select name="quantity" id="quantity" class="box">
                            <option value="${value.quantity}" id="${value.quantity}" selected > ${value.quantity} </option>
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

                        <button class="boxx" ><i class='bx bx-trash-alt' ></i></button>
                      </div>
                    </div>
                  </div>`

          $(".column1").append(html)
        })
        var order = `<div class="order">
        <ul class="list-group">
          <li class="li1">ĐƠN HÀNG</li>
          <li class="li2">NHẬP MÃ KHUYẾN MÃI</li>
          <li class="li3">
            <input type="text">
            <span>
              <button class="btn" type="button">ÁP DỤNG</button>
            </span>
          </li>
          <li class="li4">
            <p class="order">Đơn hàng</p>
            <p class="price">${totalPrice.toLocaleString('vi-Vn')} <span>VND</span></p>
          </li>
          <li class="li5">
            <p class="discount">Giảm</p>
            <p class="price">0 <span>VND</span></p>
          </li>
          <li class="li6">
            <p class="provisional">Tạm tính</p>
            <p class="price">620.000 <span>VND</span></p>
          </li>
        </ul>
        <button class="pay-btn">THANH TOÁN</button>
      </div>`
      $(".column2").append(order)
      }

    });



  $(".column1").on("click", ".boxx", function () {
    var detailElement = $(this).closest('.detail');
    var product_id = detailElement.find('.name').attr('id');
    var size_id = detailElement.find('#size').val();
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/user/getId",
      headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
      }
    })
      .done(function (msg) {
        console.log(msg.data)
        console.log(product_id)
        console.log(size_id)
        if (msg) {
          $.ajax({
            method: "DELETE",
            url: "http://localhost:8080/cart/deleteCart",
            data: {
              user_id: msg.data.userId,
              product_id: product_id,
              size_id: size_id
            }
          })
            .done(function (msg2) {
              if (msg2)
                location.reload();
            })
        }
      });
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