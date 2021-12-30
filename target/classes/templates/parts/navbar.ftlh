<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Сервис для просмотра расписания</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/weekSchedule">Расписание на неделю</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/daySchedule">Расписание на день</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">Админ-панель</a>
            </li>
            </#if>
        </ul>
        <div class="navbar-text mr-2">${group}</div>
        <div class="navbar-text mr-3">${name}</div>
        <#if name != "unknown"><@l.logout/></#if>
        <#if name = "unknown"><a href="/login"><button class="btn btn-outline-light" type="submit">Войти</button></a></#if>
    </div>
</nav>