package ymm.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ymm.sell.emnums.ProductStatusEnum;

import java.util.List;

/**
 * @Author: ymm
 * @Date: 2018/8/1 23:15
 * @Description:商品包含类目
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
