<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <h1>Редактор пользователей</h1>
    <form action="/user" method="post">
        <table class="table">
            <tbody>
            <tr><input type="text" name="username" value="${user.username}"></tr>
            <tr><#list  roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
            </div>
        </#list></tr>
            <tr><input type="hidden" value="${user.id}" name="userId"></tr>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-primary">save</button>
            </tbody>
            </table>
    </form>

</@c.page>