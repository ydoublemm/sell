package ymm.sell.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ymm.sell.VO.ProductInfoVO;
import ymm.sell.VO.ProductVO;
import ymm.sell.VO.ResultVO;
import ymm.sell.dataproject.ProductCategory;
import ymm.sell.dataproject.ProductInfo;
import ymm.sell.service.CategoryService;
import ymm.sell.service.ProductService;
import ymm.sell.utils.ResultVOUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: ymm
 * @Date: 2018/8/1 16:05
 * @Description:
 */

@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResultVO list(){

        //1.查询所有上架商品
        List<ProductInfo>  productInfoList  = productService.findUpAll();

        //2.查询上架商品类目
        List<Integer> categoryTypeList = new ArrayList<>();
        //把上架商品中的类目全部取出，封装到list，然后查询
        for(ProductInfo p : productInfoList){
            categoryTypeList.add(p.getCategoryType());
        }
        //查询上架商品的类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装，把类目和商品对应起来
        List<ProductVO> productVOList  = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productVO.getCategoryType())){
                    ProductInfoVO productInfoVO =new ProductInfoVO();
                    //把productInfo 中的属性 拷贝到productInfoVO
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return  ResultVOUtil.success(productVOList);
    }
}
