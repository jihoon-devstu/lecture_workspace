/**
 * 
 */

$(() => {
    $(".nav-link").click(function () {
        const isTopMenu = $(this).siblings('ul.nav-treeview').length > 0;

        if (!isTopMenu) {
            $(".nav-link").removeClass("active");
            $(this).addClass("active");
            $(this).closest('ul.nav-treeview')
                   .closest('li.menu-open')
                   .children('a.nav-link')
                   .addClass('active');
        }
    });
});


