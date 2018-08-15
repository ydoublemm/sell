package ymm.sell.form;

import lombok.Data;

/**
 * @Author: ymm
 * @Date: 2018/8/15 11:19
 * @Description:
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
