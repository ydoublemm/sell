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
                <form role="form" method="post" action="/sell/seller/category/save">

                    <div class="form-group">
                        <label>名称</label>
                        <input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!''}"/>
                    </div>

                    <div class="form-group">
                        <label>type</label>
                        <input name="categoryType" type="text" class="form-control" value="${(productCategory.categoryType)!''}"/>
                    </div>

                    <input type="hidden" name="categoryId" value="${(productCategory.categoryId)!''}">
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>