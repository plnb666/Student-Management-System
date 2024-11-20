document.addEventListener("DOMContentLoaded", function() {
    const rowsPerPage = 17;
    const table = document.querySelector("table");
    const rows = table.querySelectorAll("tbody tr");
    const totalPages = Math.ceil(rows.length / rowsPerPage);
    let currentPage = 1;

    function showPage(page) {
        rows.forEach((row, index) => {
            row.classList.add("hidden");
            if (index >= (page - 1) * rowsPerPage && index < page * rowsPerPage) {
                row.classList.remove("hidden");
            }
        });
    }

    function createPagination() {
        const pagination = document.querySelector(".pagination");
        const firstLink = document.createElement("a");
        firstLink.href = "#";
        firstLink.textContent = "首页";
        firstLink.addEventListener("click", function(e) {
            e.preventDefault();
            currentPage = 1;
            showPage(currentPage);
            updatePagination();
        });
        pagination.appendChild(firstLink);

        const prevLink = document.createElement("a");
        prevLink.href = "#";
        prevLink.textContent = "«";
        prevLink.addEventListener("click", function(e) {
            e.preventDefault();
            if (currentPage > 1) {
                currentPage--;
                showPage(currentPage);
                updatePagination();
            }
        });
        pagination.appendChild(prevLink);

        for (let i = 1; i <= totalPages; i++) {
            const pageLink = document.createElement("a");
            pageLink.href = "#";
            pageLink.textContent = i;
            pageLink.addEventListener("click", function(e) {
                e.preventDefault();
                currentPage = i;
                showPage(currentPage);
                updatePagination();
            });
            pagination.appendChild(pageLink);
        }

        const nextLink = document.createElement("a");
        nextLink.href = "#";
        nextLink.textContent = "»";
        nextLink.addEventListener("click", function(e) {
            e.preventDefault();
            if (currentPage < totalPages) {
                currentPage++;
                showPage(currentPage);
                updatePagination();
            }
        });
        pagination.appendChild(nextLink);

        const lastLink = document.createElement("a");
        lastLink.href = "#";
        lastLink.textContent = "尾页";
        lastLink.addEventListener("click", function(e) {
            e.preventDefault();
            currentPage = totalPages;
            showPage(currentPage);
            updatePagination();
        });
        pagination.appendChild(lastLink);

        updatePagination();
    }

    function updatePagination() {
        const pagination = document.querySelector(".pagination");
        const pageLinks = pagination.querySelectorAll("a");
        pageLinks.forEach(link => link.classList.remove("active"));

        const start = Math.max(1, currentPage - 1);
        const end = Math.min(totalPages, currentPage + 1);

        pageLinks.forEach((link, index) => {
            if (index === 0 || index === 1 || index === pageLinks.length - 1 || index === pageLinks.length - 2) return;
            const pageNumber = parseInt(link.textContent);
            if (pageNumber >= start && pageNumber <= end) {
                link.classList.remove("hidden");
            } else {
                link.classList.add("hidden");
            }
        });

        pageLinks[currentPage + 1].classList.add("active");
    }

    showPage(currentPage);
    createPagination();
});