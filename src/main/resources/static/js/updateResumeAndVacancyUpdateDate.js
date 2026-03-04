document.addEventListener('DOMContentLoaded', onLoad);

function onLoad() {
    const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    document.querySelectorAll('.update-date-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            const id = btn.dataset.id;
            const type = btn.dataset.type;

            let url;
            if (type === "vacancy") {
                url = `/vacancies/updateDate/${id}`;
            } else if (type === "resume") {
                url = `/resumes/updateDate/${id}`;
            }

            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                }
            })
                .then(response => response.json())
                .then(data => {
                    const dateEl = document.querySelector(`#${type}-update-date-${id}`);
                    if (dateEl && data.updateDate) {
                        dateEl.textContent = data.updateDate;
                    }
                });
        });
    });
}