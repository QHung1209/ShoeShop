
function filter(param) {
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/product/filter?${param.toString()}`
  })
    .done(function (msg) {
      if (msg) {
        $("#container-san-pham").empty(); // Xóa hết nội dung cũ trước khi thêm mới
        $.each(msg.data, function (index, value) {
          var html = `<div class="san-pham">
                    <div class="container-hover-image">
                        <a href="desktop3.html?id=${value.product_id}"><img class="rectangle-38" src="${value.image_url}" alt=""></a>
                        <div class="button-hover"><a class="mua-ngay" href="#"> MUA NGAY </a></div>
                    </div>
                    <a href="" class="ten-giay">${value.shoe_name}</a>
                    <span class="vnd">${value.price} VND</span>
                </div>`;
          $("#container-san-pham").append(html);
        });
      }
    });
}
function updateURL() {
  var checkedCheckboxes = document.querySelectorAll('.myinputCheckbox:checked');
  var queryParams = [];
  checkedCheckboxes.forEach(function (checkbox) {
    queryParams.push(checkbox.id);
  });

  var checkedCheckboxes_kieudang = document.querySelectorAll('.kieu-dang:checked');
  var queryParams_kieudang = [];
  checkedCheckboxes_kieudang.forEach(function (checkbox) {
    queryParams_kieudang.push(checkbox.id);
  });

  var checkedCheckboxes_dongsanpham = document.querySelectorAll('.dong-san-pham:checked');
  var queryParams_dongsanpham = [];
  checkedCheckboxes_dongsanpham.forEach(function (checkbox) {
    queryParams_dongsanpham.push(checkbox.id);
  });

  var checkedCheckboxes_chatlieu = document.querySelectorAll('.chat-lieu:checked');
  var queryParams_chatlieu = [];
  checkedCheckboxes_chatlieu.forEach(function (checkbox) {
    queryParams_chatlieu.push(checkbox.id);
  });
  // Get base URL
  var url = window.location.href.split('?')[0];

  // Additional parameters such as gender and category
  if (queryParams.length > 0 || queryParams_kieudang.length > 0 || queryParams_dongsanpham.length > 0 || queryParams_chatlieu.length > 0) {
    url += '?'; // Add '?' if it doesn't exist
  }

  if (queryParams.length > 0) {
    url += 'price=';
    url += queryParams.join(',');
  }

  if (queryParams_kieudang.length > 0) {
    if (queryParams.length > 0) {
      url += '&style='; // Add '&' to append to existing params
    }
    else
      url += 'style=';
    url += queryParams_kieudang.join(',');
  }

  if (queryParams_dongsanpham.length > 0) {
    if (queryParams.length > 0 || queryParams_kieudang.length > 0) {
      url += '&category='; // Add '&' to append to existing params
    }
    else
      url += 'category=';
    url += queryParams_dongsanpham.join(',');
  }

  if (queryParams_chatlieu.length > 0) {
    if (queryParams.length > 0 || queryParams_kieudang.length > 0 || queryParams_dongsanpham.length > 0) {
      url += '&material='; // Add '&' to append to existing params
    }
    else
      url += 'material=';
    url += queryParams_chatlieu.join(',');
  }

  window.history.replaceState({}, document.title, url);
  let searchParams = new URLSearchParams(window.location.search)
  console.log(searchParams.toString())

  filter(searchParams);

}


$(document).ready(function () {
  var link_product = "http://localhost:8080/product/allproduct"
  var token = localStorage.getItem("token")
  console.log(token)
  $.ajax({
    method: "GET",
    url: link_product,
    headers: {
      'Authorization': `Bearer ${token}`
    }

  })
    .done(function (msg) {
      if (msg) {
        $.each(msg.data, function (index, value) {
          console.log(value);
          var html = `<div class="san-pham">
                    <div class="container-hover-image">
                      <a href="desktop3.html?id=${value.product_id}"><img class="rectangle-38" img src="${value.image_url}" alt=""></a>
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
          var html = ` <input type="checkbox" class="kieu-dang" id="${value}" name="${value}">
                    <label for="${value}">
                        <span class="text"> ${value}</span>
                    </label>`

          $("#kieu-dang").append(html)
        })
      }
      updateURL();
    });

  $.ajax({
    method: "GET",
    url: "http://localhost:8080/product/allcategoryname"

  })
    .done(function (msg) {
      if (msg) {
        $.each(msg.data, function (index, value) {
          console.log(value);
          var html = ` <input type="checkbox" class="dong-san-pham" id="${value}" name="${value}">
          <label for="${value}">
              <span class="text"> ${value}</span>
          </label>`

          $("#dong-san-pham").append(html)
        })
      }
      updateURL();
    });

  $.ajax({
    method: "GET",
    url: "http://localhost:8080/product/allmaterialname"

  })
    .done(function (msg) {
      if (msg) {
        $.each(msg.data, function (index, value) {
          console.log(value);
          var html = ` <input type="checkbox" class="chat-lieu" id="${value}" name="${value}">
          <label for="${value}">
              <span class="text"> ${value}</span>
          </label>`

          $("#chat-lieu").append(html)
        })
      }
      updateURL();
    });



  $(document).on('click', '.myinputCheckbox', updateURL);
  $(document).on('click', '.kieu-dang', updateURL);
  $(document).on('click', '.dong-san-pham', updateURL);
  $(document).on('click', '.chat-lieu', updateURL);



})
