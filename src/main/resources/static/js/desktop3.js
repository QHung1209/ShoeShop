var swiper = new Swiper(".mySwiper", {
    slidesPerView: 3,
    spaceBetween: 30,
    freeMode: true,
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
});

const light_orange_color = '#f75f1d';
const  grey_color = '#4d4d4d';

const info_btn = document.getElementById('info_btn');
const exchange_btn = document.getElementById('exchange_btn');
const row2_info = document.getElementById('row2_info');
const row2_exchange = document.getElementById('row2_exchange');
const change1 = document.getElementById('change1');
const change2 = document.getElementById("change2");

info_btn.addEventListener('click', function() {
  if(row2_info.style.display == 'none'){
    row2_info.style.display = 'block';
    info_btn.classList.replace('bx-chevron-down','bx-x');
    info_btn.style.color = light_orange_color; 
    change1.style.color = light_orange_color;
  } else {
    row2_info.style.display = 'none';
    info_btn.classList.replace('bx-x','bx-chevron-down');
    info_btn.style.color = grey_color; 
    change1.style.color = grey_color;
  }
});

exchange_btn.addEventListener('click', function() {
  if(row2_exchange.style.display == 'none'){
    row2_exchange.style.display = 'block';
    exchange_btn.classList.replace('bx-chevron-down','bx-x');
    exchange_btn.style.color = light_orange_color; 
    change2.style.color = light_orange_color;
  } else {
    row2_exchange.style.display = 'none';
    exchange_btn.classList.replace('bx-x','bx-chevron-down');
    exchange_btn.style.color = grey_color; 
    change2.style.color = grey_color;
  }
});