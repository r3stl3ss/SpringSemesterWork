<#import "spring.ftl" as spring>
<#import "header.ftlh" as template>

<@template.header>
    <h3>${userChannel.username}</h3>
    <#if !(userChannel.username==currentUserUsername)>
        <#if isSubscriber>
            <a class="btn btn-danger" href="/user/unsubscribe/${userChannel.id}">Отписаться</a>
        <#else>
            <a class="btn btn-success" href="/user/subscribe/${userChannel.id}">Подписаться</a>
        </#if>
    </#if>
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Подписки</div>
                        <h3 class="card-text">
                            <a href="/user/subs/${userChannel.id}">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Подписчики</div>
                        <h3 class="card-text">
                            <a href="/user/subs/${userChannel.id}">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#list reviews as review>
        <div class="card mb-3">
            <b>#${review.id}</b>
            <h5 class="card-title">${review.header}</h5>
            <p class="card-text">${review.text}</p>
            <h5 class="card-title">${review.rating}/10</h5>
            <div>
                <#if review.pathToPicture??>
                    <img width="640px" height="360px" src="/img/${review.pathToPicture}">
                </#if>
            </div>
            <div class="card-footer text-muted">
                by ${review.author.username}
            </div>
            <div class="card-footer text-muted">
                ${review.createdAt}
            </div>
        </div>
    </#list>
</@template.header>