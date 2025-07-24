/**
 * 
 */

$(() => {
    $(".nav-link").click(function (e) {
        const isTopMenu = $(this).siblings('ul.nav-treeview').length > 0;

        if (!isTopMenu) {
            e.preventDefault(); // 하위 메뉴 클릭 시 페이지 이동 막음(필요하면 제거)
            $(".nav-link").removeClass("active");
            $(this).addClass("active");
            $(this).closest('ul.nav-treeview')
                   .closest('li.menu-open')
                   .children('a.nav-link')
                   .addClass('active');
        } else {
            // 상위 메뉴 클릭 시 아무 처리도 안 함 (active 유지)
            // e.preventDefault()도 안 함 -> 링크 이동 가능
        }
    });
});








