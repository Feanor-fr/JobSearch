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

                        <#if isApplicant && myResumes?has_content && !alreadyResponded>
                            <button class="btn btn-warning btn-lg px-4 py-2 fw-bold"
                                    data-bs-toggle="modal"
                                    data-bs-target="#respondModal">
                                Откликнуться
                            </button>

                        <#elseif isApplicant && alreadyResponded>
                            <div class="alert alert-warning">
                                Вы уже откликнулись
                            </div>
                        </#if>

                    </div>

                </div>
            </div>
        </div>
    </section>

    <div class="modal fade" id="respondModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="/responses/respond" method="post">
                    <input type="hidden" name="vacancyId" value="${vacancy.id}"/>

                    <#if _csrf??>
                        <input
                                type="hidden"
                                name="${(_csrf.parameterName)!'csrf-param-name'}"
                                value="${(_csrf.token)!'csrf-token'}"
                        >
                    </#if>

                    <div class="modal-header">
                        <h5 class="modal-title">Выберите резюме</h5>
                    </div>

                    <div class="modal-body">
                        <select name="resumeId" class="form-select">
                            <#list myResumes as resume>
                                <option value="${resume.id}">
                                    ${resume.name}
                                </option>
                            </#list>
                        </select>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-warning">
                            Отправить отклик
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@viewV.layout>
