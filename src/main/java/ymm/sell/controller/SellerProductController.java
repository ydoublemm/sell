package ymm.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ymm.sell.dataproject.ProductCategory;
import ymm.sell.dataproject.ProductInfo;
import ymm.sell.form.ProductForm;
import ymm.sell.service.CategoryService;
import ymm.sell.service.ProductService;
import ymm.sell.utils.KeyUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: ymm
 * @Date: 2018/8/13 15:17
 * @Description:
 */
@Controller
@RequestMapping(value = "/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /*
     * @author: ymm
     * @date: 2018/8/13 15:18
     * @param: [page, size, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:   商品列表
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        Page<ProductInfo> productInfoPage = productService.findAll(PageRequest.of(page - 1, size));
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }


    /*
     * @author: ymm
     * @date: 2018/8/13 23:44
     * @param: [productId, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description: 上架商品
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {

        try {
            productService.onSale(productId);
        } catch (Exception e) {
            log.error("【卖家端上架商品】{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "上架成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);

    }


    /*
     * @author: ymm
     * @date: 2018/8/13 23:45
     * @param: [productId, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:   下架商品
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {

        try {
            productService.offSale(productId);
        } catch (Exception e) {
            log.error("【卖家端下架商品】{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "下架成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);

    }

    /*
     * @author: ymm
     * @date: 2018/8/14 1:07
     * @param: [productId, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:   修改,新增商品
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {

        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId).get();
            map.put("productInfo", productInfo);

        }

        //查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    /*
     * @author: ymm
     * @date: 2018/8/14 1:07
     * @param: [form, bindingResult, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:   保存 更新
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productid为空，说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId()).get();
                BeanUtils.copyProperties(form, productInfo);
            } else {
                BeanUtils.copyProperties(form, productInfo);
                productInfo.setProductId(KeyUtil.getUniqueKey());
            }
            productService.save(productInfo);
        } catch (Exception e) {
            map.put("msg", e);
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

}
