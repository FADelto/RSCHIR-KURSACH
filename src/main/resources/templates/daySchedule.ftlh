<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <body>
    <table class="table table-bordered border-dark">
        <thead>
        <tr>
            <th>День недели</th>
            <th>Название предмета</th>
            <th>Время начала</th>
            <th>Время конца</th>
        </tr>
        </thead>
        <tbody>
        <#list lessons as lesson >
            <#if lesson.weekday == 1><tr class="table-danger"><th>Понедельник</th></#if>
            <#if lesson.weekday == 2><tr class="table-success"><th>Вторник</th></#if>
            <#if lesson.weekday == 3><tr class="table-primary"><th>Среда</th></#if>
            <#if lesson.weekday == 4><tr class="table-danger"><th>Четверг</th></#if>
            <#if lesson.weekday == 5><tr class="table-success"><th>Пятница</th></#if>
            <#if lesson.weekday == 6><tr class="table-primary"><th>Суббота</th></#if>
            <th>${lesson.name}</th>
            <th>${lesson.timeStart}</th>
            <th>${lesson.timeEnd}</th>
            </tr>
        </#list>
        </tbody>
    </table>
    </body>
</@c.page>