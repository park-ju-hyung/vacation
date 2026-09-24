document.addEventListener("DOMContentLoaded", function () {
    const currentPath = window.location.pathname.toLowerCase();
    const menuLinks = document.querySelectorAll(".sidebar .menu li a");

    menuLinks.forEach(link => {
        // Thymeleaf가 렌더링한 href 속성 추출
        const linkPath = new URL(link.href, window.location.origin).pathname.toLowerCase();

        // 1. 대분류 경로 추출 (예: /admin/Employee/list -> /admin/employee)
        // 공통 접두사 2단계 세그먼트를 기준으로 비교
        const pathSegments = linkPath.split("/").filter(Boolean); // ['admin', 'employee', 'list']
        const baseMenuPath = pathSegments.length >= 2
            ? `/${pathSegments[0]}/${pathSegments[1]}`
            : linkPath;

        // 2. 현재 URL이 해당 메뉴의 기본 경로로 시작하면 active 부여
        // (예: 현재 URL이 /admin/employee/detail 이라도 /admin/employee와 매칭)
        if (currentPath.startsWith(baseMenuPath)) {
            // li 태그에 active 추가
            const parentLi = link.closest("li");
            if (parentLi) {
                parentLi.classList.add("active");
            }
            // a 태그 자체에도 active가 필요한 경우
            link.classList.add("active");
        }

        // 3. 클릭 시각 효과 (페이지 전환 전 즉각 피드백용)
        link.addEventListener("click", function () {
            document.querySelectorAll(".sidebar .menu li").forEach(li => li.classList.remove("active"));
            document.querySelectorAll(".sidebar .menu li a").forEach(a => a.classList.remove("active"));

            const li = this.closest("li");
            if (li) li.classList.add("active");
            this.classList.add("active");
        });
    });
});