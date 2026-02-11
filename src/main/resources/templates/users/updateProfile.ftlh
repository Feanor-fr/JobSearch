<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <section class="py-5">
        <div class="card mx-auto shadow-lg border border-secondary-subtle rounded-4 bg-white" style="max-width: 580px;">
            <div class="card-body py-5 px-4 px-md-5">

                <h3 class="mb-5 fw-bold text-center"> <@spring.message "update_profile.title"/></h3>

                <form id="update-form" action="/users/update" method="post">
                    <#if _csrf??>
                        <input
                                type="hidden"
                                name="${(_csrf.parameterName)!'csrf-param-name'}"
                                value="${(_csrf.token)!'csrf-token'}"
                        >
                    </#if>

                    <div class="mb-4">
                        <label for="name" class="form-label fw-medium"><@spring.message "update_profile.name"/></label>
                        <@spring.formInput "userForUpdate.name" 'id="name" class="form-control border-secondary"'/>
                        <@spring.showErrors "<br>" "error text-danger"/>
                    </div>

                    <div class="mb-4">
                        <label for="surname" class="form-label fw-medium"><@spring.message "update_profile.surname"/></label>
                        <@spring.formInput "userForUpdate.surname" 'id="surname" class="form-control border-secondary"'/>
                        <@spring.showErrors "<br>" "error text-danger"/>
                    </div>

                    <div class="mb-4">
                        <label for="age" class="form-label fw-medium"><@spring.message "update_profile.age"/></label>
                        <@spring.formInput "userForUpdate.age" 'id= "age" type="number" class="form-control border-secondary"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-medium"><@spring.message "update_profile.password"/></label>
                        <@spring.formPasswordInput "userForUpdate.password" 'id= "password" type="password" class="form-control border-secondary"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>

                    <div class="mb-5">
                        <label for="phoneNumber" class="form-label fw-medium"><@spring.message "update_profile.phone_number"/></label>
                        <@spring.formInput "userForUpdate.phoneNumber" 'id= "phoneNumber" type="tel" class="form-control border-secondary"' />
                        <@spring.showErrors  "<br>" "error text-danger" />
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                            <@spring.message "update_profile.save"/>
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </section>

</@main.layout>