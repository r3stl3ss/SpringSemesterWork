<#assign loggedIn = Session.SPRING_SECURITY_CONTEXT??>

<#if loggedIn>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        username = user.getUsername()
        isPhoneProved = user.isPhoneProved()
        userExists = true
        currentUserId = user.getId()
    >
<#else>
    <#assign
    username = "unknown"
    isPhoneProved = false
    userExists = false
    >
</#if>
