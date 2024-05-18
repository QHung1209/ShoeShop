
function toggleDropdown(event) {
  event.preventDefault();
  var dropdown = document.getElementById("myDropdown");
  dropdown.classList.toggle("show");
}

// Đóng dropdown khi click ra ngoài dropdown
window.onclick = function (event) {
  if (!event.target.matches('.account')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    for (var i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
$(document).ready(function () {

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

})