async function getUserDetail() {
  try {
    const response = await $.ajax({
      method: "GET",
      url: "http://localhost:8080/user/Detail",
      headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
      }
    });
    // Xử lý dữ liệu ở đây nếu cần
    return response.data.user;
  } catch (error) {
    console.error("Lỗi khi lấy dữ liệu:", error);
    throw error;
  }
}

$(document).ready(async function () {
  var url_temp = window.location.href
  const userDetails = await getUserDetail()

  $.ajax({
    method: "GET",
    url: "http://localhost:8080/cart/getAllCarts",
    data: {
      user_id: userDetails.user_id
    }
  })
    .done(function (msg2) {
      if (msg2) {
        var totalPrice = 0;
        $.each(msg2.data, function (index, value) {
          totalPrice += value.productDTO.price * value.quantity
          console.log(totalPrice);
          var html = `<div class="col">
                    <img src="${value.productDTO.image_url}" alt="">
                    <div class="detail">
                      <p class="name" id="${value.productDTO.product_id}">${value.productDTO.shoe_name} - ${value.productDTO.color_name}</p>
                      <p class="price">Giá: ${value.productDTO.price.toLocaleString('vi-VN')} <span>VND</span></p>
                      <div class="row">
                        <div class="size">
                        <p class="name">Size</p>
                          <select name="size" id="size" class="box">
                          <option value="${value.size_id}" id="size" hidden selected>${value.size_name}</option>
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
                            <option value="${value.quantity}" id="${value.quantity}"hidden selected > ${value.quantity} </option>
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
            <p class="price">${totalPrice.toLocaleString('vi-Vn')} <span>VND</span></p>
          </li>
        </ul>
        <button class="pay-btn">THANH TOÁN</button>
      </div>`
        $(".column2").append(order)
      }

    });

})

$(".column1").on("click", ".boxx", function () {
  var detailElement = $(this).closest('.detail');
  var product_id = detailElement.find('.name').attr('id');
  var size_id = detailElement.find('#size').val();
  console.log(product_id)
  console.log(size_id)

  console.log(msg.data)
  console.log(product_id)
  console.log(size_id)
  if (msg) {
    $.ajax({
      method: "DELETE",
      url: "http://localhost:8080/cart/deleteCart",
      data: {
        user_id: userDetails.user_id,
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


document.getElementById("cart").addEventListener("click", function () {
  var token = localStorage.getItem("token");
  localStorage.setItem("url_temp", url_temp)
  if (!token) {
    window.location.href = "./index.html"; // Redirect to login page if token is not present
  }
  window.location.href = "./desktop4.html";
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
