function addDishToCart(dish) {
    ${requestScope.cartDishes.add(dish)}
}

$(document).ready(
    function () {
        $(".date-menu").change(function () {
            var date = $(this).val();
            sessionStorage.setItem("dateMenu", date);
        });
    });