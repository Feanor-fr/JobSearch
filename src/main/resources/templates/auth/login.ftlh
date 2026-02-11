<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <section class="py-5">
        <div class="card mx-auto shadow-lg border border-secondary-subtle rounded-4 bg-white" style="max-width: 580px;">
            <div class="card-body py-5 px-4 px-md-5">

                <h3 class="mb-5 fw-bold text-center"><@spring.message "login.title"/></h3>

                <form id="login-form" action="/auth/login" method="post">
                    <#if _csrf??>
                        <input
                                type="hidden"
                                name="${(_csrf.parameterName)!'csrf-param-name'}"
                                value="${(_csrf.token)!'csrf-token'}"
                        >
                    </#if>

                    <div class="mb-4">
                        <label for="form2Example1" class="form-label fw-medium"><@spring.message "login.email"/></label>
                        <input
                                type="email"
                                id="form2Example1"
                                name="username"
                                class="form-control border-secondary"
                        />
                    </div>

                    <div class="mb-4">
                        <label for="form2Example2" class="form-label fw-medium"><@spring.message "login.password"/></label>
                        <input
                                type="password"
                                id="form2Example2"
                                name="password"
                                class="form-control border-secondary"
                                placeholder="••••••••"
                        />
                    </div>


                    <#if SPRING_SECURITY_LAST_EXCEPTION?? || error??>
                        <div class="alert alert-warning mb-4 text-center" role="alert">
                            <@spring.message "login.error"/>
                        </div>
                    </#if>

                    <div class="text-center">
                        <button
                                type="submit"
                                class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                            <@spring.message "login.login"/>
                        </button>
                    </div>

                    <div class="text-center mt-1">

                        <a href="/auth/forgot_password" class="text-warning text-decoration-none fw-semibold me-3">
                            <@spring.message "login.forgot_password"/>
                        </a>
                    </div>

                </form>

            </div>
        </div>
    </section>

</@main.layout>