<#import "../layout.ftlh" as reg>
<@reg.layout>

    <section class="py-5">
        <div class="card mx-auto shadow-lg border border-secondary-subtle rounded-4 bg-white" style="max-width: 580px;">
            <div class="card-body py-5 px-4 px-md-5">

                <h3 class="mb-5 fw-bold text-center">Регистрация</h3>

                <form id="register-form" action="/auth/register" method="post">

                    <div class="mb-4">
                        <label for="name" class="form-label fw-medium">Имя</label>
                        <input type="text" id="name" name="name" class="form-control border-secondary"  />
                    </div>

                    <div class="mb-4">
                        <label for="surname" class="form-label fw-medium">Фамилия</label>
                        <input type="text" id="surname" name="surname" class="form-control border-secondary"  />
                    </div>

                    <div class="mb-4">
                        <label for="age" class="form-label fw-medium">Возраст</label>
                        <input type="number" id="age" name="age" class="form-control border-secondary"/>
                    </div>


                    <div class="mb-4">
                        <label for="email" class="form-label fw-medium">Email</label>
                        <input type="email" id="email" name="email" class="form-control border-secondary"/>
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-medium">Пароль</label>
                        <input type="password" id="password" name="password" class="form-control border-secondary"/>
                    </div>


                    <div class="mb-5">
                        <label for="phoneNumber" class="form-label fw-medium">Телефон</label>
                        <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control border-secondary"/>
                    </div>

                    <div class="mb-5">
                        <label class="form-label fw-bold text-center d-block mb-3">Я хочу:</label>
                        <div class="d-flex flex-column flex-md-row gap-3">
                            <div class="form-check form-check-inline flex-fill m-0">
                                <input class="form-check-input d-none" type="radio" name="role" id="role-applicant" value="1"/>
                                <label class="form-check-label btn btn-outline-warning w-100 py-3 fw-medium" for="role-applicant">
                                    Найти работу
                                </label>
                            </div>
                            <div class="form-check form-check-inline flex-fill m-0">
                                <input class="form-check-input d-none" type="radio" name="role" id="role-employer" value="2"/>
                                <label class="form-check-label btn btn-outline-warning w-100 py-3 fw-medium" for="role-employer">
                                    Найти сотрудника
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                            Зарегистрироваться
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </section>
</@reg.layout>