$(document).ready(function () {
  fetchShoeNames();
  fetchColorNames();
  fetchStyleNames();
  fetchCategoryNames();
  fetchMaterialNames();

  fetchDataAndPopulateTable();

  $("#submit-product-btn").click(function (event) {
    event.preventDefault();
    addProduct();
  });
});

var itemCount = 0;
function fetchDataAndPopulateTable() {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/getProduct",
  }).done(function (msg) {
    if (msg.data) {
      var tableHTML = "<table>";
      tableHTML +=
        "<tr><th>STT</th><th>Tên sản phẩm</th><th>Màu sắc</th><th>Giới tính</th><th>Kiểu dáng</th><th>Chất liệu</th><th>Dòng sản phẩm</th><th>Ảnh miêu ta</th><th>Chức năng</th></tr>";
      itemCount = 0;
      msg.data.forEach(function (product) {
        itemCount++;
        tableHTML +=
          "<tr data-id='" +
          product.product_id +
          "' data-name-id='" +
          product.shoe_name +
          "' data-color-id='" +
          product.color_name +
          "' data-gender-id='" +
          product.gender +
          "' data-material-id='" +
          product.material +
          "' data-category-id='" +
          product.category +
          "' data-style-id='" +
          product.style +
          "'>";
        tableHTML += "<td>" + itemCount + "</td>";

        tableHTML +=
          "<td><span class='name-display'>" +
          product.shoe_name +
          "</span></td>";
        tableHTML +=
          "<td><span class='color-display'>" +
          product.color_name +
          "</span></td>";
        tableHTML +=
          "<td><span class='gender-display'>" + product.gender + "</span></td>";
        tableHTML +=
          "<td><span class='style-display'>" + product.style + "</span></td>";
        tableHTML +=
          "<td><span class='material-display'>" +
          product.material +"</span></td>";
        
        tableHTML +=
          "<td><span class='category-display'>" +
          product.category +
          "</span></td>";

          tableHTML += "<td><img src='" + product.image_url + "' alt='Product Image' style='width: 100px; height: auto;'/></td>"; 
        tableHTML += "<td>";
        tableHTML += "<button class='edit-btn'>Sửa</button>";
        tableHTML += "</td>";
        tableHTML += "</tr>";
      });
      tableHTML += "</table>";
      $("#table-container").html(tableHTML);

      $(".edit-btn").click(function () {
        var row = $(this).closest("tr");
        var productId = row.data("id");
        console.log("Selected Product ID:", productId); // Log the product ID for debugging
        var newName = $("#shoe-name-select");
        var newColor = $("#color-name-select");
        var newGender = $("#gender-name-select");
        var newStyle = $("#style-name-select");
        var newMaterial = $("#material-name-select");
        var newCategory = $("#category-name-select");


        if (
          newName &&
          newColor &&
          newGender &&
          newStyle &&
          newMaterial &&
          newCategory
        ) {
          updateProduct(
            productId,
            newName,
            newColor,
            newGender,
            newStyle,
            newMaterial,
            newCategory
          );
        }
      });
    } else {
      alert("Không có dữ liệu sản phẩm");
    }
  });
}

function updateProduct(
  productId,
  newName,
  newColor,
  newGender,
  newStyle,
  newMaterial,
  newCategory,
  newUrl
) {
  $.ajax({
    method: "PUT",
    url: "http://127.0.0.1:8080/admin/product/updateProduct/" + productId,
    data: JSON.stringify({ quantity: newQuantity }),
    contentType: "application/json",
    success: function (response) {
      alert("Product updated successfully!");
      fetchDataAndPopulateTable();
    },
    error: function (xhr, status, error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi cập nhật số lượng!");
    },
  });
}

function fetchShoeNames() {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/getShoesNames",
  })
    .done(function (data) {
      var shoeNameSelect = $("#shoe-name-select");
      shoeNameSelect.empty(); // Clear existing options
      data.forEach(function (shoeName) {
        shoeNameSelect.append(new Option(shoeName, shoeName));
      });
    })
    .fail(function (xhr, status, error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi lấy tên giày!");
    });
}

function fetchColorNames() {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/getColorsNames",
  })
    .done(function (data) {
      var colorNameSelect = $("#color-name-select");
      colorNameSelect.empty(); // Clear existing options
      data.forEach(function (colorName) {
        colorNameSelect.append(new Option(colorName, colorName));
      });
    })
    .fail(function (xhr, status, error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi lấy tên màu!");
    });
}

function fetchMaterialNames() {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/getMaterialsNames",
  })
    .done(function (data) {
      var materialNameSelect = $("#material-name-select");
      materialNameSelect.empty(); // Clear existing options
      data.forEach(function (materialName) {
        materialNameSelect.append(new Option(materialName, materialName));
      });
    })
    .fail(function (xhr, status, error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi lấy tên màu!");
    });
}

function fetchCategoryNames() {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/getCategoriesNames",
  })
    .done(function (data) {
      var categoryNameSelect = $("#category-name-select");
      categoryNameSelect.empty(); // Clear existing options
      data.forEach(function (categoryName) {
        categoryNameSelect.append(new Option(categoryName, categoryName));
      });
    })
    .fail(function (xhr, status, error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi lấy tên màu!");
    });
}

function fetchStyleNames() {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/getStylesNames",
  })
    .done(function (data) {
      var styleNameSelect = $("#style-name-select");
      styleNameSelect.empty(); // Clear existing options
      data.forEach(function (styleName) {
        styleNameSelect.append(new Option(styleName, styleName));
      });
    })
    .fail(function (xhr, status, error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi lấy tên màu!");
    });
}

function addProduct() {
    var newName = $("#shoe-name-select").val();
    var newColor = $("#color-name-select").val();
    var newGender = $("#gender-name-select").val();
    var newStyle = $("#style-name-select").val();
    var newMaterial = $("#material-name-select").val();
    var newCategory = $("#category-name-select").val();


  if (!newName || !newColor || !newGender || !newStyle || !newMaterial || !newCategory) {
    alert("Vui lòng nhập đầy đủ thông tin sản phẩm");
    return;
  }



  $.ajax({
    method: "POST",
    url: "http://127.0.0.1:8080/admin/product/addProduct",
    contentType: "application/json",
    data: JSON.stringify(newProduct),
  })
    .done(function (response) {
      fetchDataAndPopulateTable();
      alert("Thêm sản phẩm vào kho thành công!");
    })
    .fail(function (xhr, status, error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi thêm sản phẩm vào kho!");
    });
}
