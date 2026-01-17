<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <section class="py-5">
        <div class="card mx-auto shadow-lg border border-secondary-subtle rounded-4 bg-white" style="max-width: 580px;">
            <div class="card-body py-5 px-4 px-md-5">

                <h3 class="mb-5 fw-bold text-center">Редактирование профиля</h3>

                <form id="update-form" action="/users/update" method="post">

                    <div class="mb-4">
                        <label for="name" class="form-label fw-medium">Имя</label>
                        <input type="text" id="name" name="name" class="form-control border-secondary"
                               value="${user.name!''}"/>
                    </div>

                    <div class="mb-4">
                        <label for="surname" class="form-label fw-medium">Фамилия</label>
                        <input type="text" id="surname" name="surname" class="form-control border-secondary"
                               value="${user.surname!''}"/>
                    </div>

                    <div class="mb-4">
                        <label for="age" class="form-label fw-medium">Возраст</label>
                        <input type="number" id="age" name="age" class="form-control border-secondary"
                               value="${user.age!''}" min="18" max="60"/>
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-medium">Новый пароль (оставьте пустым, если не меняете)</label>
                        <input type="password" id="password" name="password" class="form-control border-secondary" />
                    </div>

                    <div class="mb-5">
                        <label for="phoneNumber" class="form-label fw-medium">Телефон</label>
                        <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control border-secondary"
                               value="${user.phoneNumber!''}"/>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                            Сохранить изменения
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </section>

</@main.layout>