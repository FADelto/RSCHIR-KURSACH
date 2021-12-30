<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name  = user.getUsername()
        group = user.getGroupName()
        isAdmin = user.isAdmin()
    >
    <#else>
        <#assign
        name = "unknown"
        group = "unknown Group"
        isAdmin = false
        >
</#if>