<#import "../layout.ftlh" as resumePage>
<@resumePage.layout ; spring>
    <#if resumes?has_content>

        <div class="d-flex align-items-center justify-content-between mb-4">
            <div style="width: 280px;"></div>
            <#if totalPages != 1>
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg mb-0">
                        <li class="page-item <#if currentPage == 0>disabled</#if>">
                            <a class="page-link bg-warning text-dark fw-bold"
                               href="/resumes/showAll?page=${currentPage - 1}"> ← </a>
                        </li>
                        <#list 0..totalPages-1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link bg-warning text-dark fw-bold"
                                   href="/resumes/showAll?page=${p}"> ${p + 1} </a>
                            </li>
                        </#list>
                        <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                            <a class="page-link bg-warning text-dark fw-bold"
                               href="/resumes/showAll?page=${currentPage + 1}"> → </a>
                        </li>
                    </ul>
                </nav>
            </#if>
            <div style="width: 280px;"></div>
        </div>

        <div class="card p-3 mb-4 shadow-sm">
            <div class="row g-3 align-items-end">
                <div class="col-md-9">
                    <label for="categoryFilter" class="form-label">Фильтр по категориям:</label>
                    <select id="categoryFilter" class="form-select">
                        <option value="">Все категории</option>
                        <#list categories?values as categoryName>
                            <option value="${categoryName}">${categoryName}</option>
                        </#list>
                    </select>
                </div>
                <div class="col-md-3">
                    <button id="applyFilterBtn" class="btn btn-warning w-100">Применить</button>
                </div>
            </div>
        </div>

        <div class="row" id="resumesContainer">
            <#list resumes as r>
                <div class="col-12 mb-4 resume-card" data-category="${r.categoryName! ''}">
                    <div class="card h-100 shadow border bg-body-tertiary">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title"><@spring.message "resumes.resume.name"/> ${r.name}</h5>
                            <p class="card-text"><@spring.message "resumes.resume.email"/> ${r.applicant}</p>
                            <p class="text-muted small">Категория: ${r.categoryName! 'Не указана'}</p>
                            <strong><@spring.message "resumes.resume.salary"/></strong> ${r.salary?string.currency}

                            <div class="mt-auto text-end">
                                <a href="/resumes/${r.id}" class="btn btn-outline-warning">
                                    <@spring.message "resumes.resume.more"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

    <#else>
        <p class="text-muted text-center fs-5 py-5"><@spring.message "resumes.resume.is_empty"/></p>
    </#if>

    <script src="/static/js/resumeFilter.js"></script>
</@resumePage.layout>