<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="d-flex justify-content-center">Логин</div>
    <@l.login "/login" />
    <div class="d-flex justify-content-center"><a href="/registration">у меня нет аккаунта</a></div>
</@c.page>