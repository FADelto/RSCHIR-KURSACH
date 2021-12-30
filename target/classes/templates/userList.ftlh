<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<h1>Пользователи</h1>
<table class="table">
    <thead>
    <tr>
        <th>Имя</th>
        <th>Роль</th>
        <th>Группа</th>
        <th>Изменить</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td> ${user.groupName} </td>
        <td><a href="/user/${user.id}" class="btn btn-primary" role="button">изменить</a></td>
        </tr>
    </#list>
    </tbody>
</table>
    <h1>Обновление базы данных предметов</h1>
    <a class="btn btn-primary"  href="/update" role="button">Обновить</a>
</@c.page>