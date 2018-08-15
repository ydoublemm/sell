package ymm.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;
import ymm.sell.dataproject.ProductCategory;
import ymm.sell.execption.SellException;
import ymm.sell.form.CategoryForm;
import ymm.sell.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: ymm
 * @Date: 2018/8/15 10:53
 * @Description: 卖家类目
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellCategoryController {

    @Autowired
    private CategoryService categoryService;


    /*
     * @author: ymm
     * @date: 2018/8/15 10:57
     * @param: [map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:类目列表
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/category/list", map);
    }


    /*
     * @author: ymm
     * @date: 2018/8/15 11:06
     * @param: [categoryId, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:   展示
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            Optional<ProductCategory> categoryOptional = categoryService.findOne(categoryId);
            if (categoryOptional.isPresent()) {
                map.put("productCategory", categoryOptional.get());
                return new ModelAndView("/category/index", map);
            } else {
                log.error("【类目展示】 商品类目不存在");
                return new ModelAndView("/category/list", map);
            }
        }

        return new ModelAndView("/category/index", map);
    }


    /*
     * @author: ymm
     * @date: 2018/8/15 11:30
     * @param: [form, bindingResult, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:   修改，保存类目
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory category = new ProductCategory();
        try {
            if (form.getCategoryId() != null) {
                Optional<ProductCategory> categoryOptional = categoryService.findOne(form.getCategoryId());
                if (categoryOptional.isPresent()) {
                    category = categoryOptional.get();
                } else {
                    map.put("msg", "类目不存在");
                    map.put("url", "/sell/seller/category/index");
                    return new ModelAndView("common/error", map);
                }
            }
            BeanUtils.copyProperties(form, category);
            categoryService.save(category);

        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
