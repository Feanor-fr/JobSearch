document.addEventListener('DOMContentLoaded', onLoad);

function onLoad() {
    const applyFilterBtn = document.getElementById("applyFilterBtn");

    applyFilterBtn.addEventListener("click", function () {
        const category = document.getElementById('categoryFilter').value;

        const filter = {
            category: category
        };

        save(filter);
        applyFiltering(filter);
    });

    const savedFilter = restore();
    if (savedFilter) {
        document.getElementById('categoryFilter').value = savedFilter.category || "";
        applyFiltering(savedFilter);
    }
}

function save(filter) {
    const userJson = localStorage.getItem('user');
    if (!userJson) return;

    const user = JSON.parse(userJson);
    localStorage.setItem('resumeFilters_' + user.username, JSON.stringify(filter));
}

function restore() {
    const userJson = localStorage.getItem('user');
    if (!userJson) return null;

    const user = JSON.parse(userJson);
    const filterJson = localStorage.getItem('resumeFilters_' + user.username);
    return filterJson ? JSON.parse(filterJson) : null;
}

function applyFiltering(filter) {
    const resumes = document.querySelectorAll('.resume-card');

    resumes.forEach(resume => {
        const resumeCategory = resume.dataset.category;
        let show = true;

        if (filter.category && resumeCategory !== filter.category) {
            show = false;
        }

        resume.style.display = show ? "" : "none";
    });
}