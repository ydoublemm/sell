<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改商品</title>
     <#include "../common/header.ftl">
</head>
<body>



<div id="wrapper" class="toggled">
<#--边栏sidebar-->
    <#include  "../common/nav.ftl">
<#--主要内容content-->
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" method="post" action="/sell/seller/product/save">

                    <div class="form-group">
                        <label>名称</label>
                        <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                    </div>

                    <div class="form-group">
                        <label>价格</label>
                        <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>
                    </div>

                    <div class="form-group">
                        <label>库存</label>
                        <input name="productStock" type="text" class="form-control" value="${((productInfo.productStock)?c)!''}"/>
                    </div>

                    <div class="form-group">
                        <label>描述</label>
                        <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}"/>
                    </div>

                    <div class="form-group">
                        <label>图片</label>
                        <div>
                            <img height="100" width="100" src="${(productInfo.productIcon)!''}">
                            <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label>类目</label>
                        <select name="categoryType" class="form-control">
                            <#list categoryList as category>
                                <option value="${category.categoryType}"
                                    <#if (productInfo.categoryType)?? && productInfo.categoryType==category.categoryType>
                                        selected
                                    </#if>
                                >${category.categoryName}</option>
                            </#list>
                        </select>
                    </div>
                    <input type="hidden" name="productId" value="${(productInfo.productId)!''}">
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>