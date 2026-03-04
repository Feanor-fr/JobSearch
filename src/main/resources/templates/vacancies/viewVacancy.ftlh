<#import "../layout.ftlh" as viewV>

<@viewV.layout ; spring>
    <section class="py-5">
        <div class="container">
            <div class="card mx-auto shadow-lg border border-warning-subtle rounded-4 bg-white" style="max-width: 700px;">
                <div class="card-body py-5 px-4 px-md-5">

                    <h3 class="mb-4 fw-bold text-center text-warning">
                        ${vacancy.name}
                    </h3>

                    <div class="mb-4">
                        <strong><@spring.message "create_vacancy.category"/>:</strong>
                        ${vacancy.categoryName}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "view_vacancy.author"/>:</strong>
                        ${vacancy.author}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "create_vacancy.salary"/>:</strong>
                        ${vacancy.salary?string.currency}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "create_vacancy.description"/>:</strong>
                        <p class="mt-2">${vacancy.description}</p>
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "create_vacancy.experience"/>:</strong>
                        ${vacancy.expFrom} - ${vacancy.expTo}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "view_vacancy.created"/>:</strong>
                        ${vacancy.createdDate?date("yyyy-MM-dd")?string.medium}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "view_vacancy.updated"/>:</strong>
                        ${vacancy.updateDate?date("yyyy-MM-dd")?string.medium}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "view_vacancy.status"/>:</strong>
                        <#if vacancy.isActive>
                            <span class="badge bg-success">
                            <@spring.message "view_vacancy.active"/>
                        </span>
                        <#else>
                            <span class="badge bg-secondary">
                            <@spring.message "view_vacancy.inactive"/>
                        </span>
                        </#if>
                    </div>

                    <div class="text-center mt-4">
<#--                        <a href="/vacancies"-->
<#--                           class="btn btn-outline-warning px-4">-->
<#--                            <@spring.message "view_vacancy.back"/>-->
<#--                        </a>-->
                    </div>

                </div>
            </div>
        </div>
    </section>
</@viewV.layout>
