<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>udream</title>
    <link rel="apple-touch-icon" href="icon.png">
    <link href="icon.png" sizes="114x114" rel="apple-touch-icon-precomposed">
</head>
<body>
民乐<br>
<#list list as r>
    ${r.nickname}---${r.waitingCount}<br>
</#list>

园岭<br>
<#list list1 as r>
    ${r.nickname}---${r.waitingCount}<br>
</#list>
</body>
</html>