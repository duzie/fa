<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>我的账单</title>
    <link rel="stylesheet" href="http://www.getuikit.net/docs/css/uikit.docs.min.css"/>

</head>
<body>

<div class="uk-overflow-container" style="margin: 10px;">
<table class="uk-table "  >
    <tbody>
<#list bill as b>
    <tr>
        <td>${b.billDate?string('yyyy-MM-dd')}</td>
        <td>${b.amount}</td>
        <td>
            <table class="uk-table " >
                <#list b.billDetails as detail>
                <tr>
                    <td>${detail.billName!}</td>
                    <td>${detail.amount}</td>
                </tr>
                </#list>
            </table>
        </td>
    </tr>
</#list>
    </tbody>
</table>
</div>
</body>
</html>