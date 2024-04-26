$(document).ready(function () {
  var link_product = "http://localhost:8080/product/allproduct"
  $.ajax({
    method: "GET",
    url: link_product

  })
    .done(function (msg) {
      if (msg) {
        $.each(msg.data, function (index, value) {
          console.log(value);
          var html = `<div class="san-pham">
                    <div class="container-hover-image">
                      <a href=""><img class="rectangle-38" img src="${value.image_url}" alt=""></a>
                      <div class="button-hover"><a class="mua-ngay" href="#"> MUA NGAY </a></div>
                    </div>
                    <a href="" class="ten-giay">
                      ${value.shoe_name}
                    </a>
                    <span class="vnd">
                      ${value.price} VND
                    </span>
                  </div>`

          $("#container-san-pham").append(html)
        })
      }

    });


  $.ajax({
    method: "GET",
    url: "http://localhost:8080/product/allstylename"

  })
    .done(function (msg) {
      if (msg) {
        $.each(msg.data, function (index, value) {
          console.log(value);
          var html = ` <input type="checkbox" class="myinputCheckbox" id=" ${value}">
                    <label for=" ${value}">
                        <span class="text"> ${value}</span>
                    </label>`

          $("#kieu-dang").append(html)
        })
      }

    });

  $.ajax({
    method: "GET",
    url: "http://localhost:8080/product/allcategoryname"

  })
    .done(function (msg) {
      if (msg) {
        $.each(msg.data, function (index, value) {
          console.log(value);
          var html = ` <input type="checkbox" class="myinputCheckbox" id=" ${value}">
          <label for=" ${value}">
              <span class="text"> ${value}</span>
          </label>`

          $("#dong-san-pham").append(html)
        })
      }

    });

  $.ajax({
    method: "GET",
    url: "http://localhost:8080/product/allmaterialname"

  })
    .done(function (msg) {
      if (msg) {
        $.each(msg.data, function (index, value) {
          console.log(value);
          var html = ` <input type="checkbox" class="myinputCheckbox" id=" ${value}">
          <label for=" ${value}">
              <span class="text"> ${value}</span>
          </label>`

          $("#chat-lieu").append(html)
        })
      }

    });



})
