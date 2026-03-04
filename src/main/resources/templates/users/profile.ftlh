<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <section class="py-5 ">
        <div class="container">

            <#assign isApplicant = (user.role?? && user.role == 1) />
            <#assign isEmployer   = (user.role?? && user.role == 2) />

            <h2 class="text-center fw-bold mb-5">
                <@spring.message "profile.title"/>
                <#if isApplicant>  <@spring.message "profile.role.applicant"/>
                <#elseif isEmployer> <@spring.message "profile.role.employer"/>
                <#else> <@spring.message "profile.role.user"/></#if>
            </h2>

            <div class=" rounded-4  p-4 p-md-5">

                <div class="row mb-5 g-4 align-items-start">

                    <div class="col-lg-8">
                        <div class="d-flex flex-column flex-md-row align-items-start gap-4 gap-md-5">

                            <div class="text-center text-md-start">

                                <#if user.avatar?? && user.avatar?has_content>
                                    <img src="/images/${user.avatar}"
                                         alt="Аватар"
                                         class="rounded-3 shadow border border-warning border-4"
                                         style="width: 140px; height: 140px; object-fit: cover;">
                                <#else>
                                    <img src="/static/images/avatar.jpg"
                                         alt="Аватар по умолчанию"
                                         class="rounded-3 shadow border border-warning border-4"
                                         style="width: 140px; height: 140px; object-fit: cover;">
                                </#if>

                                <form action="/users/avatar" method="post" enctype="multipart/form-data" class="mt-3">
                                    <#if _csrf??>
                                        <input
                                                type="hidden"
                                                name="${(_csrf.parameterName)!'csrf-param-name'}"
                                                value="${(_csrf.token)!'csrf-token'}"
                                        >
                                    </#if>
                                    <label for="avatarInput"
                                           class="btn btn-warning btn-sm fw-bold px-4 py-2 d-block mx-auto mx-md-0 shadow-sm"
                                           style="cursor: pointer; border-radius: 50px;">
                                        <i class="bi bi-camera-fill me-1"></i> <@spring.message "profile.photo"/>
                                    </label>

                                    <input type="file"
                                           id="avatarInput"
                                           name="file"
                                           accept="image/*"
                                           class="d-none"
                                           onchange="this.form.submit();">
                                </form>
                            </div>


                            <div>
                                <h4 class="fw-bold mb-2">${user.name} ${user.surname!}</h4>
                                <#if user.age??>
                                    <p class="text-muted mb-3 fs-5">
                                        <@spring.message "profile.age"/> ${user.age}
                                    </p>
                                </#if>
                                <a href="/users/update" class="btn btn-outline-secondary btn-sm rounded-pill px-4 py-2">
                                    <@spring.message "profile.edit"/>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 d-flex align-items-stretch">
                        <div class="w-100 d-flex align-items-center justify-content-center">
                            <#if isApplicant>
                                <a href="/resumes/create" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column me-3" style="width: 180px; height: 180px; font-size: 1.25rem;">
                                    <@spring.message "profile.applicant.create"/>
                                </a>

                                <a href="#" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column" style="width: 180px; height: 180px; font-size: 1.25rem;">
                                    <@spring.message "profile.applicant.responses"/>
                                </a>

                            <#elseif isEmployer>
                                <a href="/vacancies/create" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column me-3" style="width: 180px; height: 180px; font-size: 1.25rem;">
                                    <@spring.message "profile.employer.create"/>
                                </a>

                                <a href="/resumes/showAll" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column" style="width: 180px; height: 180px; font-size: 1.25rem;">
                                    <@spring.message "profile.employer.view.resumes"/>
                                </a>
                            </#if>
                        </div>
                    </div>
                </div>

                <#if isEmployer>

                    <h4 class="fw-bold mb-4 text-center text-md-start"> <@spring.message "profile.employer.vacancies"/></h4>

                    <nav aria-label="Page navigation" class="mt-4 mb-4">
                        <ul class="pagination  mb-0 justify-content-center">
                            <li class="page-item <#if currentPage == 0>disabled</#if>">
                                <a class="page-link bg-warning text-dark fw-bold" href="/users/profile?page=${currentPage - 1}">←</a>
                            </li>
                            <#list 0..totalPages-1 as p>
                                <li class="page-item <#if p == currentPage>active</#if>">
                                    <a class="page-link bg-warning text-dark fw-bold" href="/users/profile?page=${p}">${p + 1}</a>
                                </li>
                            </#list>
                            <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                                <a class="page-link bg-warning text-dark fw-bold" href="/users/profile?page=${currentPage + 1}">→</a>
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
                                            <p id="vacancy-update-date-${vacancy.id}" class="text-muted small mb-4">
                                                <@spring.message "profile.employer.vacancy.update_date"/> ${vacancy.updateDate?date("yyyy-MM-dd")?string.medium}
                                            </p>
                                            <div class="d-flex gap-2">
                                                <button type="button"
                                                        class="btn btn-outline-warning btn-sm update-date-btn"
                                                        data-id="${vacancy.id}"
                                                        data-type="vacancy">
                                                    <@spring.message "profile.employer.vacancy.update"/>
                                                </button>

                                                <a href="/vacancies/update/${vacancy.id}" class="btn btn-outline-secondary btn-sm flex-fill rounded-pill"> <@spring.message "profile.employer.vacancy.edit"/></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    <#else>
                        <div class="p-5 text-center  rounded-3">
                            <p class="text-muted fs-5 mb-0"> <@spring.message "profile.employer.vacancy.is_empty"/></p>
                        </div>
                    </#if>

                <#elseif isApplicant>

                    <h4 class="fw-bold mb-4 text-center text-md-start"> <@spring.message "profile.applicant.resumes"/></h4>

                    <nav aria-label="Page navigation" class="mt-4 mb-4">
                        <ul class="pagination mb-0 justify-content-center">
                            <li class="page-item <#if currentPage == 0>disabled</#if>">
                                <a class="page-link bg-warning text-dark fw-bold" href="/users/profile?page=${currentPage - 1}">←</a>
                            </li>
                            <#list 0..totalPages-1 as p>
                                <li class="page-item <#if p == currentPage>active</#if>">
                                    <a class="page-link bg-warning text-dark fw-bold" href="/users/profile?page=${p}">${p + 1}</a>
                                </li>
                            </#list>
                            <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                                <a class="page-link bg-warning text-dark fw-bold" href="/users/profile?page=${currentPage + 1}">→</a>
                            </li>
                        </ul>
                    </nav>

                    <#if resumes?? && resumes?size gt 0>
                        <div class="row g-4">
                            <#list resumes as resume>
                                <div class="col-md-4">
                                    <div class="card h-100 shadow-sm border-2 rounded-3">
                                        <div class="card-body p-4">

                                            <a href="/resumes/${resume.id}"
                                               class="fw-medium mb-3 text-dark text-decoration-none">
                                                ${resume.name}
                                            </a>
                                            <p id="resume-update-date-${resume.id}"  class="text-muted small mb-4">
                                                <@spring.message "profile.applicant.resume.update_date"/> ${resume.updateDate?date("yyyy-MM-dd")?string.medium}
                                            </p>
                                            <div class="d-flex gap-2">
                                                <button type="button"
                                                        class="btn btn-outline-warning btn-sm flex-fill rounded-pill update-date-btn"
                                                        data-id="${resume.id}"
                                                        data-type="resume">
                                                    <@spring.message "profile.applicant.resume.update"/>
                                                </button>
                                                <a href="/resumes/update/${resume.id}" class="btn btn-outline-secondary btn-sm flex-fill rounded-pill">  <@spring.message "profile.applicant.resume.edit"/></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    <#else>
                        <div class="p-5 text-center  rounded-3">
                            <p class="text-muted fs-5 mb-0">  <@spring.message "profile.applicant.resume.is_empty"/></p>
                        </div>
                    </#if>

                <#else>
                    <div class="alert alert-warning text-center rounded-3">
                        <@spring.message "profile.wrong_role"/>
                    </div>
                </#if>

            </div>

        </div>
    </section>

    <script src="/static/js/updateResumeAndVacancyUpdateDate.js"></script>
</@main.layout>