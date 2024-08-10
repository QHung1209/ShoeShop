// package com.example.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.payload.ResponseData;
// import com.example.demo.repository.InventoryRepository;
// import com.example.demo.service.imp.InventoryImp;

// @CrossOrigin(origins = {"http://127.0.0.1:5500"})
// @RestController
// @RequestMapping("/inventory")
// public class InventoryController {
//     @Autowired
//     InventoryImp inventoryImp;

//     @Autowired
//     InventoryRepository inventoryReposity;

//     @GetMapping("/getQuantity")
//     public ResponseEntity<?> getDetail(@RequestParam int product_id, @RequestParam int size_id) {

//         ResponseData responseData = new ResponseData();
//         responseData.setData(inventoryImp.Quantity(product_id, size_id));
//         return new ResponseEntity<>(responseData, HttpStatus.OK);
//     }

//     @GetMapping("/getMost")
//     public ResponseEntity<?> getMost() {

//         ResponseData responseData = new ResponseData();
//         responseData.setData(inventoryReposity.findMostSell());
//         return new ResponseEntity<>(responseData, HttpStatus.OK);
//     }
// }
