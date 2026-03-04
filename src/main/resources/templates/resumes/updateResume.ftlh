<#import "../layout.ftlh" as editR>

<@editR.layout ; spring>
    <section class="py-5">
        <div class="container">
            <div class="card mx-auto shadow-lg border border-warning-subtle rounded-4 bg-white" style="max-width: 580px;">
                <div class="card-body py-5 px-4 px-md-5">

                    <h3 class="mb-5 fw-bold text-center text-warning"><@spring.message "update_resume.title"/></h3>

                    <form id="resume-form" action="/resumes/update/${resumeId}" method="post">
                        <#if _csrf??>
                            <input
                                    type="hidden"
                                    name="${(_csrf.parameterName)!'csrf-param-name'}"
                                    value="${(_csrf.token)!'csrf-token'}"
                            >
                        </#if>

                        <input type="hidden" name="id" value="${resumeId}" />

                        <div class="mb-4">
                            <label for="name" class="form-label fw-medium"><@spring.message "update_resume.name"/></label>
                            <@spring.formInput "resumeDto.name"
                            'id="name" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="categoryId" class="form-label fw-medium"><@spring.message "update_resume.category"/></label>
                            <@spring.formSingleSelect
                            "resumeDto.categoryId"
                            categories
                            'id="categoryId" class="form-select border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="salary" class="form-label fw-medium"><@spring.message "update_resume.salary"/></label>
                            <@spring.formInput "resumeDto.salary"
                            'id="salary" type="number" step="1000" min="0" class="form-control border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-5">
                            <div class="form-check">
                                <@spring.formCheckbox "resumeDto.isActive"
                                'id="isActive" class="form-check-input"' />
                                <label class="form-check-label fw-medium" for="isActive">
                                    <@spring.message "update_resume.is_active"/>
                                </label>
                            </div>
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <#if resumeDto.workExperienceInfos?has_content>
                            <h5 class="mt-4">Опыт работы</h5>

                            <#list resumeDto.workExperienceInfos as work>
                                <div class="border rounded p-3 mb-3">

                                    <input type="hidden"
                                           name="workExperienceInfos[${work_index}].id"
                                           value="${work.id}" />

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.workExperienceInfos[${work_index}].years" />
                                        <input type="number"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.workExperienceInfos[${work_index}].companyName" />
                                        <input type="text"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.workExperienceInfos[${work_index}].position" />
                                        <input type="text"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.workExperienceInfos[${work_index}].responsibilities" />
                                        <textarea name="${spring.status.expression}"
                                                  class="form-control border-warning">${spring.status.value!''}</textarea>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                </div>
                            </#list>
                        </#if>

                        <#if resumeDto.educationInfos?has_content>
                            <h5 class="mt-4">Образование</h5>

                            <#list resumeDto.educationInfos as edu>
                                <div class="border rounded p-3 mb-3">

                                    <input type="hidden"
                                           name="educationInfos[${edu_index}].id"
                                           value="${edu.id}" />

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.educationInfos[${edu_index}].institution" />
                                        <input type="text"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"
                                               placeholder="Учебное заведение"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.educationInfos[${edu_index}].program" />
                                        <input type="text"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"
                                               placeholder="Программа"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.educationInfos[${edu_index}].startDate" />
                                        <input type="date"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.educationInfos[${edu_index}].endDate" />
                                        <input type="date"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.educationInfos[${edu_index}].degree" />
                                        <input type="text"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"
                                               placeholder="Степень"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                </div>
                            </#list>
                        </#if>

                        <#if resumeDto.contactInfos?has_content>
                            <h5 class="mt-4">Контакты</h5>

                            <#list resumeDto.contactInfos as contact>
                                <div class="mb-3 border rounded p-3">

                                    <input type="hidden"
                                           name="contactInfos[${contact_index}].id"
                                           value="${contact.id}" />

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.contactInfos[${contact_index}].typeId" />
                                        <select name="${spring.status.expression}"
                                                class="form-select border-warning">
                                            <#list contactTypes?keys as key>
                                                <option value="${key}"
                                                        <#if key?string == spring.status.value?string>selected</#if>>
                                                    ${contactTypes[key]}
                                                </option>
                                            </#list>
                                        </select>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-2">
                                        <@spring.bind "resumeDto.contactInfos[${contact_index}].contactValue" />
                                        <input type="text"
                                               name="${spring.status.expression}"
                                               value="${spring.status.value!''}"
                                               class="form-control border-warning"
                                               placeholder="Введите контакт"/>
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                </div>
                            </#list>
                        </#if>


                        <div class="text-center mt-5 d-flex justify-content-center gap-4 flex-wrap">
                            <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                                <@spring.message "update_resume.publish"/>
                            </button>

                            <a href="/users/profile" class="btn btn-outline-secondary btn-lg px-5 py-3">
                                <@spring.message "update_resume.cancel"/>
                            </a>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </section>
</@editR.layout>