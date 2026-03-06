<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <section class="py-5">
        <div class="container">

            <h2 class="text-center fw-bold mb-5"><@spring.message "company_profile.title"/></h2>

            <div class="rounded-4 p-4 p-md-5">

                <div class="row mb-5 g-4 align-items-start">

                    <div class="col-lg-4 text-center text-md-start mb-4 mb-lg-0">
                        <#if company.avatar?? && company.avatar?has_content>
                            <img src="/images/${company.avatar}"
                                 alt="Аватар компании"
                                 class="rounded-3 shadow border border-warning border-4"
                                 style="width: 140px; height: 140px; object-fit: cover;">
                        <#else>
                            <img src="/static/images/avatar.jpg"
                                 alt="Аватар по умолчанию"
                                 class="rounded-3 shadow border border-warning border-4"
                                 style="width: 140px; height: 140px; object-fit: cover;">
                        </#if>
                    </div>

                    <div class="col-lg-8">
                        <h4 class="fw-bold mb-2">${company.name} ${company.surname}</h4>
                        <p class="text-muted mb-3"><strong><@spring.message "company_profile.email"/></strong> ${company.email}</p>
                    </div>

                </div>

                <h4 class="fw-bold mb-4 text-center text-md-start"><@spring.message "company_profile.vacancies"/></h4>

                <nav aria-label="Page navigation" class="mt-4 mb-4">
                    <ul class="pagination mb-0 justify-content-center">
                        <li class="page-item <#if currentPage == 0>disabled</#if>">
                            <a class="page-link bg-warning text-dark fw-bold" href="?page=${currentPage - 1}">←</a>
                        </li>
                        <#list 0..totalPages-1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link bg-warning text-dark fw-bold" href="?page=${p}">${p + 1}</a>
                            </li>
                        </#list>
                        <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                            <a class="page-link bg-warning text-dark fw-bold" href="?page=${currentPage + 1}">→</a>
                        </li>
                    </ul>
                </nav>

                <#if vacancies?? && vacancies?size gt 0>
                    <div class="row g-4">
                        <#list vacancies as vacancy>
                            <div class="col-md-4">
                                <div class="card h-100 shadow-sm border-2 rounded-3">
                                    <div class="card-body p-4">
                                        <h5 class="fw-medium mb-3">${vacancy.name}</h5>
                                        <p class="text-muted small mb-4"><@spring.message "company_profile.update_date"/> ${vacancy.updateDate?date("yyyy-MM-dd")?string.medium}</p>
                                        <a href="/vacancies/${vacancy.id}" class="btn btn-outline-warning btn-sm flex-fill rounded-pill"><@spring.message "company_profile.more"/></a>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                <#else>
                    <div class="p-5 text-center rounded-3">
                        <p class="text-muted fs-5 mb-0"><@spring.message "company_profile.is_empty"/></p>
                    </div>
                </#if>

            </div>
        </div>
    </section>

</@main.layout>
