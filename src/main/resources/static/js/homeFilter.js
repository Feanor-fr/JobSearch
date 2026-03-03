document.addEventListener('DOMContentLoaded', onLoad);

function onLoad() {
    const applyFilter = document.getElementById("applyFilterBtn");
    applyFilter.addEventListener("click", function () {

        const search = document.getElementById('searchInput').value.toLowerCase();
        const category = document.getElementById('categoryFilter').value;
        const salarySort = document.getElementById('salarySort').value;

        const filter = {
            search: search,
            category: category,
            salarySort: salarySort
        };

        save(filter);
        filterVacancy(filter);
    });

    const saveFilter = restore();

    if (saveFilter) {

        document.getElementById('searchInput').value = saveFilter.search || "";
        document.getElementById('categoryFilter').value = saveFilter.category || "";
        document.getElementById('salarySort').value = saveFilter.salarySort || "";

        filterVacancy(saveFilter);
    }
}

function save(filter) {
    const userJson = localStorage.getItem('user');
    if (!userJson) return;

    const user = JSON.parse(userJson);
    const filterJson = JSON.stringify(filter);

    localStorage.setItem('vacancyFilters_' + user.username, filterJson);
}

function  restore() {
    const userJson = localStorage.getItem('user');
    if (!userJson) return null;

    const user = JSON.parse(userJson);

    const filterJson = localStorage.getItem('vacancyFilters_' + user.username);
    return filterJson ? JSON.parse(filterJson) : null;
}

function filterVacancy(filter) {
    let vacancies = Array.from(document.querySelectorAll('.vacancy-card'));

    vacancies.forEach(vacancy => {

        let name = vacancy.dataset.name;
        let category = vacancy.dataset.category;

        let show = true;

        if (filter.search && !name.includes(filter.search)){
            show = false;
        }

        if(filter.category && category !== filter.category){
            show = false;
        }

        if(show) {
            vacancy.style.display = "";
        } else {
            vacancy.style.display = "none";
        }
    });

    if(filter.salarySort) {
        const container = document.querySelector('.row');
        const vacancyArray = Array.from(vacancies);

        vacancyArray.sort(function (a, b) {

            const salaryA = parseFloat(a.dataset.salary);
            const salaryB = parseFloat(b.dataset.salary);

            if (filter.salarySort === "asc") {
                return salaryA - salaryB;
            } else {
                return salaryB - salaryA;
            }
        });

        vacancyArray.forEach(vacancy => container.append(vacancy));
    }
}