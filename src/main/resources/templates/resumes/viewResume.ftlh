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

                    <div class="text-center mt-4">
                        <a href="/resumes/showAll"
                           class="btn btn-outline-warning px-4">
                            <@spring.message "resumes.resume.back"/>
                        </a>
                    </div>

                </div>
            </div>
        </div>
    </section>
</@viewR.layout>
