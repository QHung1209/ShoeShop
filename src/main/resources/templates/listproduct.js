
function search(search_key) {
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/product/search`,
    data: {
      key: search_key
    }
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
                    
                    <a href="desktop3.html?id=${value.product_id}" class="ten-giay">${value.shoe_name}</a>
                    <span class ="color_name" >
                      ${value.color_name}
                    </span>
                    <span class="vnd">${value.price.toLocaleString('vi-VN')} VND</span>
                </div>`;
          $("#container-san-pham").append(html);
        });
      }
    });
}
function filter(param) {
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/product/filter?${param.toString()}`
  })
    .done(function (msg) {
      if (msg) {
        $("#container-san-pham").empty(); // Xóa hết nội dung cũ trước khi thêm mới
        $.each(msg.data, function (index, value) {
          var priceHtml = "";
          if (value.discount != 0) {
            // Calculate discounted price
            var discountedPrice = value.price * (100 - value.discount) / 100;
            priceHtml = `<span class="vnd">${discountedPrice.toLocaleString('vi-VN')} VND   <s style="text-decoration: line-through; font-weight:500; font-size:14px">${value.price.toLocaleString('vi-VN')} VND</s></span>`;
          } else {
            priceHtml = `<span class="vnd">${value.price.toLocaleString('vi-VN')} VND</span>`;
          }
          var html = `<div class="san-pham">
                    <div class="container-hover-image">
                        <a href="desktop3.html?id=${value.product_id}"><img class="rectangle-38" src="${value.image_url}" alt=""></a>
                        <div class="button-hover"><a class="mua-ngay" href="#"> MUA NGAY </a></div>
                    </div>
                    <a href="desktop3.html?id=${value.product_id}" class="ten-giay">${value.shoe_name}</a>
                    <span class ="color_name" >
                      ${value.color_name}
                    </span>
                    ${priceHtml}
                </div>`;
          $("#container-san-pham").append(html);
        });
      }
    });
}

function SaleOff() {
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/product/saleoff`
  })
    .done(function (msg) {
      if (msg) {
        $("#container-san-pham").empty(); // Xóa hết nội dung cũ trước khi thêm mới
        $.each(msg.data, function (index, value) {
          var priceHtml = "";
          if (value.discount != 0) {
            // Calculate discounted price
            var discountedPrice = value.price * (100 - value.discount) / 100;
            priceHtml = `<span class="vnd">${discountedPrice.toLocaleString('vi-VN')} VND   <s style="text-decoration: line-through; font-weight:500; font-size:14px">${value.price.toLocaleString('vi-VN')} VND</s></span>`;
          } else {
            priceHtml = `<span class="vnd">${value.price.toLocaleString('vi-VN')} VND</span>`;
          }
          var html = `<div class="san-pham">
                    <div class="container-hover-image">
                        <a href="desktop3.html?id=${value.product_id}"><img class="rectangle-38" src="${value.image_url}" alt=""></a>
                        <div class="button-hover"><a class="mua-ngay" href="#"> MUA NGAY </a></div>
                    </div>
                    <a href="desktop3.html?id=${value.product_id}" class="ten-giay">${value.shoe_name}</a>
                    <span class ="color_name" >
                      ${value.color_name}
                    </span>
                    ${priceHtml}
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

  var checkedCheckboxes_gender = document.querySelectorAll('.gender:checked');
  var queryParams_gender = [];
  checkedCheckboxes_gender.forEach(function (checkbox) {
    queryParams_gender.push(checkbox.id);
    console.log(queryParams_gender)
  });
  // Get base URL
  var url = window.location.href.split('?')[0];

  // Additional parameters such as gender and category
  if (queryParams.length > 0 || queryParams_kieudang.length > 0 || queryParams_dongsanpham.length > 0 || queryParams_chatlieu.length > 0 || queryParams_gender.length > 0) {
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

  if (queryParams_gender.length > 0) {
    if (queryParams.length > 0 || queryParams_kieudang.length > 0 || queryParams_dongsanpham.length > 0 || queryParams_chatlieu.length > 0) {
      url += '&gender='; // Add '&' to append to existing params
    }
    else
      url += 'gender=';
    url += queryParams_gender.join(',');
  }

  window.history.replaceState({}, document.title, url);
  let searchParams = new URLSearchParams(window.location.search)
  console.log(searchParams.toString())

  filter(searchParams);

}


$(document).ready(function () {

  var male_check = localStorage.getItem('maleCheckboxState');
  var female_check = localStorage.getItem('femaleCheckboxState');
  var sale_check = localStorage.getItem('saleoff');
  var search_key = localStorage.getItem('search');
  console.log(search_key)
  if (search_key != null) {
    document.getElementById('search-input').value = search_key;
    document.getElementById("container-san-pham").scrollIntoView({ behavior: 'smooth' });
    search(search_key)
    localStorage.removeItem('search')
  }
  else {
    if (male_check != null) {
      var checkbox = document.getElementById('male');
      checkbox.checked = !checkbox.checked;
      $("#container-san-pham").empty();
      filter("gender=male")
      localStorage.removeItem('maleCheckboxState')
      $(document).on('change', '.gender', updateURL);

    }
    else if (female_check != null) {
      var checkbox = document.getElementById('female');
      checkbox.checked = !checkbox.checked;
      $("#container-san-pham").empty();
      filter("gender=female")
      localStorage.removeItem('femaleCheckboxState')
      $(document).on('change', '.gender', updateURL);
    }
    else if (sale_check != null) {
      SaleOff();
      localStorage.removeItem('saleoff')
    }
    else {

      var link_product = "http://localhost:8080/product/allproduct"
      var token = localStorage.getItem("token")
      var url_temp = window.location.href
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
              var priceHtml = "";
              if (value.discount != 0) {
                // Calculate discounted price
                var discountedPrice = value.price * (100 - value.discount) / 100;
                priceHtml = `<span class="vnd">${discountedPrice.toLocaleString('vi-VN')} VND   <s style="text-decoration: line-through; font-weight:500; font-size:14px">${value.price.toLocaleString('vi-VN')} VND</s></span>`;
              } else {
                priceHtml = `<span class="vnd">${value.price.toLocaleString('vi-VN')} VND</span>`;
              }
              var html = `<div class="san-pham">
                    <div class="container-hover-image">
                      <a href="desktop3.html?id=${value.product_id}"><img class="rectangle-38" img src="${value.image_url}" alt=""></a>
                      <div class="button-hover"><a class="mua-ngay" href="#"> MUA NGAY </a></div>
                    </div>
                    <a href="desktop3.html?id=${value.product_id}" class="ten-giay">
                      ${value.shoe_name}
                    </a>
                    <span class ="color_name" >
                      ${value.color_name}
                    </span>
                    ${priceHtml}
                  </div>`

              $("#container-san-pham").append(html)

            })
          }

        });

    }
  }


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

    });

  document.getElementById("cart").addEventListener("click", function () {
    var token = localStorage.getItem("token");
    localStorage.setItem("url_temp", url_temp)
    if (!token) {
      window.location.href = "./index.html"; // Redirect to login page if token is not present
    }
    else {
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

  document.getElementById("search-form").addEventListener("submit", function (event) {
    event.preventDefault();
    document.getElementById("container-san-pham").scrollIntoView({ behavior: 'smooth' });
    search(document.getElementById('search-input').value)
  });


  $(document).on('click', '.sale', SaleOff);
  $(document).on('click', '.male', function () {
    var checkbox = document.getElementById('male');
    var checkbox2 = document.getElementById('female');
    checkbox.checked = true;
    checkbox2.checked = false;
    $("#container-san-pham").empty();
    filter("gender=male")
  });
  $(document).on('click', '.female', function () {
    var checkbox = document.getElementById('female');
    var checkbox2 = document.getElementById('male');
    checkbox2.checked = false;
    checkbox.checked = true;
    $("#container-san-pham").empty();
    filter("gender=female")
  });
  $(document).on('change', '.myinputCheckbox', updateURL);
  $(document).on('change', '.kieu-dang', updateURL);
  $(document).on('change', '.dong-san-pham', updateURL);
  $(document).on('change', '.chat-lieu', updateURL);
  $(document).on('change', '.gender', updateURL);


})
