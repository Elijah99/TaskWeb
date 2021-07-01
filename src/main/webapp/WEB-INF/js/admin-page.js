function showAddMenuForm() {
    if ($(".usersForm").css('display')!=='none') {
        $(".usersForm").css('display', 'none');
    }
    if ($(".add-menu-container").css('display')===('none')) {
        $(".add-menu-container").css('display', 'flex');
    }
}

function showUsersForm() {
    window.location.href = "${pageContext.request.contextPath}/controller?command=users-crud";
}