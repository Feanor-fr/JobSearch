<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <section class="py-5">
        <div class="container">

            <h3 class="fw-bold mb-5 text-center">
                Отклики на вакансию
            </h3>

            <#if responses?? && responses?size gt 0>
                <div class="row g-4">
                    <#list responses as response>
                        <div class="col-md-12">
                            <div class="card shadow-sm rounded-3">
                                <div class="card-body">

                                    <h5>
                                        ${response.resume.applicant.name}
                                        ${response.resume.applicant.surname!}
                                    </h5>

                                    <p>
                                        <a href="/resumes/${response.resume.id}"
                                           class="fw-medium mb-3 text-dark text-decoration-none">
                                            ${response.resume.name}
                                        </a>
                                    </p>

                                    <div class="d-flex gap-2 mt-2">
                                        <#if !response.confirmation>
                                            <form method="post" action="/responses/confirm" class="m-0">
                                                <input type="hidden" name="responseId" value="${response.id}">
                                                <#if _csrf??>
                                                    <input
                                                            type="hidden"
                                                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                                                            value="${(_csrf.token)!'csrf-token'}"
                                                    >
                                                </#if>
                                                <button type="submit" class="btn btn-sm btn-warning">
                                                    Подтвердить
                                                </button>
                                            </form>
                                            <span class="badge bg-secondary">Не подтвержден</span>
                                        <#else>
                                            <span class="badge bg-warning">Подтверждено</span>
                                        </#if>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            <#else>
                <div class="alert alert-warning text-center">
                    Пока никто не откликнулся
                </div>
            </#if>

        </div>
    </section>

</@main.layout>