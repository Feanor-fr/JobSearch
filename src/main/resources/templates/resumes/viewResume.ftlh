<#import "../layout.ftlh" as viewR>

<@viewR.layout ; spring>
    <section class="py-5">
        <div class="container">
            <div class="card mx-auto shadow-lg border border-warning-subtle rounded-4 bg-white" style="max-width: 700px;">
                <div class="card-body py-5 px-4 px-md-5">

                    <h3 class="mb-4 fw-bold text-center text-warning">
                        ${resume.name}
                    </h3>

                    <div class="mb-4">
                        <strong><@spring.message "resumes.resume.applicant"/>:</strong>
                        ${resume.applicant}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "create_resume.category"/>:</strong>
                        ${resume.categoryName}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "create_resume.salary"/>:</strong>
                        ${resume.salary?string.currency}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "resumes.resume.status"/>:</strong>
                        <#if resume.isActive>
                            <span class="badge bg-success">
                                <@spring.message "resumes.resume.active"/>
                            </span>
                        <#else>
                            <span class="badge bg-secondary">
                                <@spring.message "resumes.resume.inactive"/>
                            </span>
                        </#if>
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "resumes.resume.created"/>:</strong>
                        ${resume.createdDate?date("yyyy-MM-dd")?string.medium}
                    </div>

                    <div class="mb-4">
                        <strong><@spring.message "resumes.resume.updated"/>:</strong>
                        ${resume.updateDate?date("yyyy-MM-dd")?string.medium}
                    </div>

<#--                    <div class="text-center mt-4">-->
<#--                        <a href="/resumes/showAll"-->
<#--                           class="btn btn-outline-warning px-4">-->
<#--                            <@spring.message "resumes.resume.back"/>-->
<#--                        </a>-->
<#--                    </div>-->

                </div>

                <div class="card-body py-5 px-4 px-md-5">

                    <h5 class="fw-bold text-warning mb-3">
                        <i class="bi bi-mortarboard-fill"></i> Образование
                    </h5>

                    <#if resume.educationInfos?has_content>
                        <#list resume.educationInfos as edu>
                            <div class="border rounded-3 p-3 mb-3 bg-light">
                                <div><strong>Учебное заведение:</strong> ${edu.institution}</div>
                                <div><strong>Программа:</strong> ${edu.program}</div>
                                <div><strong>Степень:</strong> ${edu.degree}</div>
                                <div>
                                    <strong>Период:</strong>
                                    ${edu.startDate?date("yyyy-MM-dd")?string.medium} -
                                    ${edu.endDate?date("yyyy-MM-dd")?string.medium}
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <p class="text-muted">Информация об образовании отсутствует</p>
                    </#if>

                    <h5 class="fw-bold text-warning mb-3">
                        <i class="bi bi-briefcase-fill"></i> Опыт работы
                    </h5>

                    <#if resume.workExperienceInfos?has_content>
                        <#list resume.workExperienceInfos as work>
                            <div class="border rounded-3 p-3 mb-3 bg-light">
                                <div><strong>Компания:</strong> ${work.companyName}</div>
                                <div><strong>Должность:</strong> ${work.position}</div>
                                <div><strong>Стаж:</strong> ${work.years} лет</div>
                                <div class="mt-2">
                                    <strong>Обязанности:</strong>
                                    <p class="mb-0">${work.responsibilities}</p>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <p class="text-muted">Опыт работы отсутствует</p>
                    </#if>

                    <h5 class="fw-bold text-warning mb-3">
                        <i class="bi bi-envelope-fill"></i> Контактная информация
                    </h5>

                    <#if resume.contactInfos?has_content>
                        <ul class="list-group">
                            <#list resume.contactInfos as contact>
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>${contact.contactValue}</span>
                                </li>
                            </#list>
                        </ul>
                    <#else>
                        <p class="text-muted">Контактная информация отсутствует</p>
                    </#if>
                </div>

            </div>
        </div>
    </section>
</@viewR.layout>
