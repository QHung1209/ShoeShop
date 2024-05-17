
$(document).ready(function () {

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/user/Detail",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
        .done(function (msg) {
            if (msg) {
                $.ajax({
                    method: "GET",
                    url: "http://localhost:8080/cart/getAllCarts",
                    data: {
                        user_id: msg.data.user.user_id
                    }   
                })
                    .done(function (msg2) {
                        if (msg2) {
                            var total = 0;
                            $.each(msg2.data, function (index, value) {
                                total += value.productDTO.price * value.quantity;
                                var product = `<div class="row2">
                                                        <p class="name">${value.productDTO.shoe_name} - Insignia Blue</p>
                                                        <p class="price">${(value.productDTO.price * value.quantity).toLocaleString('vi-VN')}</p>
                                                    </div>
                                        
                                                    <div class="row3">
                                                        <p class="size">Size: ${value.size_name}</p>
                                                        <p class="quantity">x ${value.quantity}</p>
                                                    </div>`

                                $(".list_product").append(product)
                            })
                            var price = `<div class="row4">
                                            <p class="order">Đơn hàng</p>
                                            <p class="price">${total.toLocaleString('vi-VN')} VND</p>
                                        </div>
                            
                                        <div class="row5">
                                            <p class="promo">Giảm</p>
                                            <p class="price">-124.000 VND</p>
                                        </div>
                            
                                        <div class="row6">
                                            <p class="ship-fee">Phí vận chuyển</p>
                                            <p class="price">0 VND</p>
                                        </div>`
                            $(".price_detail").append(price)

                            const parts = msg.data.user.address;
                            const cleanedParts = parts.split(',').map(part => part.trim());
                            console.log(cleanedParts)
                            document.getElementById('cusNam').value = `${msg.data.user.name}`;
                            document.getElementById('cusNumber').value =`${msg.data.user.telephone}`;
                            document.getElementById('cusEmail').value =`hehehe`;
                            document.getElementById('cusAddress').value = `${cleanedParts.slice(0, cleanedParts.length - 3)}`;

                            $(".ls_ward").append(`<option value="${cleanedParts[cleanedParts.length - 1]}" id="${cleanedParts[cleanedParts.length - 1]}" hidden selected>${cleanedParts[cleanedParts.length - 1]}</option>`)
                            $(".ls_district").append(`<option value="${cleanedParts[cleanedParts.length - 2]}" id="${cleanedParts[cleanedParts.length - 2]}" hidden selected>${cleanedParts[cleanedParts.length - 2]}</option>`)
                            $(".ls_province").append(`<option value="${cleanedParts[cleanedParts.length - 3]}" id="${cleanedParts[cleanedParts.length - 3]}" hidden selected>${cleanedParts[cleanedParts.length - 3]}</option>`)
                            
                          
                        }

                        $(".total_price").append(`${total.toLocaleString('vi-VN')} VND`)
                        
                    })
            }
        });
});

