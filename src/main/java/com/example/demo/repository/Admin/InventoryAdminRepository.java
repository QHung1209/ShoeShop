package com.example.demo.repository.Admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.Admin.InventoryAdminDTO;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Products;
import com.example.demo.entity.Shoes;
import com.example.demo.entity.Sizes;
import com.example.demo.repository.Admin.imp.InventoryAdminRepositoryImp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InventoryAdminRepository implements InventoryAdminRepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;

    public List<InventoryAdminDTO> getInventory() {
        String jpql = "SELECT new com.example.demo.dto.Admin.InventoryAdminDTO(i.inventory_id ,s.name, sz.size_name, i.quantity) "
                +
                "FROM Inventory i " +
                "JOIN i.products p " +
                "JOIN p.shoes s " +
                "JOIN i.sizes sz";

        TypedQuery<InventoryAdminDTO> query = entityManager.createQuery(jpql, InventoryAdminDTO.class);
        return query.getResultList();
    }

    @Transactional
    public Inventory addInventory(String shoeName, int sizeName, int quantity) {
        // Fetch the existing shoe entity by name
        TypedQuery<Shoes> shoeQuery = entityManager.createQuery(
                "SELECT s FROM Shoes s WHERE s.name = :shoeName", Shoes.class);
        shoeQuery.setParameter("shoeName", shoeName);
        Shoes shoe = shoeQuery.getSingleResult();

        // Fetch the existing size entity by name
        TypedQuery<Sizes> sizeQuery = entityManager.createQuery(
                "SELECT sz FROM Sizes sz WHERE sz.size_name = :sizeName", Sizes.class);
        sizeQuery.setParameter("sizeName", sizeName);
        Sizes size = sizeQuery.getSingleResult();

        // Create a new product
        Products product = new Products();
        product.setShoes(shoe);
        entityManager.persist(product);

        // Create a new inventory entry
        Inventory inventory = new Inventory();
        inventory.setProducts(product);
        inventory.setSizes(size);
        inventory.setQuantity(quantity);

        // Persist the new inventory entry
        entityManager.persist(inventory);

        return inventory;
    }

    @Transactional
    public Inventory updateInventory(int inventoryId, int quantity) {
        // Fetch the existing inventory entry
        Inventory inventory = entityManager.find(Inventory.class, inventoryId);

        // Update the inventory quantity
        inventory.setQuantity(quantity);

        // Persist the updated inventory entry
        entityManager.merge(inventory);

        return inventory;
    }
}
