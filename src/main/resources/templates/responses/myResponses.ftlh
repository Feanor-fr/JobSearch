<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <section class="py-5">
        <div class="container">

            <h3 class="fw-bold mb-5 text-center">
                Мои отклики
            </h3>

            <#if responses?? && responses?size gt 0>
                <div class="row g-4">
                    <#list responses as response>
                        <div class="col-md-12">
                            <div class="card shadow-sm rounded-3">
                                <div class="card-body">

                                    <h5 class="fw-bold">
                                        <a href="/vacancies/${response.vacancy.id}"
                                           class="text-decoration-none text-dark">
                                            ${response.vacancy.name}
                                        </a>
                                    </h5>

                                    <p class="mb-2 text-muted">
                                        Резюме:
                                        <a href="/resumes/${response.resume.id}"
                                           class="fw-medium mb-3 text-dark text-decoration-none">
                                            ${response.resume.name}
                                        </a>
                                    </p>

                                    <#if response.confirmation>
                                        <span class="badge bg-warning">
                                        Подтверждено
                                    </span>
                                    <#else>
                                        <span class="badge bg-secondary">
                                        Ожидает подтверждения
                                    </span>
                                    </#if>

                                    <div class="card-footer bg-transparent border-0 d-flex justify-content-end">
                                        <a href="/chat/${response.id}" class="btn btn-sm btn-warning">
                                            Чат
                                        </a>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            <#else>
                <div class="alert alert-warning text-center">
                    У вас пока нет откликов
                </div>
            </#if>

        </div>
    </section>

</@main.layout>