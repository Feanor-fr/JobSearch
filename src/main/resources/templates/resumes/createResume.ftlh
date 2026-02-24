<#import "../layout.ftlh" as createR>

<@createR.layout ; spring>
    <section class="py-5">
        <div class="container">
            <div class="card mx-auto shadow-lg border border-warning-subtle rounded-4 bg-white" style="max-width:680px;">
                <div class="card-body py-5 px-4 px-md-5">

                    <h3 class="mb-5 fw-bold text-center text-warning"><@spring.message "create_resume.title"/></h3>

                    <form id="resume-form" action="/resumes/create" method="post">
                        <#if _csrf??>
                            <input
                                    type="hidden"
                                    name="${(_csrf.parameterName)!'csrf-param-name'}"
                                    value="${(_csrf.token)!'csrf-token'}"
                            >
                        </#if>

                        <div class="mb-4">
                            <label for="name" class="form-label fw-medium"><@spring.message "create_resume.name"/></label>
                            <@spring.formInput "resumeDto.name" 'id="name" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="categoryId" class="form-label fw-medium"><@spring.message "create_resume.category"/></label>
                            <@spring.formSingleSelect
                            "resumeDto.categoryId"
                            categories
                            'id="categoryId" class="form-select border-warning"'
                            />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="salary" class="form-label fw-medium"><@spring.message "create_resume.salary"/></label>
                            <@spring.formInput "resumeDto.salary"
                            'id="salary" type="number" step="1000" min="0" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-5">
                            <div class="form-check">
                                <@spring.formCheckbox "resumeDto.isActive" 'id="isActive" class="form-check-input"' />
                                <label class="form-check-label fw-medium" for="isActive"><@spring.message "create_resume.is_active"/></label>
                            </div>
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <h5 class="fw-bold"><@spring.message "create_resume.contact_info"/></h5>
                            <div id="contacts-container" class="border border-warning rounded-3 p-4 bg-light">

                                <#list contactTypes?keys as typeId>
                                    <div class="mb-3">

                                        <input type="hidden"
                                               name="contactInfos[${typeId?index}].typeId"
                                               value="${typeId}">

                                        <input type="text"
                                               name="contactInfos[${typeId?index}].contactValue"
                                               class="form-control border-warning"
                                               placeholder="<@spring.message "create_resume.contact_info.title"/> ${contactTypes[typeId]}">
                                    </div>
                                </#list>

                            </div>
                        </div>

                        <div class="mb-4">
                            <h5 class="fw-bold"><@spring.message "create_resume.education"/></h5>
                            <div id="education-container"
                                 data-title="<@spring.message 'education.institution.placeholder'/>"
                                 data-prog="<@spring.message 'education.program.placeholder'/>"
                                 data-deg="<@spring.message 'education.degree.placeholder'/>">

                                <#if (resumeDto.educationInfos)??>
                                    <#list resumeDto.educationInfos as edu>
                                        <div class="border border-warning rounded-3 p-3 mb-2 position-relative bg-light">

                                            <div class="mb-2">
                                                <@spring.bind "resumeDto.educationInfos[${edu?index}].institution" />
                                                <input type="text" name="${spring.status.expression}" value="${spring.status.value!''}"
                                                       class="form-control border-warning" placeholder="<@spring.message 'education.institution.placeholder'/>">
                                                <@spring.showErrors "<br>" "error text-danger" />
                                            </div>

                                            <div class="mb-2">
                                                <@spring.bind "resumeDto.educationInfos[${edu?index}].program" />
                                                <input type="text" name="${spring.status.expression}" value="${spring.status.value!''}"
                                                       class="form-control border-warning" placeholder="<@spring.message 'education.program.placeholder'/>">
                                                <@spring.showErrors "<br>" "error text-danger" />
                                            </div>

                                            <div class="row mb-2">
                                                <div class="col-6">
                                                    <@spring.bind "resumeDto.educationInfos[${edu?index}].startDate" />
                                                    <input type="date" name="${spring.status.expression}" value="${spring.status.value!''}" class="form-control border-warning">
                                                    <@spring.showErrors "<br>" "error text-danger" />
                                                </div>
                                                <div class="col-6">
                                                    <@spring.bind "resumeDto.educationInfos[${edu?index}].endDate" />
                                                    <input type="date" name="${spring.status.expression}" value="${spring.status.value!''}" class="form-control border-warning">
                                                    <@spring.showErrors "<br>" "error text-danger" />
                                                </div>
                                            </div>

                                            <div class="mb-2">
                                                <@spring.bind "resumeDto.educationInfos[${edu?index}].degree" />
                                                <input type="text" name="${spring.status.expression}" value="${spring.status.value!''}"
                                                       class="form-control border-warning" placeholder="<@spring.message 'education.degree.placeholder'/>">
                                                <@spring.showErrors "<br>" "error text-danger" />
                                            </div>

                                            <button type="button" class="btn btn-danger btn-sm btn-remove"
                                                    style="position: absolute; top: -10px; right: -10px;"
                                                    onclick="this.parentElement.remove()">X</button>
                                        </div>
                                    </#list>
                                </#if>
                            </div>
                            <button type="button" id="btn-add-education" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column me-3" style="width: 100%; ">
                                +<@spring.message "create_resume.add_education"/>
                            </button>
                        </div>

                        <div class="mb-4">
                            <h5 class="fw-bold"><@spring.message "create_resume.work"/></h5>
                            <div id="experience-container"
                                 data-years="<@spring.message 'experience.years.placeholder'/>"
                                 data-company="<@spring.message 'experience.company.placeholder'/>"
                                 data-position="<@spring.message 'experience.position.placeholder'/>"
                                 data-resp="<@spring.message 'experience.responsibilities.placeholder'/>">

                                <#if (resumeDto.workExperienceInfos)??>
                                    <#list resumeDto.workExperienceInfos as exp>
                                        <div class="border border-warning rounded-3 p-3 mb-2 position-relative bg-light">

                                            <div class="mb-2">
                                                <@spring.bind "resumeDto.workExperienceInfos[${exp?index}].years" />
                                                <input type="number"
                                                       name="${spring.status.expression}"
                                                       value="${spring.status.value!''}"
                                                       class="form-control border-warning"
                                                       min="1"
                                                       placeholder="<@spring.message 'experience.years.placeholder'/>">
                                                <@spring.showErrors "<br>" "error text-danger" />
                                            </div>

                                            <div class="mb-2">
                                                <@spring.bind "resumeDto.workExperienceInfos[${exp?index}].companyName" />
                                                <input type="text"
                                                       name="${spring.status.expression}"
                                                       value="${spring.status.value!''}"
                                                       class="form-control border-warning"
                                                       placeholder="<@spring.message 'experience.company.placeholder'/>">
                                                <@spring.showErrors "<br>" "error text-danger" />
                                            </div>

                                            <div class="mb-2">
                                                <@spring.bind "resumeDto.workExperienceInfos[${exp?index}].position" />
                                                <input type="text"
                                                       name="${spring.status.expression}"
                                                       value="${spring.status.value!''}"
                                                       class="form-control border-warning"
                                                       placeholder="<@spring.message 'experience.position.placeholder'/>">
                                                <@spring.showErrors "<br>" "error text-danger" />
                                            </div>

                                            <div class="mb-2">
                                                <@spring.bind "resumeDto.workExperienceInfos[${exp?index}].responsibilities" />
                                                <input type="text"
                                                       name="${spring.status.expression}"
                                                       value="${spring.status.value!''}"
                                                       class="form-control border-warning"
                                                       placeholder="<@spring.message 'experience.responsibilities.placeholder'/>">
                                                <@spring.showErrors "<br>" "error text-danger" />
                                            </div>

                                            <button type="button"
                                                    class="btn btn-danger btn-sm btn-remove"
                                                    style="position: absolute; top: -10px; right: -10px;"
                                                    onclick="this.parentElement.remove()">
                                                X
                                            </button>
                                        </div>
                                    </#list>
                                </#if>
                            </div>
                            <button type="button" id="btn-add-experience" class="btn btn-warning fw-bold rounded-3 d-flex align-items-center justify-content-center flex-column me-3" style="width: 100%;">
                                +<@spring.message "create_resume.add_work"/>
                            </button>
                        </div>

                        <div class="text-center mt-5 d-flex justify-content-center gap-4 flex-wrap">
                            <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                                <@spring.message "create_resume.publish"/>
                            </button>

                            <a href="/users/profile" class="btn btn-outline-secondary btn-lg px-5 py-3">
                                <@spring.message "create_resume.cancel"/>
                            </a>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </section>
    <script src="/static/js/createResume.js"></script>
</@createR.layout>