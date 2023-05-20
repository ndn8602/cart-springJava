package com.example.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.shop.entity.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Long> {

    List<Products> findByProductType_Id(Long typeId);

    @Query("SELECT o FROM Products o WHERE o.name LIKE ?1")
    Products findByName(String name);

    // Lấy ra sản phẩm có cùng mã loại
    @Query("SELECT p FROM Products p WHERE   p.productType.id=?1 ")
    List<Products> findByIdfindByProductTypeId(Integer cid);

    // Lấy ra sản phẩm có cùng mã loại và phân trang
    // @Query("SELECT p FROM Products p WHERE p.category.id=?1")
    // Page<Products> findByCategoryId(Integer integer, Pageable pageable);

    // Tìm kiếm sản phẩm theo tên
    @Query("SELECT o FROM Products o WHERE o.name LIKE ?1")
    Page<Products> findByKeywords(String keywords, Pageable pageable);

    @Query(value = "select * from products where quantity > 0", nativeQuery = true)
    Page<Products> findAllAvailable(Pageable pageable);

    // @Query("SELECT p FROM Products p WHERE p.category.name LIKE ?1 and p.quantity > 0 ")
    // Page<Products> findByCategoryDescription(String integer, Pageable pageable);

    // Hiển thị 8 sản phẩm mới nhập về
    @Query(value = " select top 6 * FROM Products where quantity > 0 ORDER BY id DESC", nativeQuery = true)
    List<Products> top6Product_new();

    // Cập nhật lại số lượng
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE products SET quantity=?1 WHERE id=?2", nativeQuery = true)
    void updateQuantity(Integer newquantity, Integer id);
    
    @Query("SELECT p FROM Products p WHERE p.productType.description LIKE ?1 and p.quantity > 0 ")
    Page<Products> findByProductTypeDescription(String integer, Pageable pageable);

}
