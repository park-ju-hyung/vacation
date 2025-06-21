document.addEventListener("DOMContentLoaded", function () {
    const menuItems = document.querySelectorAll(".menu li");

    menuItems.forEach(item => {
        const link = item.querySelector("a");
        if (link && window.location.pathname === new URL(link.href).pathname) {
            item.classList.add("active");
        }
    });
});
