package ymm.sell.dataproject.mapper;

import org.apache.ibatis.annotations.*;
import ymm.sell.dataproject.ProductCategory;

import java.util.List;
import java.util.Map;

/**
 * @Author: ymm
 * @Date: 2018/8/17 14:40
 * @Description:
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);


    @Select("SELECT category_id,category_name,category_type FROM product_category WHERE category_type=#{categoryType}")
    @Results({
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
    })
    ProductCategory findByCategoryType(Integer categoryType);


    @Select("SELECT category_id,category_name,category_type FROM product_category WHERE category_name=#{categoryName}")
    @Results({
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
    })
    List<ProductCategory> findByCategoryName(String categoryName);


    @Update("UPDATE product_category set category_name=#{categoryName} WHERE category_type=#{categoryType}")
    int updateCategory(@Param("categoryType") Integer categoryType,@Param("categoryName")String categoryName);

    @Update("UPDATE product_category set category_name=#{categoryName} WHERE category_type=#{categoryType}")
    int updateCategory2(ProductCategory productCategory);


    ProductCategory selectByCategoryType(Integer categoryType);
}
