document.addEventListener("DOMContentLoaded", function () {
    const mainSelect = document.getElementById("mainSelect");
    const dynamicSelectContainer = document.getElementById("dynamicSelect");

    mainSelect.addEventListener("change", function () {
        const selectedValue = mainSelect.value;
        dynamicSelectContainer.innerHTML = "";
        if (selectedValue === "patron") {
            const text = document.createElement("span");
            text.textContent = "Search patron by name";
            text.style.fontWeight = "700";
            dynamicSelectContainer.appendChild(text);
        } else if (selectedValue === "book") {
            const bookSelect = document.createElement("select");
            const text = document.createElement("span");
            text.textContent = "Select searching option";
            bookSelect.name = "bookOptions";
            bookSelect.style.marginBottom = "10px";
            text.style.fontWeight = "700";
            text.style.marginRight = "10px";
            bookSelect.required = true;
            bookSelect.innerHTML = `
                <option value="" disabled selected>Choose</option>
                <option value="title">Title</option>
                <option value="author">Author</option>
                <option value="ISBN">ISBN</option>`;
            dynamicSelectContainer.appendChild(text);
            dynamicSelectContainer.appendChild(bookSelect);
        }
    });
});