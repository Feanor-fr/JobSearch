<#import "spring.ftl" as spring>
<#macro layout>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <title>Job Search</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-body-secondary">

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
        <div class="container-fluid px-3 px-md-4">
            <a class="navbar-brand text-warning fw-bold fs-4" href="/">Job Search</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarContent">
                <div class="ms-auto d-flex align-items-center gap-3">
                    <#assign known = SPRING_SECURITY_CONTEXT?? >
                    <#if known>
                        <#list SPRING_SECURITY_CONTEXT.authentication.authorities as a>
                            <#if a == "Employer">
                                <a href="/resumes/showAll" class="btn btn-outline-warning px-4 text-decoration-none">Резюме</a>

                            <#elseif a == "Applicant">
                                <a href="/users/companies" class="btn btn-outline-warning px-4 text-decoration-none">Компании</a>

                            </#if>
                        </#list>

                        <#assign user = SPRING_SECURITY_CONTEXT.authentication.principal>
                        <#if user??>
                            <a href="/users/profile" class="text-warning text-decoration-none fw-semibold me-3">
                                ${user.username}
                            </a>




                            <form action="/auth/logout" method="post" class="m-0">
                                <button type="submit" class="btn btn-outline-warning px-4">Выйти</button>
                            </form>
                        </#if>
                    <#else>
                        <a href="/auth/login"    class="btn btn-warning px-4">Вход</a>
                        <a href="/auth/register" class="btn btn-outline-warning px-4">Регистрация</a>
                    </#if>
                </div>
            </div>
        </div>
    </nav>

    <main class="container py-4 py-md-5 flex-grow-1">
        <#nested spring>
    </main>

    <footer class="mt-auto bg-dark py-3 border-top border-warning">
        <div class="container text-center">
            <span class="text-warning fw-medium">Job Search © ${.now?string("yyyy")}</span>
        </div>
    </footer>

    </body>
    </html>
</#macro>