<#import "spring.ftl" as spring>
<#import "header.ftlh" as template>

<@template.header>
    <h3>${username}</h3>
    <div> Подписчики
        <ul class="list-group">
            <#list subscribers as subscriber>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <a href="/reviews/${subscriber.id}">${subscriber.getUsername()}</a>
                    <span class="badge bg-primary rounded-pill">
                        ${subscriber.subscribers?size}
                    </span>
                </li>
            </#list>
        </ul>
    </div>
    <div> Подписки
        <ul class="list-group">
            <#list subscriptions as subscription>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <a href="/reviews/${subscription.id}">${subscription.getUsername()}</a>
                    <span class="badge bg-primary rounded-pill">
                    ${subscription.subscribers?size}
                </span>
                </li>
            </#list>
        </ul>
    </div>
</@template.header>