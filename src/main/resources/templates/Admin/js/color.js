$(document).ready(function () {
  // Gọi hàm lấy dữ liệu và thêm bảng vào khi trang web được tải
  fetchDataAndPopulateTable();
  $("#add-color-btn").click(function () {
    event.preventDefault();
    addColor();
  });

  // Gán sự kiện click cho nút "Lưu"
  $(document).on("click", ".save-btn", function () {
    var row = $(this).closest("tr");
    var newColorName = row.find(".color-name-input").val();
    var colorName = row.data("color-name-id");
    var newColorCode = row.find(".color-code-input").val();
    var colorCode = row.data("color-code-id");
    updateColor(colorName, colorCode, newColorName, newColorCode, row);
  });
});
var itemCount = 0; // Biến đếm số lượng sản phẩm
function fetchDataAndPopulateTable() {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/getColors",
  }).done(function (msg) {
    if (msg.data) {
      existingColorsName = msg.data.map((color) => color.color_name);
      existingColorsCode = msg.data.map((color) => color.color_code);
      var tableHTML = "<table>";
      tableHTML +=
        "<tr><th>STT</th><th>Mã màu giày</th><th>Màu giày </th><th>Chức năng</th></tr>";
      itemCount = 0;
      msg.data.forEach(function (color) {
        itemCount++;
        tableHTML += "<tr data-color-code-id='" + color.color_code + "'>";
        tableHTML += "<tr data-color-name-id='" + color.color_name + "'>";
        tableHTML += "<td>" + itemCount + "</td>";
        tableHTML +=
          "<td><span class='color-code-display'>" +
          color.color_code +
          "</span><input class='color-name-input' type='text' value='" +
          color.color_code +
          "' style='display:none;'/></td>";
        tableHTML +=
          "<td><span class='color-name-display'>" +
          color.color_name +
          "</span><input class='color-name-input' type='text' value='" +
          color.color_name +
          "' style='display:none;'/></td>";
        tableHTML += "<td>";
        tableHTML +=
          "<button class='edit-btn'><i class='fas fa-edit'></i>Sửa</button>";
        tableHTML +=
          "<button class='save-btn' style='display:none;'><i class='fas fa-save'></i>Lưu</button>";
        tableHTML += "</td>";
        tableHTML += "</tr>";
      });
      tableHTML += "</table>";
      $("#table-container").html(tableHTML);

      $(".edit-btn").click(function () {
        var row = $(this).closest("tr");
        row.find(".color-code-display").hide();
        row.find(".color-name-display").hide();
        row.find(".color-code-input").show();
        row.find(".color-name-input").show();
        row.find(".edit-btn").hide();
        row.find(".save-btn").show();
      });
    } else {
      alert("Không có dữ liệu sản phẩm");
    }
  });
}

function addColor() {
  // Lấy giá trị từ ô input
  var colorName = $("#color-name").val();
  var colorCode = $("#color-code").val();

  // Kiểm tra xem người dùng đã nhập đủ thông tin chưa
  if (!colorName || !colorCode) {
    alert("Vui lòng nhập đầy đủ thông tin sản phẩm");
    return;
  }

  // Kiểm tra xem colorCode đã tồn tại chưa
  checkColorCodeExists(colorCode, function (codeExists) {
    if (codeExists) {
      alert("Mã màu đã tồn tại!");
    } else {
      // Kiểm tra xem colorName đã tồn tại chưa
      checkColorNameExists(colorName, function (nameExists) {
        if (nameExists) {
          alert("Tên màu đã tồn tại!");
        } else {
          // Nếu cả colorCode và colorName chưa tồn tại, tạo đối tượng newColor từ dữ liệu người dùng nhập
          var newColor = {
            color_name: colorName,
            color_code: colorCode,
          };

          // Gửi yêu cầu POST để thêm màu mới
          $.ajax({
            method: "POST",
            url: "http://127.0.0.1:8080/admin/product/addColors",
            data: newColor,
          })
            .done(function (response) {
              // Sau khi thêm màu, cập nhật lại bảng dữ liệu
              fetchDataAndPopulateTable();
            })
            .fail(function (xhr, status, error) {
              // Xử lý lỗi nếu có
              console.error("Error:", error);
              alert("Có lỗi xảy ra khi thêm màu!");
            });
        }
      });
    }
  });
}

function updateColor(
  oldColorName,
  oldColorCode,
  newColorName,
  newColorCode,
  row
) {
  checkColorNameExists(newColorName, function (nameExists) {
    if (nameExists) {
      alert("Tên màu đã tồn tại!");
    } else {
      checkColorCodeExists(newColorCode, function (codeExists) {
        if (codeExists) {
          alert("Mã màu đã tồn tại!");
        } else {
          updateColorOnServer(
            oldColorName,
            oldColorCode,
            newColorName,
            newColorCode,
            row
          );
        }
      });
    }
  });
}

// Hàm cập nhật dữ liệu trên giao diện

function updateUI(row, newColorName, newColorCode) {
  row.find(".color-name-display").text(newColorName);
  row.find(".color-code-display").text(newColorCode);
  row.find(".color-name-display").show();
  row.find(".color-code-display").show();
  row.find(".color-name-input").hide();
  row.find(".color-code-input").hide();
  row.find(".edit-btn").show();
  row.find(".save-btn").hide();
  row.data("color-name", newColorName);
  row.data("color-code", newColorCode);
}

function updateColorOnServer(
  colorName,
  colorCode,
  newColorName,
  newColorCode,
  row
) {
  // Update color name first
  $.ajax({
    method: "PUT",
    url: "http://127.0.0.1:8080/admin/product/updateColorName",
    data: {
      color_name: ColorName,
      newColorName: newColorName,
    },
    success: function (response) {
      // After updating color name, update color code
      $.ajax({
        method: "PUT",
        url: "http://localhost:8080/admin/product/updateColorCode",
        data: {
          color_code: colorCode,
          newColorCode: newColorCode,
        },
        success: function (response) {
          // After both updates are successful, update the UI
          updateUI(row, newColorName, newColorCode);
        },
        error: function (xhr, status, error) {
          console.error("Error:", error);
          alert("Có lỗi xảy ra khi cập nhật mã màu!");
        },
      });
    },
    error: function (xhr, status, error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi cập nhật tên màu!");
    },
  });
}

function checkColorCodeExists(colorCode, callback) {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/checkColorCodeExists",
    data: { color_code: colorCode },
  })
    .done(function (msg) {
      // Gọi callback với kết quả kiểm tra (true nếu style đã tồn tại, false nếu chưa)
      callback(msg);
    })
    .fail(function (xhr, status, error) {
      // Xử lý lỗi nếu có
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi kiểm tra code màu!");
    });
}

function checkColorNameExists(colorName, callback) {
  $.ajax({
    method: "GET",
    url: "http://127.0.0.1:8080/admin/product/checkColorNameExists",
    data: { color_name: colorName },
  })
    .done(function (msg) {
      // Gọi callback với kết quả kiểm tra (true nếu style đã tồn tại, false nếu chưa)
      callback(msg);
    })
    .fail(function (xhr, status, error) {
      // Xử lý lỗi nếu có
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi kiểm tra tên màu!");
    });
}
