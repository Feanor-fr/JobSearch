<#import "../layout.ftlh" as editV>

<@editV.layout ; spring>
    <section class="py-5">
        <div class="container">
            <div class="card mx-auto shadow-lg border border-warning-subtle rounded-4 bg-white" style="max-width: 580px;">
                <div class="card-body py-5 px-4 px-md-5">

                    <h3 class="mb-5 fw-bold text-center text-warning">Редактирование вакансии</h3>

                    <form id="vacancy-form" action="/vacancies/update/${vacancyId}" method="post">

                        <input type="hidden" name="id" value="${vacancyId}" />

                        <div class="mb-4">
                            <label for="name" class="form-label fw-medium">Название вакансии:</label>
                            <@spring.formInput "vacancyDto.name" 'id="name" class="form-control border-warning" placeholder="Введите название"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="categoryId" class="form-label fw-medium">Категория:</label>
                            <@spring.formSingleSelect "vacancyDto.categoryId" categories 'id="categoryId" class="form-select border-warning"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="salary" class="form-label fw-medium">Заработная плата:</label>
                            <@spring.formInput "vacancyDto.salary" 'id="salary" type="number" step="1000" min="0" class="form-control border-warning" placeholder="Введите оклад"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label for="description" class="form-label fw-medium">Описание вакансии:</label>
                            <@spring.formTextarea "vacancyDto.description" 'id="description" rows="6" class="form-control border-warning" placeholder="Введите описание"' />
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-medium">Требуемый опыт:</label>
                            <div class="row g-3">
                                <div class="col-6">
                                    <@spring.formInput "vacancyDto.expFrom" 'id="expFrom" type="number" min="0" class="form-control border-warning" placeholder="от"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>
                                <div class="col-6">
                                    <@spring.formInput "vacancyDto.expTo" 'id="expTo" type="number" min="0" class="form-control border-warning" placeholder="до"' />
                                    <@spring.showErrors "<br>" "error text-danger" />
                                </div>
                            </div>
                        </div>

                        <div class="mb-5">
                            <div class="form-check">
                                <@spring.formCheckbox "vacancyDto.isActive" 'id="isActive" class="form-check-input"' />
                                <label class="form-check-label fw-medium" for="isActive">Активно</label>
                            </div>
                            <@spring.showErrors "<br>" "error text-danger" />
                        </div>

                        <div class="text-center mt-5 d-flex justify-content-center gap-4 flex-wrap">
                            <button type="submit" class="btn btn-warning btn-lg px-5 py-3 fw-bold">
                                Сохранить изменения
                            </button>

                            <a href="/users/profile" class="btn btn-outline-secondary btn-lg px-5 py-3">
                                Отмена
                            </a>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </section>
</@editV.layout>