<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
     <#include "../common/header.ftl">
</head>
<body>


<div id="wrapper" class="toggled">
<#--边栏sidebar-->
    <#include  "../common/nav.ftl">
<#--主要内容content-->
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-6 column">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>订单id</th>
                        <th>订单总金额</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.orderAmount}</td>
                    </tr>
                    </tbody>
                </table>
            </div>


            <div class="col-md-12 column">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>商品id</th>
                        <th>商品名称</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>总额</th>
                    </tr>
                    </thead>
                    <tbody>
                <#list orderDTO.orderDetailList as orderdetail>
                <tr>
                    <td>${orderdetail.productId}</td>
                    <td>${orderdetail.productName}</td>
                    <td>${orderdetail.productPrice}</td>
                    <td>${orderdetail.productQuantity}</td>
                    <td>${orderdetail.productPrice  * orderdetail.productQuantity}</td>
                </tr>
                </#list>
                    </tbody>
                </table>
            </div>
        </div>
    <#if orderDTO.orderStatusEnum.msg=="新订单">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-lg btn-primary">完结订单</a>
                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-danger btn-lg">取消订单</a>
            </div>
        </div>
    </#if>
    </div>
</div>



</body>
</html>