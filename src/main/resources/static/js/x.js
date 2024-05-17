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